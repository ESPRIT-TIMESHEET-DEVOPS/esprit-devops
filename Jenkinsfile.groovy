node {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-login')
    }
    stage('SCM') {
        checkout scm
    }
    stage('Test') {
        withMaven {
            sh "mvn clean test"
        }
        post {
            failure {
                mail to: 'chihab.hajji@esprit.tn',
                        subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                        body: "Something is wrong with ${env.BUILD_URL}'s test"
            }
        }
    }
    stage('SonarQube Analysis') {
        withSonarQubeEnv() {
            withMaven {
                sh "mvn clean sonar:sonar -DskipTests"
            }
        }
    }
    stage('Deploy to Nexus') {
        withMaven {
            sh "mvn install deploy -DskipTests"
            rchiveArtifacts artifacts: '**/timesheet-*.jar', onlyIfSuccessful: false
        }
    }
    stage('Local Integration Tests') {
        withMaven {
            "mvn -B org.jacoco:jacoco-maven-plugin:prepare-agent-integration failsafe:integration-test failsafe:verify"
            step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/TEST-*.xml'])
        }
    }
    stage('Build docker image') {
        stages {
            stage('Build'){
                withMaven {
                    sh "mvn spring-boot:build-image"
                }
            }
            stage('Login') {
                steps {
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
            }
            stage('Push') {
                steps {
                    sh 'docker push espritchihab/timesheet:1.0'
                }
            }
        }
        post {
            always {
                sh 'docker logout'
            }
        }
    }
}

