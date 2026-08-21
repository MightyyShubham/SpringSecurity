use in this way

you need to login into application by credentials present in DB

http://localhost:8080/auth/login POST
{
"email":"Shubham@gmail.com",
"password":"1234"
}

you will get auth token , use that token to make futher api call

http://localhost:8080/auth/signup POST
{
"name":"shubham1",
"email":"Shubham1@gmail.com",
"password":"1234"
}
response
{
"id": 3,
"name": "shubham123",
"password": "$2a$10$FrhU.I04wQjRtrZh8LyWSOTzzQoAd1K3YoD7s3t2W.ro/73E1qWBO"
}

http://localhost:8080/posts/1 Get
in Authorization -> type Bearer -> past the token that you get at login 

