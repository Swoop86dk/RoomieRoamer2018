/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.resources;

import chat.entity.Chat;
import chat.entity.Message;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


/**
 *
 * @author Tarllark
 */
public class ChatFacade
{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
   
    
    public Chat getChat(int id){
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Chat chat = em.find(Chat.class, id);
        em.close();
        return chat;
        
    }
    
    public String getChatSession(int user1, int user2){
         EntityManager em = emf.createEntityManager();
        String qlString = "SELECT chat_id FROM chat c WHERE (c.USER1_user_id= " 
                + user1 + " AND c.USER2_user_id = " + user2 + ") OR (c.USER1_user_id = " + user2 
                + " AND c.USER2_user_id = " + user1 + ");";
        Query q = em.createNativeQuery(qlString);
        
        List<Object> res = q.getResultList();
        int id = 0;
        if(res.size() <= 0){
            id = createSession(user1, user2).getId();
            System.out.println("User 1: " + user1 + " User 2: " + user2);
        }
        else
            id = Integer.parseInt(res.get(0).toString());
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("ID", id);
        jsonObj.put("History", getHistory(em.find(Chat.class, id)));
        return jsonObj.toJSONString();
    }
    
    private void updateChatHistory(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String sqlString = "SELECT msg_id FROM message m WHERE m.chat = " + id + ";";
        Chat chat = em.find(Chat.class, id);
        List<Object> resList = em.createNativeQuery(sqlString).getResultList();
        for(Object o : resList){
            chat.addHistory(em.find(Message.class, Integer.parseInt(o.toString())));
        }
        em.close();
    }
    private void updateChatHistory(Chat chat){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String sqlString = "SELECT msg_id FROM message m WHERE m.chat = " + chat.getId() + ";";
        List<Object> resList = em.createNativeQuery(sqlString).getResultList();
        for(Object o : resList){
            chat.addHistory(em.find(Message.class, Integer.parseInt(o.toString())));
        }
        em.close();
    }
    
    public Chat createSession(int user1, int user2){
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Chat chat = new Chat(em.find(User.class, user1), em.find(User.class, user2));
        em.persist(chat);
        em.getTransaction().commit();
        em.close();
        return chat;
    }
    
    public boolean isNewer(Chat chat){
        if(chat.getLastMSG() != null && (!chat.getLastMSG().equals(chat.getNewMSG()) && chat.getNewMSG() != null)){ 
            chat.setLastMSG(chat.getNewMSG());
            chat.setNewMSG(null);
            return true;
        }
        return false;
    }
    
    public JSONObject toJSON(Message msg){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender().getId());
        jsonObj.put("msg", msg.getMsg());
        jsonObj.put("chat", msg.getChat().getId());
        return jsonObj;
    }
    public JSONObject toJSON(Message msg, Chat chat){
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sender", msg.getSender().getId());
        jsonObj.put("msg", msg.getMsg());
        jsonObj.put("chat", chat.getId());
        return jsonObj;
    }
        
    public String getNewMessage(int id){
        Chat chat = getChat(id);
        updateChatHistory(chat);
        return toJSON(chat.getHistory().get(chat.getHistory().size()-1)).toString();
        
        //return toJSON(chat.getLastMSG()).toJSONString();
        
    }
    
    public String getHistory(int id){
        Chat chat = getChat(id);
        updateChatHistory(chat);
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        for(Message msg : chat.getHistory())
            jsonArr.add(toJSON(msg));
        jsonObj.put("results", jsonArr);
        return jsonObj.toJSONString();
    }
    public JSONArray getHistory(Chat chat){
        updateChatHistory(chat);
        JSONArray jsonArr = new JSONArray();
        for(Message msg : chat.getHistory()){
            JSONObject o = toJSON(msg);
            jsonArr.add(o);
        }
        return jsonArr;
    }
    
    private void persistMSG(Message msg){
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(msg);
        em.getTransaction().commit();
        em.close();
    }
    
    public boolean createMSG(String msg, int userID, int chatID){
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Chat chat = em.find(Chat.class, chatID);
        Message newMSG = new Message(msg, em.find(User.class, userID),
                chat);
        em.close();
        chat.addHistory(newMSG);
        chat.setNewMSG(newMSG);
        isNewer(chat);
        persistMSG(newMSG);
        return true;
    }
    public boolean createMSG(String msg, User user, Chat chat){
        Message newMSG = new Message(msg, user,
                chat);
        chat.addHistory(newMSG);
        chat.setNewMSG(newMSG);
        isNewer(chat);
        persistMSG(newMSG);
        return true;
    }
    public boolean createMSG(JSONObject jsonMSG, int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String msg = jsonMSG.getAsString("msg");
        User user = em.find(User.class, id);
        Chat chat = em.find(Chat.class, jsonMSG.get("chat"));
        em.close();
        return createMSG(msg, user, chat);
    }
    
    public Message fromJSON(JSONObject jsonMSG){
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        String msg = jsonMSG.getAsString("msg");
        User user = em.find(User.class, jsonMSG.get("sender"));
        Chat chat = em.find(Chat.class, jsonMSG.get("chat"));
        em.close();
        Message newMSG = new Message(msg, user, chat);
        return new Message (msg, user, chat);
    }
    
}
