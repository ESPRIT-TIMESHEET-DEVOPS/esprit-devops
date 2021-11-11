pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-login')
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }
    stages {
        stage('Test') {
            steps{
                withMaven {
                    sh "mvn clean test"
                }
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
            success{
                discordSend description: "${IMAGE}:${VERSION} Pipeline Build", footer: "Build successful", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discord.com/api/webhooks/908327603428028447/WqNlqvRQhP2caIzVFKOoItlXZa7yJIXiQUVjrKIfGRfhU_W184n_zfm2uZGQZOeE1Oba"
            }
        }
        success{
            discordSend description: "${IMAGE}:${VERSION} Pipeline Build", footer: "Build successful", link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME, webhookURL: "https://discord.com/api/webhooks/908327603428028447/WqNlqvRQhP2caIzVFKOoItlXZa7yJIXiQUVjrKIfGRfhU_W184n_zfm2uZGQZOeE1Oba"
        }
    }
}