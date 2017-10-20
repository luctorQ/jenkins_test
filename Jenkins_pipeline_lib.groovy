//@Library('test-lib') _

ibrary(folderName:'shared_lib') _

library(identifier: 'test-lib@master', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/luctorQ/jenkins_shared_lib.git',
   credentialsId: '8f8ed4c0-b044-44f2-8029-66964ab226d2']))
 
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