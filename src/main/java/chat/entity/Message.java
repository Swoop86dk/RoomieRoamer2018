/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.entity;

import entity.Budget;
import entity.User;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name="message")
public class Message implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg", length = 255)
    private String msg;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sender")
    private User sender;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="chat")
    private Chat chat;

    public Message()
    {
    }

    public Message(String msg, User sender, Chat chat)
    {
        this.msg = msg;
        this.sender = sender;
        this.chat = chat;
    }
    
    public Message(Integer id, String msg, User sender, Chat chat)
    {
        this.id = id;
        this.msg = msg;
        this.sender = sender;
        this.chat = chat;
    }
    
    public Integer getId()
    {
        return id;
    }

    public Chat getChat()
    {
        return chat;
    }
    

    public String getMsg()
    {
        return msg;
    }

    public User getSender()
    {
        return sender;
    }

}
