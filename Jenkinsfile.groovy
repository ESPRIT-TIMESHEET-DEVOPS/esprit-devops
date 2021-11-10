pipeline {
    agent any
    stages {
        stage('clone and clean repo') {
            steps {
                cleanWs()
                bat "git clone -b master https://github.com/sadek-selmi/Devops.git"
                dir("DevOps") {
                    bat "mvn clean "
                }
            }
        }
        stage('Testing') {
            steps {
                dir("DevOps") {
                    bat "mvn test"
                }
            }
        }
        stage('Deploy') {
            steps {
                dir("DevOps") {
                    bat "mvn package"
                    bat "mvn sonar:sonar"
                    bat "mvn deploy"
                }
            }
        }
        stage('Docker') {
            steps {
                dir("DevOps") {
                    bat "mvn spring-boot:build-image"
                    bat "docker build -t dev ."
                    bat "docker tag dev  sadekselmi/devop:1"
                    bat "docker login -u sadekselmi -p admin1234"
                    bat "docker push  sadekselmi/devop:1"
                }
            }
        }
        stage('email') {
            steps {
                mail bcc: '', body: 'Hello , this is a Pipeline DevOps', cc: '', from: '', replyTo: '', subject: 'DevOps Pipeline', to: 'sadek.selmi@esprit.tn'
            }
        }
    }
}