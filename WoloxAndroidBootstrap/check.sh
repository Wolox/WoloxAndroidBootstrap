function openUrl {
    local URL=$1
    if which xdg-open > /dev/null
    then
      xdg-open $URL
    elif which gnome-open > /dev/null
    then
      gnome-open $URL
    elif which open > /dev/null
    then
      open $URL
    fi
}

./gradlew clean lint testProductionReleaseUnitTest
LINT_URL=app/build/outputs/lint-results-productionDebug.html
openUrl $LINT_URL

REPORT_URL=app/build/reports/tests/productionRelease/index.html
openUrl $REPORT_URL
