/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
public class InterestDTO
{
    private String interestName;
    private List<User> userList = new ArrayList();
    
    public InterestDTO(Interest i) {
        this.interestName = i.getInterestName();
        this.userList = i.getUserList();
    }

    public String getInterestName()
    {
        return interestName;
    }

    public void setInterestName(String interestName)
    {
        this.interestName = interestName;
    }

    public List<User> getUserList()
    {
        return userList;
    }

    public void setUserList(List<User> userList)
    {
        this.userList = userList;
    }
    
}
