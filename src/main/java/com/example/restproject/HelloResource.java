package com.example.restproject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/bicycles")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response bicycles() {
        Bicycle blueBike = new Bicycle("Blue Bike", 10);
        Bicycle redBike = new Bicycle("Red Bike", 12);
        List<Bicycle> bikeList = new ArrayList<Bicycle>();

        GenericEntity<List<Bicycle>> entityList = new GenericEntity<List<Bicycle>>(bikeList) {};
        bikeList.add(blueBike);
        bikeList.add(redBike);
        return Response.ok(entityList).build();
    }
}
