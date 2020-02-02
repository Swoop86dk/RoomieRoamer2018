/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources.DBAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 *
 * @author Tarllark
 */
public class dbData
{

    public String getData(int offset) throws SQLException, ClassNotFoundException
    {
        JSONArray JA = new JSONArray();
        Connection con = Connector.connection();
        String SQL = "select * from CA3.dummy  ORDER BY id ASC LIMIT 20 OFFSET ?;";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setInt(1, (offset));
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            JSONObject item = new JSONObject();
            item.put("ID", rs.getInt("id"));
            item.put("Desc", rs.getString("desc"));
            JA.add(item);
        }
        JSONObject res = new JSONObject();
        res.put("Result", JA);
        return res.toJSONString();
    }
}
