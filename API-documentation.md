# Server is running on : 


Locally (if you did the set up and ran it locally with maven) : 
```http://localhost:8282```


## POST /images

Upload one or more images to the server.

201 Created

Upload one or more images to the server.


IMPORTANT, BEFORE THE QUERY :
[Import the following file in postman for a POST query](https://github.com/manefz/image_repo/blob/master/POST-example.postman_collection.json)

After that, go to body -> form-data -> value column : Select files -> add an image! 
You can upload multiple files by duplicating the first line and simply changing the upload file.


[POST query example with the deployed app url](https://github.com/manefz/image_repo/blob/master/queries_example/POST.png)

### GET /images/{imageName}

Download a sinlge image with the specified name. 
Returns the image data in bytes (Postman shows a preview of the image).

200 OK 

[GET query example with the deployed app url](https://github.com/manefz/image_repo/blob/master/queries_example/GET.png)


404 NOT FOUND : 

When the given name doesnt exist.

[NOT_FOUND query example with the deployed app url](https://github.com/manefz/image_repo/blob/master/queries_example/NOT_FOUND.png)
### GET /images/{id}


## V0 

## POST /images

Upload one or more images to the server.

201 Created

Upload one or more images to the server.


IMPORTANT, BEFORE THE QUERY :
[Import the following file in postman for a POST query](https://github.com/manefz/image_repo/blob/master/POST-example.postman_collection.json)

After that, go to body -> form-data -> value column : Select files -> add an image! 
You can upload multiple files by duplicating the first line and simply changing the upload file.


[POST query example](https://github.com/manefz/image_repo/blob/master/queries_example/v0/POST.png)


## GET /images
Download a sinlge image with the specified id. 
Returns the image data in bytes (Postman shows a preview of the image).

200 OK 

[GET query example](https://github.com/manefz/image_repo/blob/master/queries_example/v0/GET.png)


404 NOT FOUND : 

When the given id doesnt exist.

[NOT_FOUND query example](https://github.com/manefz/image_repo/blob/master/queries_example/v0/NOT_FOUND.png)

