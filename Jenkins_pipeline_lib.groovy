@Library('shared-lib-example') _
 
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