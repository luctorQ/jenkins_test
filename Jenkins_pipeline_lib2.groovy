
@Library('test-lib') _
import com.hastingsdirect.sql.*
import com.hastingsdirect.vo.*
import com.hastingsdirect.ep.*

pipelineEvents=events

println 'events !!:'+events
events.add('run this pipeline')

println 'env:'+env
println 'env.WORKSPACE:'+env.WORKSPACE

println 'params:'+params

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
				echo 'ENV:'+env
			}
		}
		stage('set variables'){
			steps{
				eventsStore(events.list)
				script{
					def ev=eventsRestore()
					println 'restored events:'+ev
					
					//					println 'history [0]'+events.list[0]

//					println 'history [0] type'+events.list.getClass()
					
//					env.EVENTS_HISTORY=events.list
//					println ('env:'+env)
					
//					println ('EVENTS_HISTORY type:'+env.EVENTS_HISTORY.getClass())

				}
			}
		}
	}
}

return this