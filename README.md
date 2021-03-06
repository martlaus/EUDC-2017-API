# EUDC API

# Installation

Check out the project and build the application using **maven** and run the eudcApi.jar at **/target** folder.

	mvn clean package

# Start application

By default, application will run at port 7070. The rest API is available under context path **rest/**. After starting application you can access it at:

	http://localhost:7070/rest/path/to/resource

## Default configuration

	java -jar eudcApi.jar
	
## Custom configuration

The custom configuration file can be placed anywhere visible to the application and may have any name.

	java -jar -Dconfig="/path/to/custom.properties" eudcApi.jar

# Stop application

To stop the application just run the command:

	java -jar eudcApi.jar stop
	
If you started the application using a **custom configuration** file, use the same configuration to stop the application.

	java -jar -Dconfig="/path/to/custom.properties" eudcApi.jar stop

# Configuration

The application can be configured using a custom properties file. It can be placed anywhere visible to the application and may have any name.

The available properties are:

### Database

The site uses **MySql** database.
In my.ini or my.cnf under [mysqld] add: character-set-server=utf8

* **db.url** - the database url
* **db.username** - the database username
* **db.password** - the database password for given username

### Server

* **server.port** - the port that server starts.
* **command.listener.port** - the port used to listening for commands to be executed on server. Currently only shutdown command is available.
