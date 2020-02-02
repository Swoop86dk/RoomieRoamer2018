/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.User;
import entity.UserDTO;
import entity.UserFacade;
import java.sql.SQLException;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Martin
 */
@Path("User")
public class UserEndpoint
{

    @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;

    public UserEndpoint()
    {
    }
    UserFacade uf = new UserFacade();
    JSONParser parser = new JSONParser();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("/poma")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getPomaByID(@PathParam("id") Integer id) {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return Response.ok().entity((uf.getPomaAsJSON(Integer.parseInt(up.getId())))).build();
    }
    
    @GET
    @Path("/uid")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "user"})
    public Response getLoggedInID() {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return Response.ok().entity((gson.toJson(uf.getUser(Integer.parseInt(up.getId()))))).build();
    }
    
    @GET
    @Path("/ur")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "user"})
    public Response getLoggedInRole() {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return Response.ok().entity((gson.toJson(uf.getUser(Integer.parseInt(up.getId())).getRoleList()))).build();
    }

    @GET
    @Path("/usermatches")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getUserMatches() {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return Response.ok().entity((gson.toJson(uf.getMatchedUsers(Integer.parseInt(up.getId()))))).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByID(@PathParam("id") Integer id) {
        System.out.println(gson.toJson(uf.getUserDTO(id)));
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id))).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    @RolesAllowed("user")
    public String getFromUser()
    {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return "\"Hello from USER: " + up.getId() + "\"";
    }
    
    @GET
    @Path("/{id}/desc")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserDescByID(@PathParam("id") Integer id) {
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id).getDesc())).build();
    }
    
    @GET
    @Path("/{id}/interest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInterestByID(@PathParam("id") Integer id) {
        return Response.ok().entity(gson.toJson(uf.getUserDTO(id).getInterests())).build();
    }
    
    @GET
    @Path("/allasmap")
    @Produces(MediaType.APPLICATION_JSON)

    public Response getUsers() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(uf.getUsers()).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUser(String json) throws ParseException {
        return Response.ok().entity(gson.toJson(uf.addUser((JSONObject) parser.parse(json)))).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserDesc( String content, @PathParam("id") int id)  {
        User newUser = gson.fromJson(content, User.class);
        User savedUser = uf.getUseredit(id);
        if(newUser.getDesc()!=null)
            savedUser.setDesc(newUser.getDesc());
        UserDTO uDTO = uf.editUser(savedUser);
        return Response.ok().entity(gson.toJson(uDTO)).build();
    }
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id")int id) {
        User us = uf.deleteUser(id);
        return Response.ok().entity(gson.toJson(us)).build();
    }
    
    @PUT
    @Path("/like/{id}/{idPressed}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLiked(@PathParam("id")int id, @PathParam("idPressed") int idPressed) throws Exception{
        UserDTO uDTO = uf.assignUserLike(id, idPressed);
        return Response.ok().entity(gson.toJson(uDTO)).build();
    }
    @PUT
    @Path("/ignore/{id}/{idPressed}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addIgnore(@PathParam("id")int id, @PathParam("idPressed") int idPressed) throws Exception{
        UserDTO uDTO = uf.assignUserIgnore(id, idPressed);
        return Response.ok().entity(gson.toJson(uDTO)).build();
    }
}