node {
//    OK
    stage('SCM') {
        checkout scm
    }
    stage('SonarQube Analysis') {
        def mvn = tool 'Default Maven';
        withSonarQubeEnv() {
            bat "${mvn}/bin/mvn clean verify sonar:sonar"
        }
    }
}