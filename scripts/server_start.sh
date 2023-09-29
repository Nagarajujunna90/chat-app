#!/usr/bin/env bash
cd /home/ec2-user/chat-app
sudo mvn clean install
sudo java -jar target/chat-app-0.0.1-SNAPSHOT.jar \
    *.jar > /dev/null 2> /dev/null < /dev/null &

#sudo unzip spring-build-demo.zip
#mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=9091' > /dev/null 2> /dev/null < /dev/null &
#sudo java -jar -Dserver.port=9091 \
#    *.jar > /dev/null 2> /dev/null < /dev/null &

#sudo kill $(sudo lsof -t -i:9091)
#sudo java -jar chat-app-1.0.0.jar \
#    *.jar > /dev/null 2> /dev/null < /dev/null &