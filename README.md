# Auth0 Java EE 8 Security API Example

## Overview

This example shows how to integrate [Auth0 Authentication API](https://auth0.com/docs/api/authentication#introduction) in your API developed with Java EE 8 following [Java EE 8 Security API](https://javaee.github.io/security-spec/) (JSR 375).

The example uses a [Machine to Machine Auth0 application](https://auth0.com/docs/applications/machine-to-machine) to request tokens on behalf of your API, and it also shows how to valdiate tokens sent by your API callers. 

## Dependencies

- Auth0 account (create one if you don't have [here](https://auth0.com/)).
- Auth0 API (create one using Auth0 Manager, choose RS256 as Signing Algorithm)
- Machine to machine Auth0 Application (create one using Auth0 Manager and choose API created above)
- Java EE Container ([Payara Micro](https://www.payara.fish/payara_micro) recommended)
- A RESTful client or any other tool to call RESTful services ([curl](https://curl.haxx.se/) recommended)

## Running the example 

Assuming you have already checked out the repository example, the first step is to open the file 'src/main/resources/application.properties' and replace the values below

```
{YOUR_ENDPOINT}: go to your application created and copy the value in Domain (e.g. https://myapp.auht0.com)
{YOUR_CLIENT_ID}: you can also find it in your application settings tab
{YOUR_CLIENT_SECRET}: as above, you can find it in your application settings tab within Auth0
{YOUR_AUDIENCE}: find it in your APIs dashboard page, it is shown as API Audience
```

Using your favourite Unix shell change the directory to where you have downlaoded the repository and run the command below to build the war package.

```
./gradlew war
```

If you use Windows, just run 'gradlew.bat war'.

Now it is time to deploy and run the War package using Payara Micro as follows, substituing <path_to_payara_micro> by the abolute path where you have installed Payara Micro. Note your war filename could be different, just check before you run the war file created in the 'libs' folder.

```
java -jar <path_to_payara_micro>/payara-micro-5.183.jar --deploy ./build/libs/auth0-java-8-security-api.war
```

After fiew seonds Payara Micro shows you the war has been deployed and it is ready to be used. 

## Playing with the example

Once the war has started, you can request a token using curl

```
curl -i -H "Content-Type: application/json" -X POST -d '{"username": "user1", "password": "mypassword"}' http://localhost:8080/auth0-java-8-security-api.war/authenticate
```

Authenticate service requests a token to Auth0 and then it sends the token back to the caller. When the curl command finishes, it is shown in your shell Authorization header with the token starting with Bearer.

Using the token received, it is possible to call to the other service in the example using again curl as below.

```
curl -i -H "Content-Type: application/json" -H "Authorization: Bearer <token>" -L http://localhost:8080/auth0-java-8-security-api.war
```

Remember to substitute <token> by the token you have received. Curl output in this case is a list of books hardcoded in the service.

```
[{"author":"William Smile","date":"01/03/2017","description":"Learn how to secure your APIs with Auth0.","title":"Securing your API with Auth0"},{"author":"George Keen","date":"08/09/2018","description":"Secure is everywhere and JWT plays a big game in API security.","title":"Understanding JSON Web Tokens"}]
```

If you would use an expired or non valid token, curl would show an answer like the one below.

```
<html><head></head><body><h1>HTTP Status 401 - Unauthorized</h1><hr/><p><b>type</b> Status report</p><p><b>message</b>Unauthorized</p><p><b>description</b>This request requires HTTP authentication.</p><hr/><h3>Payara Micro #badassfish</h3></body></html>
```
