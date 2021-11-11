node {
  def mvn = tool 'Default Maven';
  stage('SCM') {
    checkout scm
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
  stage('SonarQube Analysis') {
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -DskipTests"
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
  }
  stage('Build docker image') {
    withMaven {
        sh "${mvn}/bin/mvn spring-boot:build-image"
    }
  }
}
