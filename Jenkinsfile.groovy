import java.util.stream.Streams.AbstractStreamBuilderImpl

pipeline {
  agent any
  stages {
    stage('build') {
      steps {
		  script{
			  def response= build job: 'java_project1',
			  propagate: false
			  println "response:"+response
		  }
		  
      }
    }
    stage('reporting') {
      steps {
        echo 'dddd'
      }
    }
  }
  environment {
    dsaf = '33'
  }
}