import hudson.tasks.junit.TestResultAction;
import groovy.text.SimpleTemplateEngine

def statusBuild=[
	b1:[status:'undefined',msg:'Build status undefined'],
	b2:[status:'undefined',msg:'Build status undefined']
]

pipeline {
	agent any
	stages {
		stage('build') {
			steps {
				script{
					def build1= build job: 'java_project1',
					propagate: false
					
					buildStatus.b1.status=build1.result;
//					buildStatus.b1.msg=build1.rawBuild.writeWholeLogTo()
					def logFile=build1.rawBuild.logFile;
					def dest=new File(pwd(),'b1_dest.log')
					dest.write(logFile.text)
					
					println "build1:"+build1
					println 'actions:'+build1.rawBuild.actions
/*
					println 'pwd:'+pwd()

					def templateFile = new File(pwd(),"templates/simple.template")
					def engine = new SimpleTemplateEngine()
					def template = engine.createTemplate(templateFile)

					def data=[name:'testowo']

					def writable = template.make(data)
					println 'from template:'+writable*/

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
		stage('reporting') { steps { echo 'reporting' } }
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