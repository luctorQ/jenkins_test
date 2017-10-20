@Library('test-lib') _
 
pipeline {
    agent any
    stages {
        stage('Show quote') {
            steps {
                showQuote()
            }
        }
    }
}