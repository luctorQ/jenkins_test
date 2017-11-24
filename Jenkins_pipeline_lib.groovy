
@Library('test-lib') _
import com.hastingsdirect.sql.*
import com.hastingsdirect.vo.*
import com.hastingsdirect.ep.*
import com.hastingsdirect.templates.Template

events.add('run this pipeline')

println 'env:'+env
println 'env.WORKSPACE:'+env.WORKSPACE

println 'params:'+params

println 'thia:'+this
/*
 library(identifier: 'test-lib@master', retriever: modernSCM(
 [$class: 'GitSCMSource',
 remote: 'https://github.com/luctorQ/jenkins_shared_lib.git',
 credentialsId: '8f8ed4c0-b044-44f2-8029-66964ab226d2']))*/

properties([
	parameters([
		string(name: 'BRANCH'),
		string(name: 'BRANCH_1', defaultValue: 'master'),
		//						extendedChoiceParam(name:'JSON_PARAM',groovyScript:groovyscript,description:'Descr'),
		extendedChoiceParam(new EPPromotedBuilds("TEST_PARAM",'Promoted builds')),
	])
])


pipeline {
	agent any
	parameters {
		booleanParam(defaultValue: true, description: '', name: 'userFlag')
	}
	stages {
		stage("PreInit"){
			steps{
				script{
					events.add('PreInit event')
					eventsStore(type:'HOLA',msg:"HOLLLA ${env.WORKSPACE} ")
					def build=getBuild("pipeline_test_libs2",325)
					println 'BUILD'+build.getClass()

					/*					def mytemplate=new Template('gogo')
					 def str=mytemplate.eval('com/hastingsdirect/templates/emailtemplate.groovy')
					 println 'after str:'+str	
					 */				
					def	HISTORY_EVENTS_JSON="""
[{
		"date":1511369584479,"msg":"Build 621 of pc completed with result UNSTABLE","ref":{
			"build":{
				"appname":"pc","artifactoryurl":"","buildidentifier":"26294","buildresult":"UNSTABLE","created_at":1511349799117,"createdate":1511349799000,"deleted":false,"description":"","id":1870,"important":false,"jenkinsbuildnumber":621,"jenkinsbuildurl":"http://bx-cinappd03.network.uk.ad:8080/job/PC%20Build/621/","storedinartifactory":false,"svnpath":"Hastings/branches/CAD7/PolicyCenter/modules/configuration","svnrevisionnumber":26294,"trunk":false,"updated_at":1511349799117
			},"junittests":{
				"failCount":4,"failureDiffString":" / -10","skipCount":0,"testsUrl":"http://bx-cinappd03.network.uk.ad:8080/job/PC%20Build/621//testReport","totalCount":195
			}
		},"type":"APP_BUILD_DONE"
	}]
"""
					def HISTORY_EVENTS=ExtendedProperty.fromJson(HISTORY_EVENTS_JSON)
					def bindings=[
						TEST:'ok',
						APP_BUILD_DONE:HISTORY_EVENTS.findAll({it.type=='APP_BUILD_DONE'}).collect{it.ref}
					]
				

					sendEmail([
						template:'templates/email-build-deploy-summary.groovy',
						subject:'Build Test email',
						recipients: 'pluszynski@bleak.pl,pawelluszynski@hastingsdirect.onmicrosoft.com',
						attachments:'tmp_out/report*.zip',
						bindings:bindings
					])

					/*					emailext(
					 to: 'pluszynski@bleak.pl,pawelluszynski@hastingsdirect.onmicrosoft.com',
					 replyTo: 'luchtort@gmail.com',
					 subject: "CI Build on",
					 attachmentsPattern: 'tmp_out/report*.zip',
					 body: tpl
					 )
					 */

					throw new Error()

					def bb=build job: 'pipeline_test_libs2', propagate: true, wait: true,
					parameters: [
						string(name: 'BRANCH', value: 'blavalue')
					]
					println 'build result:'+bb
					println 'build class:'+bb.getClass()
					println 'ext build result:'+bb
					def restored=eventsRestore(bb)
					println('restored:'+restored)

					def j1EnvVariables = bb.buildVariables;
					println 'ext env vairalbles:'+j1EnvVariables


					def extHistory=j1EnvVariables.EVENTS_HISTORY
					println 'history of ext build:'+extHistory

					println 'history of ext build [0]:'+extHistory[0]

					println 'type fo extHistory:'+extHistory.getClass()

					eventsStore(events.list)
					eventsStore(restored)


					/*					def rawBuild=bb.rawBuild
					 println 'raw build env:'+rawBuild.getEnvironment()
					 */					
					/*def binding=rawBuild.getBinding()
					 println 'binding event:'+binding.events.list
					 */
				}
			}

		}
		stage("Initialize"){
			steps{
				script{
					eventsStore(msg:'map event store',type:'KKKK',ref:params.TEST_PARAM)
					eventsStore("new event",'NNNN')

					events.add('initialize go')
					def paramval=EPPromotedBuilds.getValue(params.TEST_PARAM)
					println 'paramval:'+paramval
					events.add("parameter set:${paramval}")

					def u=load 'lib/Utils.groovy'
					u.initialize('Pawel','L')
					u.lastname='Kowalski'
					u.showName()

					u.runU2()


					utils=load 'lib/PipelineUtils_1.groovy'


					println 'PipelineUtils:'+utils
					utils.gogo('abracadabra')

					def PipelineUtils2=load 'lib/PipelineUtils_2.groovy'
					def puInstance=PipelineUtils2.instance()
					def aa=puInstance.calculateName()
					println('aaaa:'+aa)

					/*					def clos=load 'lib/ClosurePipeline.groovy'
					 println 'clos:'+clos
					 clos(this)
					 */					
					//					def pu=utils.PU
					//					def pu=utils.getProperty('PipelineUtils')
					//				pu.calculateName()
				}
			}
		}
		stage('Show quote') {
			steps {
				script{ events.add("end of pipeline") }
				eventsStore(events.list)
				echo 'HIST:'+events.list
				script{
					def r=eventsRestore()
					echo 'HIST ALL:'+r
				}

			}
		}
	}
}