Run docker-compose.yml in /docker-dev folder to create DB image.

Run app to creat DB schema.

Run .sql files in /resources/sql to initialize records for "department" and "user" tables. 

Quick implementation of Authorization(one in memory user), to access resources execute the following request:

curl -X POST \
'http://localhost:8080/access_token?grant_type=password' \
-H 'Authorization: Basic d2ViX2NsaWVudDp0UnlfdG8tR1VFU1MyM0Aj' \
-H 'Content-Type: application/x-www-form-urlencoded' \
-d 'username=user&password=user' 