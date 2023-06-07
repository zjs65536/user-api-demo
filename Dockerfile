FROM tomcat:9.0.75-jdk11-corretto-al2
COPY ${WORKSPACE}/target/user-api-demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
