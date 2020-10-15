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
                    def rootDir = pwd()
                    def test
                    // point to exact source file
                    if(isUnix()){
                        test = load "${rootDir}@script/readStewardsAndReviewers.groovy"
                        report = load "${rootDir}@script/generateHtmlReport.groovy"
                    }
                    else{
                        test = load("${rootDir}\\readStewardsAndReviewers.groovy")
                        report = load("${rootDir}\\generateHtmlReport.groovy")
                        // report = load("${rootDir}\\htmlTable.groovy")
                    }

                    test.testSR()
                    report.generateHtmlReport()
                    // report.parseToHTMLTable()
                }
            }
        }
    }
}
