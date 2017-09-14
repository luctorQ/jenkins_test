import hudson.tasks.junit.TestResultAction;

pipeline {
	agent any
	stages {
		stage('build') {
			steps {
				script{
					def build1= build job: 'java_project1',
								propagate: false
					println "build1:"+build1
					
					if (build.getAction(hudson.tasks.junit.TestResultAction.class) == null) {
						println("No tests")
						return ("No Tests")
					  }
					

					def response2= build job: 'java_project2'
					//			  propagate: false
					println "response2:"+response2
				}
			}
		}
		stage('reporting') {
			steps { echo 'reporting' }
		}
	}
	environment { dsaf = '33' }
	post{
		always{
			echo 'post actions'

			emailext to: 'luchtort@gmail.com',
			replyTo: 'pluszynski@bleak.pl',
			subject: "test email local",
			body: """
			local test
        """
		}
	}
}