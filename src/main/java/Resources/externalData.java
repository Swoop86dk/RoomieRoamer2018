/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 *
 * @author Tarllark
 */
public class externalData
{
    
    public String getAll() throws IOException, ParseException
    {
        JSONArray JA = new JSONArray();
        JSONParser parser = new JSONParser(); 

        getData tc1 = new getData("https://swapi.co/api/people/1/");
        JA.add((JSONObject) parser.parse(tc1.call()));
        getData tc2 = new getData("https://swapi.co/api/people/2/");
        JA.add((JSONObject) parser.parse(tc2.call()));
        getData tc3 = new getData("https://swapi.co/api/people/3/");
        JA.add((JSONObject) parser.parse(tc3.call()));
        getData tc4 = new getData("https://swapi.co/api/people/4/");
        JA.add((JSONObject) parser.parse(tc4.call()));
        getData tc5 = new getData("https://swapi.co/api/people/5/");
        JA.add((JSONObject) parser.parse(tc5.call()));
        
        JSONObject res = new JSONObject();
        res.put("results", JA);
        return res.toJSONString();
    }


    

}
