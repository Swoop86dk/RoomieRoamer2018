package utils;

import chat.entity.*;
import entity.*;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManager em = Persistence.createEntityManagerFactory("pu").createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    
    // throw new UnsupportedOperationException("REMOVE THIS LINE, WHEN YOU HAVE READ WARNING");
    
       
    em.getTransaction().begin();
   // Role userRole = new Role("user");
//    Role adminRole = new Role("admin");
//    Interest smoking = new Interest("smoking");
//    Interest soccer = new Interest("soccer");
//    Interest football = new Interest("football");
//    Interest swimming = new Interest("swimming");
//    Interest gaming = new Interest("gaming");
//    Interest music = new Interest("music");
//    Interest art = new Interest("art");
//    Budget low = new Budget("2000-3000");
//    Budget medium = new Budget("3000-4000");
//    Budget high = new Budget("4000-5000");
//    CleanLevel messy = new CleanLevel("Messy");
//    CleanLevel tidy = new CleanLevel("Tidy");
//    Reason money = new Reason("Money");
//    Reason friends = new Reason("Friends");
//    User user = new User("Charlie", "lol123", "Testing", "picRef");
   // user.addRole(userRole);
//    User admin = new User("admin", "test123");
//    admin.addRole(adminRole);
//    User both = new User("user_admin", "test123");
//    both.addRole(userRole);
//    both.addRole(adminRole);
//    em.persist(userRole);
//    em.persist(adminRole);
//    em.persist(smoking);
//    em.persist(soccer);
//    em.persist(football);
//    em.persist(swimming);
//    em.persist(gaming);
//    em.persist(music);
//    em.persist(art);
//    em.persist(user);
 /*   em.persist(user);
    em.persist(low);
    em.persist(medium);
    em.persist(high);
    em.persist(messy);
    em.persist(tidy);
    em.persist(money);
    em.persist(friends);
//    em.persist(admin);
//    em.persist(both);

    em.getTransaction().begin();
    ArrayList<User> users = new ArrayList();
    users.add(new User("Hafsa","password","desc","picref"));
    users.add(new User("Dolcie","password","desc","picref"));
    users.add(new User("Renee","password","desc","picref"));
    users.add(new User("Pixie","password","desc","picref"));
    users.add(new User("Esmai","password","desc","picref"));
    users.add(new User("Tamsin","password","desc","picref"));
    users.add(new User("Aneesa","password","desc","picref"));
    users.add(new User("Jan","password","desc","picref"));
    users.add(new User("Stacey","password","desc","picref"));
    users.add(new User("Izabelle","password","desc","picref"));
    users.add(new User("Nell","password","desc","picref"));
    users.add(new User("Trinity","password","desc","picref"));
    users.add(new User("Catriona","password","desc","picref"));
    users.add(new User("Norma","password","desc","picref"));
    users.add(new User("Misty","password","desc","picref"));
    users.add(new User("Ashanti","password","desc","picref"));
    users.add(new User("Jazmin","password","desc","picref"));
    users.add(new User("Olivia","password","desc","picref"));
    users.add(new User("Maggie","password","desc","picref"));
    users.add(new User("Lacey","password","desc","picref"));
    users.add(new User("Larissa","password","desc","picref"));
    users.add(new User("Jocelyn","password","desc","picref"));
    users.add(new User("Carla","password","desc","picref"));
    users.add(new User("Melody","password","desc","picref"));
    users.add(new User("Rita","password","desc","picref"));
    users.add(new User("Kitty","password","desc","picref"));
    users.add(new User("Erika","password","desc","picref"));
    users.add(new User("Ezra","password","desc","picref"));
    users.add(new User("Tamzin","password","desc","picref"));
    users.add(new User("Caden","password","desc","picref"));
    users.add(new User("Lemar","password","desc","picref"));
    users.add(new User("Kofi","password","desc","picref"));
    users.add(new User("Cory","password","desc","picref"));
    users.add(new User("Owen","password","desc","picref"));
    users.add(new User("Kayden","password","desc","picref"));
    users.add(new User("Pharrell","password","desc","picref"));
    users.add(new User("Koby","password","desc","picref"));
    users.add(new User("Nyah","password","desc","picref"));
    users.add(new User("Rian","password","desc","picref"));
    users.add(new User("Conor","password","desc","picref"));
    users.add(new User("Keiran","password","desc","picref"));
    users.add(new User("Cormac","password","desc","picref"));
    users.add(new User("Preston","password","desc","picref"));
    users.add(new User("Keir","password","desc","picref"));
    users.add(new User("Kobe","password","desc","picref"));
    users.add(new User("Max","password","desc","picref"));
    users.add(new User("Denis","password","desc","picref"));
    users.add(new User("Josiah","password","desc","picref"));
    users.add(new User("Connor","password","desc","picref"));
    users.add(new User("Marcus","password","desc","picref"));
    users.add(new User("Nelly","password","desc","picref"));
    users.add(new User("Dakota","password","desc","picref"));
    users.add(new User("Damien","password","desc","picref"));
    users.add(new User("Russell","password","desc","picref"));
    users.add(new User("Carlos","password","desc","picref"));
    users.add(new User("Dougie","password","desc","picref"));
    users.add(new User("Awais","password","desc","picref"));
    users.add(new User("Macey","password","desc","picref"));
    users.add(new User("Jarrad","password","desc","picref"));
    users.add(new User("Bryan","password","desc","picref"));
    users.add(new User("Veer","password","desc","picref"));
    users.add(new User("Max","password","desc","picref"));
    users.add(new User("Iestyn","password","desc","picref"));
    users.add(new User("Julius","password","desc","picref"));
    users.add(new User("Harley","password","desc","picref"));
    
      for (int i = 0; i < users.size(); i++) {
         em.persist(users.get(i)); 
      }
      */
    em.getTransaction().commit();
    em.close();
//    System.out.println("PW: " + user.getUserPass());
//    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
//    User user = em.find(User.class, "user");
//    System.out.println("Testing user with password: " + user.verifyPassword("test123"));
//    System.out.println("Created TEST Users");
   em.close();
  }

}
