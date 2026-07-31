#!/usr/bin/env bash
set -euo pipefail

# 1. Define your runtime classpath (path to your compiled .jar or classes directory)
CLASSPATH="/Tmp/gamageyo/fika/experiments/graphhopper/core/target/graphhopper-core-12.0-SNAPSHOT.jar"

# 2. Map your entry points (SOURCES) to their targeted directed focus methods (SINKS)
# Format: ["SOURCE"]="SINK_CLASS::SINK_METHOD_NAME::LINE_NUMBER_WHERE_CALLED"
declare -A DIRECTED_MAP
DIRECTED_MAP=(
    ["com.graphhopper.reader.osm.OSMReaderUtility::parseDuration"]="com.graphhopper.reader.osm.OSMReaderUtility::getTimeInMillis::64"
    # Example addition:
    # ["com.graphhopper.GHRequest::setProfile"]="com.graphhopper.config.Profile::getName::112"
)

COVERAGE_DIR="fuzz-coverage-reports"
mkdir -p "$COVERAGE_DIR"

for SOURCE in "${!DIRECTED_MAP[@]}"; do
    SINK_INFO="${DIRECTED_MAP[$SOURCE]}"
    
    # Parse out the class, method name, and target line number
    IFS='::' read -r SINK_CLASS SINK_METHOD TARGET_LINE <<< "$SINK_INFO"
    
    echo "=========================================================="
    echo "[*] SOURCE ENTRYPOINT : $SOURCE"
    echo "[*] TARGET SINK CLASS : $SINK_CLASS"
    echo "[*] TARGET SINK LINE  : $TARGET_LINE ($SINK_METHOD)"
    echo "=========================================================="

    SOURCE_CLASS="${SOURCE%%::*}"
    REPORT_NAME="${SOURCE_CLASS##*.}"
    TEXT_REPORT="$COVERAGE_DIR/${REPORT_NAME}_report.txt"

    # Derive the expected source filename from the class path (e.g., OSMReaderUtility.java)
    TARGET_FILENAME="${SINK_CLASS##*.}.java"

    # 3. Execute the standard timed/continuous fuzz loop
    # We output a plain text summary instead of a zip file to make grep parsing trivial.
    ./jazzer \
      --cp="$CLASSPATH" \
      --autofuzz="$SOURCE" \
      --agent_path="/Tmp/gamageyo/fika/experiments/graphhopper/jazzer_standalone.jar" \
      --instrument="com.graphhopper.**" \
      --autofuzz_ignore="java.lang.NullPointerException,java.lang.IllegalArgumentException" \
      --coverage_report="$TEXT_REPORT" \
      -max_total_time=60 ||  echo "[!] Fuzz session interrupted or ended."

    # 4. AUTOMATED COVERAGE VERIFICATION CHECK
    if [ ! -f "$TEXT_REPORT" ]; then
        echo "[-] Error: Coverage report text file was not generated."
        continue
    fi

    echo ""
    echo "[*] Evaluating sink coverage from text logs..."

    # Extract the line coverage statistics for the specific file
    LINE_COV_INFO=$(grep "^${TARGET_FILENAME}:" "$TEXT_REPORT" | head -n 1 || true)

    if [ -z "$LINE_COV_INFO" ]; then
        echo "[-] ERROR: File $TARGET_FILENAME was not tracked in the coverage report. Is the class name correct?"
        continue
    fi

    # Check if the overall class file coverage is stuck at zero
    if [[ "$LINE_COV_INFO" == *"(0.00%)"* ]]; then
        echo "RESULT: UNCOVERAGE DETECTED. $TARGET_FILENAME line coverage is 0.00%. The fuzzer never reached your sink class."
        continue
    fi

    # Extract the array block inside the brackets [] under the Incompletely covered lines header
    # This grabs the text line *after* the file marker in the incomplete section
    INCOMPLETE_LINE_BLOCK=$(sed -n "/Incompletely covered lines:/,/^[A-Za-z]/ { /^${TARGET_FILENAME}:/p }" "$TEXT_REPORT" || true)
    
    # Extract just the raw comma-separated numbers inside the brackets []
    INCOMPLETE_NUMBERS=$(echo "$INCOMPLETE_LINE_BLOCK" | sed -E 's/.*\[(.*)\].*/\1/' | sed 's/ //g')

    # Evaluate the exact target line status
    if echo "$INCOMPLETE_NUMBERS" | grep -q -E "(^|,)$TARGET_LINE(,|$)"; then
        echo "⚠️ RESULT: PARTIAL SINK COVERAGE! Line $TARGET_LINE was reached, but its internal conditional branches were not fully exhausted."
    else
        echo "✅ RESULT: SUCCESS! Line $TARGET_LINE inside $TARGET_FILENAME was fully reached and covered by your fuzz targets."
    fi
    echo ""
done
