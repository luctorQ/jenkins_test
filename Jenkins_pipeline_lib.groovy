
@Library('test-lib') _
import com.hastingsdirect.sql.*
import com.hastingsdirect.vo.*
import com.hastingsdirect.ep.*
import com.hastingsdirect.templates.Template
import com.hastingsdirect.pipeline.PipelineUtils

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
		booleanParam(name:'INCLUDE_pc'),
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
					/*					def build=getBuild("pipeline_test_libs2",325)
					 println 'BUILD'+build.getClass()
					 */
					/*					def mytemplate=new Template('gogo')
					 def str=mytemplate.eval('com/hastingsdirect/templates/emailtemplate.groovy')
					 println 'after str:'+str	
					 */				
					def	HISTORY_EVENTS_JSON='''
[
  {
    "date": 1511798042497,
    "msg": "Continuous deployment started",
    "ref": {
      "CREDS_ID": "SVC_TST_Autodeploy",
      "INCLUDE_ab": true,
      "INCLUDE_bc": true,
      "INCLUDE_cc": true,
      "INCLUDE_pc": true,
      "SVN_BRANCH": "https://bx-svnappd01.network.uk.ad/svn/Hastings/branches/PR1801"
    },
    "type": "PARAMS_PASSED"
  },
  {
    "date": 1511798530369,
    "msg": "Build 320 of bc completed with result SUCCESS",
    "ref": {
      "build": {
        "appname": "bc",
        "artifactoryurl": "",
        "buildidentifier": "26548",
        "buildresult": "SUCCESS",
        "created_at": 1511798529017,
        "createdate": 1511798529000,
        "deleted": false,
        "description": "",
        "id": 1909,
        "important": false,
        "jenkinsbuildnumber": 320,
        "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/BC%20Build/320/",
        "storedinartifactory": false,
        "svnpath": "Hastings/branches/PR1801/BillingCenter/modules/configuration",
        "svnrevisionnumber": 26548,
        "trunk": false,
        "updated_at": 1511798529017
      },
      "junittests": {
        "failCount": 0,
        "failureDiffString": " / \u00b10",
        "skipCount": 0,
        "testsUrl": "http://bx-cinappd03.network.uk.ad:8080/job/BC%20Build/320//testReport",
        "totalCount": 4
      }
    },
    "type": "APP_BUILD_DONE"
  },
  {
    "date": 1511798548522,
    "msg": "Deployment started",
    "ref": "Started by upstream project GW Deploy DEV_PERF build number 25",
    "type": "JOB_STARTED"
  },
  {
    "date": 1511798548758,
    "msg": "Checked current deployed apps",
    "ref": [
      {
        "app_id": 127,
        "appname": "ab",
        "appserver_id": 1,
        "artifactoryurl": "GuideWire/com/hastingsdirect/ab/26399/ContactManager-dbcp.ear",
        "batch": true,
        "build_id": 1883,
        "buildidentifier": "26399",
        "buildresult": "SUCCESS",
        "category": "SOR",
        "cluster_id": 1056,
        "contextroot": "ab",
        "created_at": 1497266661677,
        "createdate": 1511448784000,
        "databasesetting_id": 25,
        "deleted": false,
        "description": "",
        "drainable": false,
        "environment_id": 1,
        "hidefromdashboard": false,
        "id": 127,
        "important": false,
        "integrationpropertiespath": "D:/resources/integration",
        "integrationpropertiestype": "Test",
        "ispingable": true,
        "jenkinsbuildnumber": 83,
        "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/CM%20Build/83/",
        "name": "ab",
        "pinglastchecked": 1511798529000,
        "rootdir": "D:\\Software\\GuidewireApp\\installedApps\\GuidewireAppNode01Cell\\ab.ear\\ab.war",
        "sso": false,
        "statusreason": "Site is up",
        "storedinartifactory": true,
        "svnpath": "Hastings/trunk/ContactManager/modules/configuration",
        "svnrevisionnumber": 26399,
        "switchedoff": false,
        "trunk": true,
        "updated_at": 1511798529000
      },
      {
        "app_id": 128,
        "appname": "bc",
        "appserver_id": 1,
        "artifactoryurl": "GuideWire/com/hastingsdirect/bc/26440/BillingCenter-dbcp.ear",
        "batch": true,
        "build_id": 1893,
        "buildidentifier": "26440",
        "buildresult": "SUCCESS",
        "category": "SOR",
        "cluster_id": 1057,
        "contextroot": "bc",
        "created_at": 1497266661677,
        "createdate": 1511528200000,
        "databasesetting_id": 26,
        "deleted": false,
        "description": "",
        "drainable": false,
        "environment_id": 1,
        "hidefromdashboard": false,
        "id": 128,
        "important": false,
        "integrationpropertiespath": "D:/resources/integration",
        "integrationpropertiestype": "Test",
        "ispingable": true,
        "jenkinsbuildnumber": 317,
        "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/BC%20Build/317/",
        "name": "bc",
        "pinglastchecked": 1511798529000,
        "rootdir": "D:\\Software\\GuidewireApp\\installedApps\\GuidewireAppNode01Cell\\bc.ear\\bc.war",
        "sso": true,
        "statusreason": "Site is up",
        "storedinartifactory": true,
        "svnpath": "Hastings/branches/PR1801/BillingCenter/modules/configuration",
        "svnrevisionnumber": 26440,
        "switchedoff": false,
        "trunk": false,
        "updated_at": 1511798529000
      },
      {
        "app_id": 129,
        "appname": "cc",
        "appserver_id": 1,
        "artifactoryurl": "GuideWire/com/hastingsdirect/cc/26544/ClaimCenter-dbcp.ear",
        "batch": true,
        "build_id": 1907,
        "buildidentifier": "26544",
        "buildresult": "SUCCESS",
        "category": "SOR",
        "cluster_id": 1058,
        "contextroot": "cc",
        "created_at": 1497266661677,
        "createdate": 1511795823000,
        "databasesetting_id": 27,
        "deleted": false,
        "description": "",
        "drainable": false,
        "environment_id": 1,
        "hidefromdashboard": false,
        "id": 129,
        "important": false,
        "integrationpropertiespath": "D:/resources/integration",
        "integrationpropertiestype": "Test",
        "ispingable": true,
        "jenkinsbuildnumber": 265,
        "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/CC%20Build/265/",
        "name": "cc",
        "pinglastchecked": 1511798529000,
        "rootdir": "D:\\Software\\GuidewireApp\\installedApps\\GuidewireAppNode01Cell\\cc.ear\\cc.war",
        "sso": true,
        "statusreason": "Site is up",
        "storedinartifactory": true,
        "svnpath": "Hastings/branches/PR1801/ClaimCenter/modules/configuration",
        "svnrevisionnumber": 26544,
        "switchedoff": false,
        "trunk": false,
        "updated_at": 1511798529000
      },
      {
        "app_id": 130,
        "appname": "pc",
        "appserver_id": 1,
        "artifactoryurl": "GuideWire/com/hastingsdirect/pc/26542/PolicyCenter-dbcp.ear",
        "batch": true,
        "build_id": 1908,
        "buildidentifier": "26542",
        "buildresult": "SUCCESS",
        "category": "SOR",
        "cluster_id": 1059,
        "contextroot": "pc",
        "created_at": 1497266661677,
        "createdate": 1511795892000,
        "databasesetting_id": 65,
        "deleted": false,
        "description": "",
        "drainable": false,
        "environment_id": 1,
        "hidefromdashboard": false,
        "id": 130,
        "important": false,
        "integrationpropertiespath": "D:/resources/integration",
        "integrationpropertiestype": "Test",
        "ispingable": true,
        "jenkinsbuildnumber": 639,
        "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/PC%20Build/639/",
        "name": "pc",
        "pinglastchecked": 1511798529000,
        "rootdir": "D:\\Software\\GuidewireApp\\installedApps\\GuidewireAppNode01Cell\\pc.ear\\pc.war",
        "sso": true,
        "statusreason": "Site is up",
        "storedinartifactory": true,
        "svnpath": "Hastings/branches/PR1801/PolicyCenter/modules/configuration",
        "svnrevisionnumber": 26542,
        "switchedoff": false,
        "trunk": false,
        "updated_at": 1511798529000
      },
      {
        "app_id": 166,
        "appname": "ec",
        "appserver_id": 124,
        "artifactoryurl": "EdgeConnect/com/hastingsdirect/ec/20171106_1156_1.240.25718/Portal.ear",
        "build_id": 1697,
        "buildidentifier": "20171106_1156_1.240.25718",
        "buildresult": "UNDEFINED",
        "category": "Digital",
        "cluster_id": 1060,
        "contextroot": "Portal",
        "created_at": 1497266661700,
        "createdate": 1509969436000,
        "deleted": false,
        "description": "",
        "drainable": false,
        "edgeconfiglocation": "C:/resources/RM1/edge-config.properties",
        "edgedebuglevel": "Level 4 (All Information)",
        "edgeloglocation": "C:/tmp/eclogs/RM1",
        "environment_id": 1,
        "hidefromdashboard": false,
        "id": 166,
        "important": false,
        "integrationpropertiespath": "",
        "integrationpropertiestype": "",
        "ispingable": true,
        "jenkinsbuildnumber": 240,
        "jenkinsbuildurl": "http://BX-CINAPPD03.network.uk.ad:8080/job/EC%20Build/240/",
        "name": "ec",
        "pinglastchecked": 1511798529000,
        "rootdir": "C:\\Software\\EdgeApp\\installedApps\\EdgeAppNode01Cell\\Portal.ear\\Portal.war",
        "statusreason": "Site is up",
        "storedinartifactory": true,
        "svnpath": "Hastings/branches/CAD7/DigitalPortal",
        "svnrevisionnumber": 25718,
        "switchedoff": false,
        "trunk": false,
        "updated_at": 1511798529000
      }
    ],
    "type": "CURRENTLY_DEPLOYED"
  },
  {
    "date": 1511798624835,
    "msg": "Restarted server BX2-RM-GW01:GuidewireApp",
    "type": "GENERAL"
  },
  {
    "date": 1511798890567,
    "msg": "Deployed app bc on server BX2-RM-GW01:GuidewireApp",
    "ref": {
      "appname": "bc",
      "artifactoryurl": "",
      "buildidentifier": "26548",
      "buildresult": "SUCCESS",
      "created_at": 1511798529017,
      "createdate": 1511798529000,
      "deleted": false,
      "description": "",
      "id": 1909,
      "important": false,
      "jenkinsbuildnumber": 320,
      "jenkinsbuildurl": "http://bx-cinappd03.network.uk.ad:8080/job/BC%20Build/320/",
      "storedinartifactory": false,
      "svnpath": "Hastings/branches/PR1801/BillingCenter/modules/configuration",
      "svnrevisionnumber": 26548,
      "trunk": false,
      "updated_at": 1511798529017
    },
    "type": "DEPLOYED_APP"
  },
  {
    "date": 1511799049622,
    "msg": "Application bc started on server BX2-RM-GW01:GuidewireApp",
    "type": "APP_STARTED"
  },
  {
    "date": 1511799409448,
    "msg": "Application pc started on server BX2-RM-GW01:GuidewireApp",
    "type": "APP_STARTED"
  },
  {
    "date": 1511799603301,
    "msg": "Application cc started on server BX2-RM-GW01:GuidewireApp",
    "type": "APP_STARTED"
  },
  {
    "date": 1511799735689,
    "msg": "Application ab started on server BX2-RM-GW01:GuidewireApp",
    "type": "APP_STARTED"
  },
  {
    "date": 1511799736171,
    "msg": "end of pipeline",
    "type": "GENERAL"
  },
  {
    "date": 1511799747292,
    "msg": "Uploaded to artifactory application bc to url http://bx-cinappd02.network.uk.ad:8081/artifactory/GuideWire/com/hastingsdirect/bc/26548/BillingCenter-dbcp.ear",
    "ref": {
      "artifactName": "BillingCenter-dbcp.ear",
      "publishUrl": "http://bx-cinappd02.network.uk.ad:8081/artifactory/GuideWire/com/hastingsdirect/bc/26548/BillingCenter-dbcp.ear",
      "status": "UPLOAD_OK"
    },
    "type": "ARTIFACTORY_UPLOAD"
  }
]
'''
					def HISTORY_EVENTS=PipelineUtils.fromJson(HISTORY_EVENTS_JSON,true)
					println 'HISTORY_EVENTS:'+HISTORY_EVENTS
					println "HISTORY_EVENTS CLASS:"+HISTORY_EVENTS.getClass()

					def covered=params.findAll({key,value->
						key.startsWith('INCLUDE_') && value
					}).collect({it.key.substring("INCLUDE_".length())})
					println('covered:'+covered)
					
					def bindings=[
						PARAMS:params,
						JOB:[
							currentResult:currentBuild.currentResult,
							number:currentBuild.number,
							absoluteUrl:currentBuild.absoluteUrl
						],
						APP_BUILD_DONE:HISTORY_EVENTS.findAll({it.type=='APP_BUILD_DONE'}).collect{it.ref},
						DEPLOYED_APP:HISTORY_EVENTS.findAll({it.type=='DEPLOYED_APP'}).collect{it.ref},
						CURRENTLY_DEPLOYED:HISTORY_EVENTS.findAll({it.type=='CURRENTLY_DEPLOYED'}).collect{it.ref},
						SMOKE_TESTED:HISTORY_EVENTS.findAll({it.type=='SMOKE_TESTED'}).collect{it.ref},
						ARTIFACTORY_UPLOAD:HISTORY_EVENTS.findAll({it.type=='ARTIFACTORY_UPLOAD'}).collect{it.ref},
					]

					println 'bindings:'+bindings

					eventsStore('abc')
					sendEmail(
							template:'templates/email-build-deploy-summary.groovy',
							subject:'Build Test email 2',
							recipients: 'pluszynski@bleak.pl,pawelluszynski@hastingsdirect.onmicrosoft.com',
							attachments:'tmp_out/report*.zip',
							bindings:bindings
							)


					/*					def bb=build job: 'pipeline_test_libs2', propagate: true, wait: true,
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
					 */

					/*					def rawBuild=bb.rawBuild
					 println 'raw build env:'+rawBuild.getEnvironment()
					 */					
					/*def binding=rawBuild.getBinding()
					 println 'binding event:'+binding.events.list
					 */
				}
			}
		}
		stage('Show quote') {
			steps {
				echo 'HIST:'+eventsRestore()

			}
		}
	}
}