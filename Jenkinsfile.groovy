node {
  def mvn = tool 'Default Maven';
  stage('SCM') {
    checkout scm
  }
   stage('Build docker image') {
    withMaven {
        sh "${mvn}/bin/mvn spring-boot:build-image"
    }
    post {
      always {
        deleteDir() /* clean up our workspace */
       }
       success {
             mail to: 'chihab.hajji@esprit.tn',
             subject: "Successful Pipeline: ${currentBuild.fullDisplayName}",
             body: "Pipeline ${env.BUILD_URL} built with success!"
        }
        failure {
             mail to: 'chihab.hajji@esprit.tn',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}'s SonarQubeAnalysis"
        }
    }
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:RELEASE:sonar -DskipTests"
    }
  }
    stage ('Test') {
    withMaven {
      sh "${mvn}/bin/mvn clean test"
    }
    post{
        failure {
             mail to: 'chihab.hajji@esprit.tn',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}'s test"
        }
    }
  }
  stage('Deploy to Nexus') {
    "${mvn}/bin/mvn install deploy -DskipTests"
    archiveArtifacts artifacts: '**/timesheet-*.jar', onlyIfSuccessful: false
   }
  stage('Local Integration Tests') {
     "${mvn}/bin/mvn -B org.jacoco:jacoco-maven-plugin:prepare-agent-integration failsafe:integration-test failsafe:verify"
      step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/TEST-*.xml'])
  }
 
  }
}
