import hudson.tasks.junit.TestResultAction;
import groovy.text.SimpleTemplateEngine

pipeline {
	agent any
	stages {
		stage('build') {
			steps {
				script{
					def build1= build job: 'java_project1',
								propagate: false
					println "build1:"+build1
					
					println 'actions:'+build1.rawBuild.actions
					
					def templateFile = new File(".\\templates\\simple.template")
					def engine = new SimpleTemplateEngine()
					def template = engine.createTemplate(templateFile)
					
					def data=[name:'testowo']
					
					def writable = template.make(data)
					println 'from template:'+writable
					
					def testsResult=build1.rawBuild.getAction(hudson.tasks.test.AggregatedTestResultAction)
//					def testsResult=build1.getRawBuild().getAction(hudson.tasks.junit.TestResultAction.class)
					
					
					
					if (testsResult == null) {
						println("No tests")
					  }else {
						  println('tests failed count:'+testsResult.failCount)
						  println('tests skipped count:'+testsResult.skipCount)
						  println('tests total count:'+testsResult.totalCount)
						  testsResult=null;
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