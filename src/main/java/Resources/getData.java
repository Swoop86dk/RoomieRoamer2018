/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import net.minidev.json.*;

/**
 *
 * @author Tarllark
 */
public class getData implements Callable
{
    
    String api;
    public getData(String api)
    {
        this.api = api;
    }
    @Override
    public String call() throws IOException
    {
        String res = "";
        try {
            res = getSwapiData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return getSwapiData();
    }

    public String getSwapiData() throws MalformedURLException, IOException
    {
        externalData ED = new externalData();
        URL url = new URL(this.api);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
            System.out.println(jsonStr);
        }
        scan.close();
        return jsonStr;
    }

}
