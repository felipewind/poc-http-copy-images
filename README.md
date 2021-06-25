# poc-http-copy-images

The objective of this POC is to create one workaround for one specific situation where the frontend application doesn't have access the internet. It can only access the backend server. The issue is that the backend application will receive some images from applications that are outside the company and this images must be shown in the frontend. 

The idea is the backend application to access these images URLS, copy them, and provide them to the frontend.

In this POC, this copy is done in two ways:
- One raw copy from the Response of the resource;
- Creating one json object with one property containing one string with the images encoded in Base64 and another property with the media type.

This way, the frontend can choose between accessing the raw copy or the Base64 version of the image.

The backend application is written in Quarkus and the frontend in Angular.

Quarkus will have two endpoints that receive one URL of any image. One will respond with the raw copy and other with the Base64.

Angular will have one input where the user can type the URL of the image to be copied. This URL will be send to both Quarkus endpoints and with the responses, two images will be shown to the user, one from the endpoint which just copy the Response and other from the endpoint which return the Base64 format.

To simplify the tests, there's one Quarkus mock-server that provides images in this formats: png, svg+xml and jpeg.




