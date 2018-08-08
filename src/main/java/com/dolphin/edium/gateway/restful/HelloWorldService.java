package com.dolphin.edium.gateway.restful;

import com.dolphin.edium.gateway.AuthenClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("helloWorld")
public class HelloWorldService {

    @GET
    @Path("{name}")
    public String getMessage(@PathParam("name") String name) throws InterruptedException {

        AuthenClient client = new AuthenClient("localhost", 50051);
        try {
            /* Access a service running on the local machine on port 50051 */
            return client.greet(name);
        } finally {
            client.shutdown();
        }
    }

    @GET
    @Path("hehe")
    public String getMessage() throws InterruptedException {
        return "hehe";
    }
}
