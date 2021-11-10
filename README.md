*** TEST
Tests fail on github actions because it fails to load local DB context, but tests pass on dev 
![Passing tests](screenshots/img.png)

SonarLint Issues 
Controllers :
![SonarLint suggestions for MissionController](screenshots/sonarlint-mission-controller.png)
Tests :
![SonarLint suggestions for MissionTest](screenshots/mission-test-sonarlint-issues.png)


Sonar cube command : 
mvn clean verify sonar:sonar \
-Dsonar.projectKey=Devops-assignment \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=691ea91b53a1805f07ba43958891da78cf9baec1

Sonar cube analysis : 
![Sonarcube global metrics](screenshots/sonarcube1.png)

Bugs :
![Sonarcube bugs](screenshots/sonarcube2.png)

Vulnerabilities :
![Sonarcube vulnerabilities](screenshots/sonarcube3.png)

Code smells :
![img.png](screenshots/sonarcube4.png)

Nexus deploy :
mvn clean package deploy:deploy-file -DgroupId=tn.esprit.spring e -DartifactId=timesheet1 -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/timesheet-1.0jar
