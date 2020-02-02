/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import security.UserPrincipal;
/**
 * REST Web Service
 *
 * @author Tarllark
 */
@Path("chat")
public class ChatEndpoint
{

    @Context
    private UriInfo context;
    
    @Context
    SecurityContext securityContext;

    /**
     * Creates a new instance of ChatEndpoint
     */
    public ChatEndpoint()
    {
    }
    
    ChatFacade cf = new ChatFacade();
    JSONParser parser = new JSONParser();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("/{id}/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatHistoryByID(@PathParam("id") Integer id) {
        return Response.ok().entity(cf.getHistory(id)).build();
    }

    @GET
    @Path("/{id}/chat")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getChatSessionByID(@PathParam("id") Integer id) {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        return Response.ok().entity(cf.getChatSession(Integer.parseInt(up.getId()), id)).build();
    }
    
    @GET
    @Path("/{id}/newest")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response getNewMsgByID(@PathParam("id") Integer id) {
        return Response.ok().entity(cf.getNewMessage(id)).build();
    }
    
    @POST
    @Path("/{id}/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    public Response postCreateMsg(String json, @PathParam("id") Integer id) throws ParseException {
        UserPrincipal up = (UserPrincipal) securityContext.getUserPrincipal();
        int userId = Integer.parseInt(up.getId());
        return Response.ok().entity(gson.toJson(cf.createMSG((JSONObject) parser.parse(json), userId))).build();
    }
}
