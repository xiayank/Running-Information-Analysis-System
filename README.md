Running Information Analysis Service 
===
CS504_Homework_1
==
##Download code 


    git https://github.com/xiayank/RunningInformationAnalysis.git
##Start up the database

Go to project folder, start up Mysql docker.

    mysql --host=127.0.0.1 --port=3306 --user=root --password=root 
Look up existed database.

    show database;

If there is no database called `private`, then create one.

    create database private;
Quit the mysql.

    quit;
### Start up Spring Application
Build maven application.

    mvn clean install
Run the fat jar.

    java -jar target/RunningInformationAnalysisService-1.0.0.0-SNAPSHOT.jar

### Test API
#### Import the dummy data using _create_ API
Use `Postman`  to make a __POST__  request with URL:

    http://localhost:9000/create
Request Body is in the file `dummaydata.txt`.  
If the response is `200 OK`, meaning that this API works.

#### Return all the info using _findAllByOrder_ API
Use `Postman` to make a __GET__ request with URL:

    http://localhost:9000/findAllByOrder
It should return all 7 JSON data with required format. And default page = 0, size = 2. 


#### Return all the info great than a heart rate using _heartRateGreatThan_ API
Use `Postman` to make a __GET__ request with URL:

    http://localhost:9000/heartRateGreatThan/{a exist heart rate}

#### Return info by a heart rate using _heartRateGreatThan_ API
Use `Postman` to make a __GET__ request with URL:

    http://localhost:9000/heartRate/{a exist heart rate}
#### Delete all info by _purge_ API
Use `Postman` to make a  __DELETE__ request with URL:

        http://localhost:9000/purge
If the response is `200 OK`, meanning that all the info have been deleted.
