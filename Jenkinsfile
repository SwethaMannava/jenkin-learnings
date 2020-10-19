/**
 * A CI pipeline for Jenkins pipeline jobs.
 */
pipeline {
    // Run this stage on any available agent
    agent any
    stages {
        stage('Build') {
            steps {
                script {
                    def test
                    def report
                    // point to exact source file
                    if(isUnix()){
                        test = load "${WORKSPACE}@script/readStewardsAndReviewers.groovy"
                        report = load "${WORKSPACE}@script/generateHtmlReport.groovy"
                    }
                    else{
                        test = load("${WORKSPACE}\\readStewardsAndReviewers.groovy")
                        report = load("${WORKSPACE}\\generateHtmlReport.groovy")
                        // report = load("${WORKSPACE}\\htmlTable.groovy")
                    }

                    def testResult = test.testSR()
                    def testReport = report.generateHtmlReport(testResult)
                    writeFile(file: 'report.html', text: testReport)
                }
            }
        }
        stage('Summary HTML REPORT') {
            steps {
                script {
                    def summaryLink = "http://file:///${WORKSPACE}\\report.html"
                    createSummary("document.png").appendText("<h2><u><a href='${summaryLink}'>MODULE STEWARDS LIST</a></u></h2>")
                }
            }
        }
    }
}
