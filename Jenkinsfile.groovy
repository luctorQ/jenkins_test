import java.util.stream.Streams.AbstractStreamBuilderImpl

pipeline {
  agent any
  stages {
    stage('build') {
      steps {
		  script{
			  def response1= build job: 'java_project1',
			  propagate: false
			  println "response1:"+response1
			  
			  def response2= build job: 'java_project2',
			  propagate: false
			  println "response2:"+response2
		  }
		  
      }
    }
    stage('reporting') {
      steps {
        echo 'reporting'
		
		emailext to: 'pawelluszynski@hastingsdirect.onmicrosoft.com',
		replyTo: 'pawelluszynski@hastingsdirect.onmicrosoft.com',
		subject: "test email local",
		body: """
			local test
        """
      }
    }
  }
  environment {
    dsaf = '33'
  }
}