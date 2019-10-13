# authorization
MyCheck Exam.

By Kirill Kazoolin.
https://github.com/kzkirill/authorization


1 Technology stack.
The technology used is Spring Boot, In-Memory H2 DB, Spring MVC for Rest, javax.crypto.spec for password encryption, io.jsonwebtoken for security token.
2 DB structure.
1 Table.
Name : User.
FIELD  
TYPE  
NULL  
KEY  
DEFAULT  
EMAIL
VARCHAR(255)
NO
PRI
NULL
KEY_WORD
VARCHAR(255)
YES

NULL
NAME
VARCHAR(255)
YES

NULL
PASSWORD
VARCHAR(255)
YES

NULL

DDL scripts are in the project under resources/db/ddl.
3 API.
The application has 3 Rest URLs.
3.1 User registration.
POST localhost:8080/users
Adds a new user to the application. Should provide User in the request body.

Example:
curl -X POST localhost:8080/users -H 'Content-type:application/json' -d '{"name": "User 1","email":"user1@gmail.com","password":"user1Gh&65","keyWord":""}'

Returns the created user.
3.2 User login.
POST localhost:8080/login
After a user is added this can be used for login. Should provide User in the request body with email and password.
Example:
curl -X POST localhost:8080/login -H 'Content-type:application/json' -d '{"email":"user1@gmail.com","password":"user1Gh&65"}'

Returns the security token of type JWT which is used to retrieve data. Token expires in 60 seconds.

Exceptions:
Returns “User not found “ message if the user is not found in the DB.
Returns “Login exception” message if the credentials are wrong.
3.3 User name retrieval.
localhost:8080/name
After the token is obtained this can be used to retrieve the user name. Should provide a SecureRequest object in the request body. The structure of the object is:
{“login”:”email”,
  “token”:”token value”}
The token value is obtained by the User Login.

Example:
curl -X POST localhost:8080/name -H 'Content-type:application/json' -d '{"login":"user1@gmail.com","token":"token value obtain in User Login"}'

Returns user’s name from the DB.

Exceptions:
Returns message "You are not authorized" if token is changed or expired.
