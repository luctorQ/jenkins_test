//@Library('test-lib') _

//library(identifier:'./shared_lib') _

println 'env:'+env

println 'params:'+params

println 'thia:'+this

library(identifier: 'test-lib@master', retriever: modernSCM(
[$class: 'GitSCMSource',
	remote: 'https://github.com/luctorQ/jenkins_shared_lib.git',
	credentialsId: '8f8ed4c0-b044-44f2-8029-66964ab226d2']))


def utils
pipeline {
	agent any
	stages {
		stage("Initialize"){
			steps{
				script{ 
					utils=load 'lib/PipelineUtils_1.groovy'
					
					
					println 'PipelineUtils:'+utils
					utils.gogo('abracadabra')
					
					def PipelineUtils2=load 'lib/PipelineUtils_2.groovy'
					def puInstance=new PipelineUtils2();
//					def pu=utils.PU
//					def pu=utils.getProperty('PipelineUtils')
					//				pu.calculateName()
				}
			}
		}
		stage('Show quote') {
			steps {
				showQuote()
				showSharedQuote()
			}
		}
	}
}