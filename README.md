# scratch-robot
A robot that uses two 28BYJ-48 stepper motors for navigation and that connects to a scratch programming environment to receive movement commands.

sudo /opt/apache-maven-3.3.3/bin/mvn install exec:java -Dscratch.host=192.168.178.30 -Dspring.profiles.active=simulation
sudo /opt/apache-maven-3.3.3/bin/mvn install exec:java -Dscratch.host=192.168.178.30 -Dspring.profiles.active=realMotor
