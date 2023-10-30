package com.example.restproject;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/bicycles")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response bicycles() {

        List<Bicycle> bikeList = new ArrayList<Bicycle>();
        try (Connection conn = DatabaseConnection.getBicyclesDatabaseConnection()){
            String SQL = "SELECT name, speeds from bicycles";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while( rs.next()) {
                Bicycle bike = new Bicycle( rs.getString("name"), rs.getInt("speeds"));
                bikeList.add(bike);

            }
        }
        catch( SQLException ex) {
            ex.printStackTrace();
        }

        GenericEntity<List<Bicycle>> entityList = new GenericEntity<List<Bicycle>>(bikeList) {};
        return Response.ok(entityList).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBicycle(Bicycle bike) {
        try (Connection conn = DatabaseConnection.getBicyclesDatabaseConnection()){
            String SQL = "INSERT INTO bicycles (name, speeds) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, bike.name);
            stmt.setInt(2, bike.speeds);
            stmt.executeQuery();
        }
        catch( SQLException ex) {
            ex.printStackTrace();
            return Response.status(400, "Unable to perform insert" + ex.getMessage()).build();
        }

        return Response.ok(bike).build();
    }

    @DELETE
    @Path("/{bikeId}")
    public Response deleteBicycle(@PathParam("bikeId") int bikeId) {
        return Response.status(200, Integer.toString(bikeId)).build();
    }

}
