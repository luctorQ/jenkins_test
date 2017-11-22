
@Library('test-lib') _
import com.hastingsdirect.sql.*
import com.hastingsdirect.vo.*
import com.hastingsdirect.ep.*

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

					def parallel_jobs=[
						aaa:{ return 'aaa' },
						bbb:{ return 'bbb' }
					]

					parallel(parallel_jobs)

					
					println "parallel_jobs:"+parallel_jobs
					println "parallel_jobs aaa:"+parallel_jobs.aaa
					println "aaa job result:"+parallel_jobs.aaa.result

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