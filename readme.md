# Revature - Project 2 - Social Media Website

## See the readme files within /java and /Angular for specific information on those implementations

## To run:

*You'll likely be able to skip these first two steps if you've already installed ojdbc8 locally for another project*
1. Download ojdbc8.jar onto your computer from the [Oracle website](http://www.oracle.com/technetwork/database/features/jdbc/jdbc-ucp-122-3110062.html)
2. To Maven install the jdbc driver so it works for this project:
```
mvn install:install-file -Dfile={Path/to/your/ojdbc8.jar} -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
```

3. Open the /java directory in STS
4. Import it as an existing Maven project
5. Run on Tomcat Server

6. Open the /Angular directory in VS Code
7. Run "npm install" to get node_modules and everything else needed to run the angular
8. Run using "ng serve"

## Default values to use
- User (id 2):
	- username: joe
	- password: @@
	- name: Sam Mendez
	- email: sammymendino@gmail.com
	- email password (if you want to check that emails are sent): 3mailpass
- User (id 21):
	- username: jim
	- password: @!
	- name: Jim Jam
	- email: non-existant email (stored as jimjam@email.com, not an actual email)