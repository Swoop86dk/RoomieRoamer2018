package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class test {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        UserFacade uf = new UserFacade();
        int user = 1;
        System.out.println(uf.getPoma(em.find(User.class, user)));
    }
}
