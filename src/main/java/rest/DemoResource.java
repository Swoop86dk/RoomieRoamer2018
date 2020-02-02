package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import Resources.*;
import entity.User;
import entity.UserFacade;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;

/**
 * REST Web Service
 *
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource
{
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from USER: " + user + "\"";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin()
    {
        String user = securityContext.getUserPrincipal().getName();
        return "\"Hello from ADMIN" + user + "\"";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    //@RolesAllowed({"user","admin"})
    public String getAll() throws IOException, ParseException
    {
        return new externalData().getAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dummy/{offset}")
    public String getDummyData(@PathParam("offset") Integer offset) throws SQLException, ClassNotFoundException
    {
        return new Resources.DBAccess.dbData().getData(offset);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("test")
    public String getTestData() throws SQLException, ClassNotFoundException
    {
        return new UserFacade().getTest().getQuestionnaire().getQuestionnaireArea().getCityInfoName();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("testPoma")
    public String getTestPoma() throws SQLException, ClassNotFoundException
    {
        return new UserFacade().getPomaAsString();
    }

   
}
