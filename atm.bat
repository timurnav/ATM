call mvn -B -s settings.xml -DskipTests=true clean install
call java -Dspring.profiles.active="postgres" -DDATABASE_URL="postgres://user:password@localhost:5432/atm" -jar target/dependency/webapp-runner.jar target/*.war
