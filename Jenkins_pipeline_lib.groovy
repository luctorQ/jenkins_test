//@Library('test-lib') _

//library(identifier:'./shared_lib') _

println 'env:'+env

println 'params:'+params

println 'thia:'+this

library(identifier: 'test-lib@master', retriever: modernSCM(
[$class: 'GitSCMSource',
	remote: 'https://github.com/luctorQ/jenkins_shared_lib.git',
	credentialsId: '8f8ed4c0-b044-44f2-8029-66964ab226d2']))


/*def esp=new com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition()
println 'esp:'+esp
*/
def utils
pipeline {
	agent any
	options{parameters([string(defaultValue: '', description: '', name: 'param1')]), pipelineTriggers([])}
/*	parameters {
		booleanParam(defaultValue: true, description: '', name: 'userFlag')
		booleanParam(defaultValue: true, description: '', name: 'userFlag11')
		
	}
*/	stages {
		stage("Initialize"){
			steps{
				script{ 
					
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
				showQuote()
//				showSharedQuote()
			}
		}
	}
}