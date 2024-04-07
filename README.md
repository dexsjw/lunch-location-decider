# Lunch-Location-Decider

## Background
There is frequently a need for teams to collectively decide on a location to head to for lunch. While each team member has an idea in mind, not everyone gets heard in the commotion and much time is spent to arrive at what may as well be a random choice.

## Application
1. A user can initiate a session and invite others to join it.
2. Other users who have joined the session may submit a restaurant of their choice.
3. All users in the session are able to see restaurants that others have submitted.
4. The user who initiated the session is able to end the session.
    - At the end of a session, a restaurant is randomly picked from all submitted restaurants. All users in the session are then able to see the picked restaurant.
    - A user should not be able to join a session that has already ended.

### Design Considerations
* This app is meant to be quick and easy to use.
* No login is required, just sharing of room ID.
* If room ID is lost, users can just create a new lunch session room again.
* Including user login can be considered for future implementations.

## Steps to use this app
Note: The SQL statements provided in server/lunch-location-decider/lunch_location_decider.sql is meant to be used with MS SQL Server. If you are using other databases, you may set up accordingly with reference to provided SQL statements.

Assumption: If you are using MS SQL Server for database, this readme / guide does not walk you through the required steps to set up a working database.
> You may refer to https://learn.microsoft.com/en-us/sql/sql-server/ for the latest version of guides and tutorials on how to set up the database.

1. Clone this repository into your local workspace.
2. To start up the backend, run the command:
    - mvn spring-boot:run
    - or
    - mvn clean spring-boot:run
3. To start up the frontend, run the command:
    - ng serve
4. Go to http://localhost:4200/ and you will be on the landing page.
5. Explore and enjoy using the app!