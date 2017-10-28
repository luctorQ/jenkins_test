
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
		string(name: 'BRANCH', defaultValue: 'master'),
		string(name: 'BRANCH_1', defaultValue: 'master'),
		//				extendedChoiceParam(name:'JSON_PARAM',groovyScript:groovyscript,description:'Descr'),
		extendedChoiceParam(new EPPromotedBuilds("TEST_PARAM",'Promoted builds'))
	])
])

def paramval=EPPromotedBuilds.getValue(params.TEST_PARAM)
println 'paramval:'+paramval

events.add("parameter set:${paramval}")

pipeline {
	agent any
	parameters {
		booleanParam(defaultValue: true, description: '', name: 'userFlag')
		booleanParam(defaultValue: true, description: '', name: 'userFlag11')
		booleanParam(defaultValue: true, description: '', name: 'userFlag_2')
	}
	stages {
		stage("Initialize"){
			steps{
				script{
					events.add('initialize go')
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
				echo 'HIST:'+events.list
			}
		}
	}
}