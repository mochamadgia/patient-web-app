# patient-web-app
## Requirements 
- npm (^16.19.1)
- [Angular CLI](https://github.com/angular/angular-cli)
- [JDK 17]([(https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- [Maven 3](https://maven.apache.org)

- On project checkout run below commands:
```shell
cd frontend
npm install
```
- Running project:
```shell
cd frontend/node_modules/.bin/
ng build --watch --configuration development --base-href /patient-fe/
mvn clean install spring-boot:run
```
