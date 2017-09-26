package jenkins_test

node() {

	stage('pre'){
		println 'pre cbr:'+currentBuild.result
	}
	stage('build'){
		echo 'build stage'

		git url: 'https://github.com/luctorQ/java_project1.git/'

		withMaven(
				// Maven installation declared in the Jenkins "Global Tool Configuration"
				maven: 'apache-maven-3.3.9',
				// Maven settings.xml file defined with the Jenkins Config File Provider Plugin
				// Maven settings and global settings can also be defined in Jenkins Global Tools Configuration
//				mavenSettingsConfig: 'my-maven-settings',
				mavenLocalRepo: '.repository'
		) {
				 // Run the maven build
			catchError{		
				bat "mvn clean install"
			} 
		} // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports

	}
	stage('post'){
		println 'build result:'+currentBuild.result
		
	}
}
