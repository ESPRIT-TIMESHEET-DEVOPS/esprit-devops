node {

    stage('SCM') {
        checkout scm
    }
    stage('SonarQube analysis') {
        withSonarQubeEnv(credentialsId: 'f225455e-ea59-40fa-8af7-08176e86507a', installationName: 'My SonarQube Server') { // You can override the credential to be used
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        }
    }
}
//        stage('Build docker image') {
//        stages {
//
//            stage('Build'){
//                withMaven {
//                    sh "mvn spring-boot:build-image"
//                }
//            }
//            stage('Login') {
//                steps {
//                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
//                }
//            }
//            stage('Push') {
//                steps {
//                    sh 'docker push espritchihab/timesheet:1.0'
//                }
//            }
//        }
//        post {
//            always {
//                sh 'docker logout'
//            }
//        }
//    }
//    stage('Test') {
//        withMaven {
//            sh "mvn clean test"
//        }
//        post {
//            failure {
//                mail to: 'chihab.hajji@esprit.tn',
//                        subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
//                        body: "Something is wrong with ${env.BUILD_URL}'s test"
//            }
//        }
//    }
//    stage('SonarQube Analysis') {
//        withSonarQubeEnv() {
//            withMaven {
//                sh "mvn clean sonar:sonar -DskipTests"
//            }
//        }
//    }
//    stage('Deploy to Nexus') {
//        withMaven {
//            sh "mvn install deploy -DskipTests"
//            rchiveArtifacts artifacts: '**/timesheet-*.jar', onlyIfSuccessful: false
//        }
//    }
//    stage('Local Integration Tests') {
//        withMaven {
//            "mvn -B org.jacoco:jacoco-maven-plugin:prepare-agent-integration failsafe:integration-test failsafe:verify"
//            step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/TEST-*.xml'])
//        }
//    }
