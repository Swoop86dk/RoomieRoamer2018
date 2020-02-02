/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import java.io.IOException;
import net.minidev.json.parser.ParseException;

/**
 *
 * @author Tarllark
 */
public class test
{
    public static void main(String[] args) throws IOException, IOException, ParseException
    {
        externalData test = new externalData();
        System.out.println(test.getAll());
    }
}
