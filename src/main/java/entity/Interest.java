/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name = "interests")
public class Interest implements Serializable
{
private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "interest_name", length = 255)
    private String interestName;
    
    @ManyToMany(mappedBy = "interests")
    private List<User> userList;

    public Interest() {
    }

    public Interest(String interestName) {
        this.interestName = interestName;
    }

    public String getInterestName()
    {
        return interestName;
    }

    public void setInterestName(String interestName)
    {
        this.interestName = interestName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
}
