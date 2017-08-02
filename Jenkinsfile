pipeline {
  agent any
  stages {
    stage('stage0') {
      steps {
        parallel(
          "stage0": {
            echo 'hello'
            
          },
          "stage0_1": {
            echo 'stage1'
            
          },
          "error": {
            isUnix()
            sleep 5
            
          }
        )
      }
    }
    stage('stage3') {
      steps {
        echo 'dddd'
      }
    }
  }
}