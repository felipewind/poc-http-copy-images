# poc-http-copy-images

Let's imagine a hypothetical situation where the frontend can't access any URL of the internet, it can only access the URLs of its backend server.

In one project, the backend application will consume some APIs that will return the URL of images and these images must be rendered in the frontend.

As these URL images are from the open internet, the frontend is not able to access them directly.

There are some ways of solving this problem.

Considering that the backend server is able to access these URL images from the internet, I made this POC offering two alternatives to solve this problem.

The backend application will access these URL images, copy them, and provide them to the frontend in two ways:
- One raw copy from the Response of the resource;
- Creating one json object with one property containing one string with the images encoded in Base64 and another property with the media type.

This way, the frontend can choose between accessing the raw copy or the Base64 version of the image.

The backend application is written in Quarkus and the frontend in Angular.

To proof that this work, in this POC Angular will have one input where the user can type the URL of the internet image to be copied. This URL will be send to both Quarkus endpoints and with the responses, two images will be shown to the user, one from the endpoint which just copied the Response and other from the endpoint which returned the Base64 format.

To simplify the tests, there's one Quarkus mock-server that provides images in this formats: png, svg+xml and jpeg.


# mock server

Images mocked by the Mock Server

http://localhost:8090/image/earth.png

http://localhost:8090/image/moon.jpg

http://localhost:8090/image/solarsystem.svg



