## Application and features 

This Project built in Java is a simple REST API that provides the feature of an image repository.
For the moment two versions exist : v1 which stores the images on AWS S3

These versions are accessible through the two branches available on this repository. 

Specifically this API offers the following features: 

- Upload one or more image
- Download an image

### Routes 

Currently two routes are supported : 

```http://localhost:8282/``` and
```http://localhost:8282/images```

Please consult the [api documentation](https://github.com/manefz/image_repo/blob/master/API-documentation.md) 

### Architecture 

This application has been built around the hexagonal architecture pattern. There are 4 layers : interface layer (REST interface),
service layer,domain layer and persistence layer. This architecture presents the following pros: 

- Easily change the external dependencies (cloud storage)
- Easier to maintain
- Easier to change technologies
- Easier to test



## Usage of Deployed app

The app is running on https://img-repo-manefz.herokuapp.com/. 
Please consult the [api documentation](https://github.com/manefz/image_repo/blob/master/API-documentation.md) 

## Usage of Local app 

Before running the app locally a few steps are required to setup S3 (v1).

### Requirements to execute 

- Java 1.8.*
- Maven 3.8.*


An AWS S3 account, with an IAM user. You need to save your two keys ( aws_access_key_id, aws_secret_access_key)
in a file with the following name : s3_config.properties

Refer to the [provided example](https://github.com/manefz/image_repo/blob/master/resources/s3_config_properties.example) for the properties file


The file needs to be placed in a folder "resources" as illustrated in the following example : 
```
image-repo (project root)
└───resources
    │   s3_config.properpeties
    ...
```

### Software required

- Any REST client (Postman, Insomnia etc.) 

This API was developed in Java with Jersey(implementation of JAX-RS) and Jetty (HTTP Container). 
Note : JAX-RS stands for Jakarta RESTful web services.

### Commands to run

Once all the requirements are met simply run the following commands.

1. Clone the repo

2. In the root directory, run 
```
mvn clean install
```

3. In the root directory, run 
```
mvn clean compile exec:java
```

Now the project should be up and running on

```http://localhost:8282/```

To run the tests, run
```
mvn test
```

Please consult the [api documentation](https://github.com/manefz/image_repo/blob/master/API-documentation.md) 



### Possible improvements/New features

- Implement an authentification system
- Add other features (buy and sell images)
- Throttle requests and limit amount of uploads per user (S3 free tier is not unlimited!)
- More unit tests
- Implement a basic UI to make this API easier to test manually
