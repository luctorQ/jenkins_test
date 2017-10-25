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

List params1 = []
List props = []

com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition test = new com.cwctravel.hudson.plugins.extended_choice_parameter.ExtendedChoiceParameterDefinition(
	"name",
	"PT_CHECKBOX",
	"VALUE, A, B",
	null,//project name
	null,
	null,
	null,
	null,// bindings
	null,
	null, // propertykey
	"VALUE, B", //default value
	null,
	null,
	null,
	null, //default bindings
	null,
	null,
	null, //descriptionPropertyValue
	null,
	null,
	null,
	null,
	null,
	null,
	null,// javascript file
	null, // javascript
	false, // save json param to file
	false, // quote
	2, // visible item count
	"DESC",
	","
)

params1 << test
//props << parameters(params)

println 'paramsddddd:'+params1


properties([parameters([
				string(name: 'BRANCH', defaultValue: 'master'),
				string(name: 'BRANCH_1', defaultValue: 'master')
			])])

properties([parameters(params1)])

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