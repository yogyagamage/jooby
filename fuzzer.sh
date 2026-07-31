#!/usr/bin/env bash
set -euo pipefail

CLASSPATH="/Tmp/gamageyo/fika/experiments/graphhopper/core/target/graphhopper-core-12.0-SNAPSHOT.jar"

# 2. Map the entry points (SOURCES) to their targeted TPL methods (SINKS)
declare -A DIRECTED_MAP
DIRECTED_MAP=(
    ["com.graphhopper.reader.osm.OSMReaderUtility::parseDuration"]="javax.xml.datatype.Duration::getTimeInMillis"
)

COVERAGE_DIR="fuzz-coverage-reports"
mkdir -p "$COVERAGE_DIR"

for SOURCE in "${!DIRECTED_MAP[@]}"; do
    SINK="${DIRECTED_MAP[$SOURCE]}"

    echo "=========================================================="
    echo "[*] SOURCE ENTRYPOINT: $SOURCE"
    echo "[*] TARGETING SINK   : $SINK"
    echo "=========================================================="

    SOURCE_CLASS="${SOURCE%%::*}"
    SINK_CLASS="${SINK%%::*}"

    # Use the source class name to generate unique coverage report files
    REPORT_NAME="${SOURCE_CLASS##*.}"

    ./jazzer \
      --cp="$CLASSPATH" \
      --autofuzz="$SOURCE" \
      --agent_path="/Tmp/gamageyo/fika/experiments/graphhopper/jazzer_standalone.jar" \
      --instrument="$SOURCE_CLASS:$SINK_CLASS" \
      --autofuzz_ignore="java.lang.NullPointerException,java.lang.IllegalArgumentException" \
      --coverage_report="$COVERAGE_DIR/${REPORT_NAME}_report" \
      --coverage_dump="$COVERAGE_DIR/${REPORT_NAME}.exec" \
      -max_total_time=60 || echo "[!] Crash or issue detected in $SOURCE, moving to next target."

    echo "[+] Coverage file written to $COVERAGE_DIR/${REPORT_NAME}_report.zip"
done
