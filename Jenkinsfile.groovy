pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-login')
    }
    stages {
        stage('Test') {
            steps{
                withMaven {
                    sh "mvn clean test"
                }
            }
        }
        stage('SonarQube Analysis') {
            steps{
                withSonarQubeEnv('Default SonarQube') {
                    sh "mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -DskipTests"
                }
            }
        }
        stage('Local Integration Tests') {
            steps{
                withMaven{
                    sh "mvn -B org.jacoco:jacoco-maven-plugin:prepare-agent-integration failsafe:integration-test failsafe:verify -DskipTests"
                }
//                step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/TEST-*.xml'])
            }
        }
        stage('Build'){
            steps{
                sh "mvn clean install -DskipTests"
            }
        }
        stage('Build docker image') {
            steps {
                sh 'docker build -t espritchihab/timesheet:latest .'
            }
        }
        stage('Login to docker') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push to docker') {
            steps {
                step{
                    pom = readMavenPom file: 'pom.xml'
                }
                sh 'docker push espritchihab/timesheet:'${pom.version}
            }
        }
        stage('Deploy to Nexus') {
            steps{
//                            withMaven {
//                sh "mvn install deploy -DskipTests"
//                rchiveArtifacts artifacts: '**/timesheet-*.jar', onlyIfSuccessful: false
//            }
                sh 'echo done'
            }
        }
    }
    post {
        always {
            sh 'docker logout'
            deleteDir()
        }
        failure {
            mail to: 'chihab.hajji@esprit.tn',
                    subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}'s test"
        }
    }
}