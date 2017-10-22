//@Library('test-lib') _

//library(identifier:'./shared_lib') _

println 'env:'+env

println 'params:'+params

println 'thia:'+this

library(identifier: 'test-lib@master', retriever: modernSCM(
[$class: 'GitSCMSource',
	remote: 'https://github.com/luctorQ/jenkins_shared_lib.git',
	credentialsId: '8f8ed4c0-b044-44f2-8029-66964ab226d2']))


def pipeline
pipeline {
	agent any
	stages {
		stage("Initialize"){
			steps{
				script{
					pipeline=load 'PipelineUtils.groovy'
					println 'PipelineUtils:'+pipeline
					def pu=pipeline.PipelineUtils
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