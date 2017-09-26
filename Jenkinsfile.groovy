import hudson.tasks.junit.TestResultAction;
import groovy.text.SimpleTemplateEngine;
import groovy.transform.Field;
import hudson.model.Result;

@Field
def CI_COVERED_APPS=[ab:false,bc:true,pc:true,cc:true]


def statusBuild=[
	b1:[status:'undefined',msg:'Build status undefined'],
	b2:[status:'undefined',msg:'Build status undefined']
]

pipeline {
	agent any
	stages {
		stage('build') {
			steps {
				dir('tmp_out'){ deleteDir() }
				script{
					def build1=null;
					//					try {
					build1= build job: 'java_project1',
					propagate: false

					println 'cbr result1:'+currentBuild.result;
					println "build1 result:"+build1.result
					if(build1.rawBuild.result.isWorseThan(Result.SUCCESS)){
						println ('worse than:'+Result.SUCCESS)
						println 'culprits:'+build1.rawBuild.culprits
					}
					println 'cbr result2:'+currentBuild.result;
					
					if(build1.result!="SUCCESS") {
						def email_recipients=emailextrecipients([[$class: 'FirstFailingBuildSuspectsRecipientProvider'], [$class: 'CulpritsRecipientProvider']])
						
						println email_recipients;
						
						dir('tmp_out'){
							writeFile file:'dummy', text:''

							step([$class: 'CopyArtifact',
								projectName: 'java_project1',
								filter: '**/surefire-report.html',
								selector: [
									$class: 'SpecificBuildSelector',
									buildNumber:build1.id
								],
								target:'smoke_tests',
								flatten:true
							]);
							script{
								if(fileExists(file:'smoke_tests')) {
									zip zipFile:'report_smoke.zip',dir:'smoke_tests'
								}
							}
						}

						//					step([$class: 'FileOperationsBuilder', fileOperations: [[$class: 'FileCopyOperation', excludes: '', flattenFiles: false, includes: 'target/site/*.html', targetLocation: './smoke_test_reports']]])

						throw new hudson.AbortException("build1 failed")
					}
					/*}catch(e) {
					 if(build1.result!="SUCCESS")
					 throw new hudson.AbortException(build1)
					 }*/
					statusBuild.b1.status=build1.result;
					//					statusBuild.b1.msg=build1.rawBuild.writeWholeLogTo()
					/*def logFile=build1.rawBuild.logFile;
					 def dest=new File(pwd(),'b1_dest.log')
					 dest.write(logFile.text)*/

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
		stage('reporting') { 
			steps {
				echo 'reporting'
				echo 'reporting build result:'+currentBuild.result 
			} 
		}
	}
	environment { dsaf = '33' }
	post{
		always{
			echo 'post build result:'+currentBuild.result
			echo 'post actions'

			//			unstash "smoke_report"

			emailext to: 'luchtort@gmail.com',
			replyTo: 'pluszynski@bleak.pl',
			subject: "test email local",
			attachmentsPattern: 'tmp_out/report*.zip',
			body: """
				APPS covered by Continuous Integration Process: ${CI_COVERED_APPS.findAll({it.value}).collect({it.key.toUpperCase()})}\n
				${CI_COVERED_APPS.ab?'nie powinno byc\n':''}
				local test
			"""
		}
	}
}