### Design

From the paper: *We selected WildFly 11.0.0.Final as application server and used the same WildFly image for docker and RKT, which was created through a dockerfile, pulled from the public repository jboss/wildfly... We then selected a RESTful service for deployment on WildFly, which we opted to be our adaptation of WSTest, ... which we now converted to a RESTful service that now returns JSON responses 
instead of SOAP (code is publicly available at [\[21\]](https://eden.dei.uc.pt/~cnl/papers/2018-edcc.zip)).*

The source code then includes a `Dockerfile` which can be used to build the server. However, the `Dockerfile` does not full from 11.0 of WildFly that their pper say they use and it must be modified.

#### Building the Server

1. Go to `docker/wstest/container`
2. Run `sudo docker build . -t wsserver`

#### Running the Server

Run the server with `sudo docker run -p 8080:8080 wsserver`. This exposes the normal HTTP REST server on port `8080`. You could also expose the HTTPS server on `8443` if desired. You can test your conenction by running `curl 127.0.0.1:8080/server-example/rest/wstest/echoVoid` which should return an empty response (without raising server errors), and then run ` curl -X POST --header "Content-Type: application/json" --data 'hi' 127.0.0.1:8080/server-example/rest/wstest/echoString`, which should return the string 'hi'.

### Running the Experiments

1. Create the docker server image and name it `wsserver`
2. Run `./wstest/dockerTests.sh --useNewContainer`. The flag ensures the script will stop and create new docker containers between each type of test, while without the flag it will reuse the same container and any performance degradation will stack.
3. Experiment output will be found at `wstest/client_emulator/*.csv`