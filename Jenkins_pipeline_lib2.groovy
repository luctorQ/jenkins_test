
@Library('test-lib') _
import com.hastingsdirect.sql.*
import com.hastingsdirect.vo.*
import com.hastingsdirect.ep.*

events.add('run this pipeline')

println 'env:'+env
println 'env.WORKSPACE:'+env.WORKSPACE

println 'params:'+params

properties([
	parameters([
		string(name: 'BRANCH', defaultValue: 'master'),
		string(name: 'BRANCH_1', defaultValue: 'master'),
//						extendedChoiceParam(name:'JSON_PARAM',groovyScript:groovyscript,description:'Descr'),
		extendedChoiceParam(new EPPromotedBuilds("TEST_PARAM",'Promoted builds')),
		extendedChoiceParam(new EPPromotedBuilds("TEST_PARAM2",'Promoted builds'))
	])
])


pipeline {
	agent any
	stages {
		stage("Initialize"){
			steps{
				script{
					events.add('pipeline 2 ')
					
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

return this