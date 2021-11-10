pipeline {
    agent any
    tools { 
        maven 'Maven 3.3.9' 
    }
    stages {
        stage ('Initialize') {
            steps {
                bat echo "M2_HOME = ${M2_HOME}"
            }
        }
        stage('SCM') {
            checkout scm
        }
            withSonarQubeEnv() {
                bat "mvn clean verify sonar:sonar"
            }
        }
        stage ('Build') {
            steps {
                echo 'This is a minimal pipeline.'
            }
        }
    }
}
