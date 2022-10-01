# Spotify-Service 

## Postman collection

the api requests collection from postman can be found here: [Postman collection](https://www.getpostman.com/collections/7acc1c05dd60762ab76f)

## Run project

You need run a mysql db in localhost with the following configuration:
* User: root
* No Password
* table name: spotify_service

If you want run the db on docker you can use the following command:
```
sudo docker run -d --name spotify-service -p 3307:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=1 mysql
```
and then create the table.

To run the spring project you only need run:
```
mvn clean install
```
and then you can run 
