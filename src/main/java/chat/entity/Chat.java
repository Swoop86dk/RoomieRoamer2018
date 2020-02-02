/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.entity;

import entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name="chat")
public class Chat implements Serializable
{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_id")
    private Integer id;
    
    @OneToOne
    private User user1;
    
    @OneToOne
    private User user2;
    @Transient
    private List<Message> history = new ArrayList();
    @Transient
    private Message lastMSG;
    @Transient
    private Message newMSG;

    public Chat()
    {
    }
    
    public Chat(User owner, User partner)
    {
        this.user1 = owner;
        this.user2 = partner;
    }
    
    public Chat(Integer id, User owner, User partner)
    {
        this.id = id;
        this.user1 = owner;
        this.user2 = partner;
    }

    public List<Message> getHistory()
    {
        return history;
    }

    public void setHistory(List<Message> history)
    {
        this.history = history;
    }

    public void addHistory(Message msg)
    {
        this.history.add(msg);
    }

    public Message getLastMSG()
    {
        return lastMSG;
    }

    public void setLastMSG(Message lastMSG)
    {
        this.lastMSG = lastMSG;
    }

    public Message getNewMSG()
    {
        return newMSG;
    }

    public void setNewMSG(Message newMSG)
    {
        this.newMSG = newMSG;
    }

    public Integer getId()
    {
        return id;
    }
    
    
}
