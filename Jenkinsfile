pipeline {
  agent any
  stages {
    stage('stage0') {
      steps {
        parallel(
          "stage66": {
            echo 'hello'
            
          },
          "stage0_1": {
            echo 'stage1'
            
          },
          "error": {
            build 'Pawel'
            
          },
          "hf": {
            build 'Pawel'
            
          }
        )
      }
    }
    stage('stage3') {
      steps {
        echo 'dddd'
      }
    }
    stage('hgsag') {
      steps {
        parallel(
          "hgsagdd": {
            echo 'safasfd'
            echo 'asfasfdddd'
            
          },
          "gsdafas": {
            echo 'asfasfdsa'
            
          }
        )
      }
    }
  }
  environment {
    dsaf = '33'
  }
}