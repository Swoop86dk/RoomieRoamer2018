package entity;

import Resources.DBAccess.Connector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import exceptions.AuthenticationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.Clock.offset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Query;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * TODO: Liste over potentielle personer du kan like Liste over LIKEDE personer
 * (én person har liked en anden og afventer svar) Liste over Matches (to
 * personer som har liked hinanden) Liste over disliked personer (personlig
 * dislike liste) Liste over blokerede personer Liste over samtaler
 *
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    //Default EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static final UserFacade instance = new UserFacade();

    public UserFacade() {
    }

    public static UserFacade getInstance() {
        return instance;
    }

    public UserDTO getUserDTO(Integer id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User u = em.find(User.class, id);

            System.out.println(u);

            //System.out.println(u);
            UserDTO udto = new UserDTO(u);
            return udto;

        } finally {
            em.close();
        }
    }

    public String getUsers() throws SQLException, ClassNotFoundException {
        EntityManager em = emf.createEntityManager();
        JSONArray JA = new JSONArray();
        //em.getTransaction().begin();
        List<User> users = new ArrayList();
        Boolean keepRunning = true;
        int counter = 1;
        while (keepRunning) {
            users.add(em.find(User.class, counter));
            keepRunning = (em.find(User.class, ++counter) != null);
        }
        //em.getTransaction().commit();
        for (User user : users) {
            JSONObject item = new JSONObject();
            item.put("id", user.getId());
            item.put("Name", user.getUserName());
            item.put("Desc", user.getDesc());
            // item.put("Desc", em.find(User.class, "desc"));

            JA.add(item);
//            UserDTO uDTO = new UserDTO(user);
//            JA.add(uDTO);
        }
        JSONObject res = new JSONObject();
        res.put("Result", JA);
        em.close();
        return res.toJSONString();
    }

    public UserDTO getUser(Integer id) {
        EntityManager em = emf.createEntityManager();
        UserDTO uDTO;
        try {
            em.getTransaction().begin();
            User u = em.find(User.class, id);
            System.out.println(u);
            uDTO = new UserDTO(u);
            System.out.println(uDTO);
            em.getTransaction().commit();
            return uDTO;

        } finally {
            em.close();

        }
    }

    public List<User> getUsersByRoleAdmin() {
        EntityManager em = emf.createEntityManager();

        List<User> users = null;

        try {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :Admin").getResultList();
            em.getTransaction().commit();
            return users;
        } finally {
            em.close();
        }
    }

    public List<User> getUsersByRoleUser() {
        EntityManager em = emf.createEntityManager();

        List<User> users = null;

        try {
            em.getTransaction().begin();
            users = em.createQuery("Select p from User p WHERE p.roleList = :User").getResultList();
            em.getTransaction().commit();
            return users;
        } finally {
            em.close();
        }
    }
   
    
    
    public User addUser(String name, String password) {
        EntityManager em = emf.createEntityManager();
            User user = new User(name, password);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } finally {
            em.close();
        }
    }
    public User addUser(JSONObject json) {
        EntityManager em = emf.createEntityManager();
            User user = new User(json.getAsString("first_name"), json.getAsString("password"));
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } finally {
            em.close();
        }
    }
    
    public User getUseredit(int id) {
        EntityManager em = emf.createEntityManager();
        
        User u = null;
        
        try {
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
            return u;
        }
        finally
        {
            em.close();
        }
    }
    
    public UserDTO editUser(User user) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User u = em.find(User.class, user.getId());
            if (u != null) {
                u = user;
                em.merge(u);
                UserDTO uDTO = new UserDTO(u);
                em.getTransaction().commit();
                return uDTO;
            }
        } finally {
            em.close();
        }

        return null;
    }

    public User deleteUser(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User u = em.find(User.class, id);
            em.remove(u);
            em.getTransaction().commit();
            return u;
        } finally {
            em.close();
        }
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            Query q = em.createQuery("SELECT u.id FROM User u WHERE u.userName=:username");
            q.setParameter("username", username);
            int id = (int) q.getSingleResult();
            user = em.find(User.class, id);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public User getTest() {
        EntityManager em = emf.createEntityManager();
        User u = em.find(User.class, 1);
        return u;
    }

    public List<User> getPoma(User user) {
        EntityManager em = emf.createEntityManager();
        List<User> poma = new ArrayList();
        int max = 100;
        int iteration = 1;
        List<User> curSet = new ArrayList();
        boolean keepRunning = true;
        //while(!(poma.size() >= 20))

        //if(em.find(User.class, (max*iteration-29)) == null) break;
        //Create user set
        /*for(int i = (max*iteration-29); i <= max*iteration; i++)
            for(int i = 1; i <= max; i++)
            {
                User cur = em.find(User.class, i);
                if(user.getId()!= cur.getId()){
                    System.out.println("NOT THE SAME AS ME!");
                    if(!user.getIgnored().contains(cur.getId())){
                        System.out.println("Not ignored");
                        if(!user.getLiked().contains(cur.getId())){
                            System.out.println("Not liked");
                            if(!user.getMatches().contains(cur.getId())){
                                System.out.println("Not matched");
                                curSet.add(cur);
                            }
                        }
                    }
                }
            }*/
        while (keepRunning) {
            User cur = em.find(User.class, iteration);
            boolean eligible = false;
            if (user.getId() != cur.getId()) {
                for (User u : user.getIgnored()) {
                    if (u.getId() != cur.getId()) {
                        eligible = true;
                    } else {
                        eligible = false;
                        break;
                    }
                }
                if (eligible) {
                    for (User u : user.getLiked()) {
                        if (u.getId() != cur.getId()) {
                            eligible = true;
                        } else {
                            eligible = false;
                            break;
                        }
                    }
                    if (eligible) {
                        for (User u : user.getMatches()) {
                            if (u.getId() != cur.getId()) {
                                eligible = true;
                            } else {
                                eligible = false;
                                break;
                            }
                        }
                    }
                }
            }
            if(eligible) curSet.add(cur);
            keepRunning = em.find(User.class, ++iteration) != null;
        }

        //Create poma list
        for (User cur : curSet) {
            int count = 0;
            if (user.getQuestionnaire().getQuestionnaireArea().getCityInfoZip().equals(cur.getQuestionnaire().getQuestionnaireArea().getCityInfoZip())) {
                count++;
            }
            if (user.getQuestionnaire().getQuestionnaireBudget().getBudgetName().equals(cur.getQuestionnaire().getQuestionnaireBudget().getBudgetName())) {
                count++;
            }
            if (user.getQuestionnaire().getQuestionnaireClean().getCleanLevelName().equals(cur.getQuestionnaire().getQuestionnaireClean().getCleanLevelName())) {
                count++;
            }
            if (user.getQuestionnaire().getQuestionnaireReason().getReasonName().equals(cur.getQuestionnaire().getQuestionnaireReason().getReasonName())) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnaireMusic() == cur.getQuestionnaire().isQuestionnaireMusic()) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnaireParty() == cur.getQuestionnaire().isQuestionnaireParty()) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnairePet() == cur.getQuestionnaire().isQuestionnairePet()) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnaireSingle() == cur.getQuestionnaire().isQuestionnaireSingle()) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnaireSmoke() == cur.getQuestionnaire().isQuestionnaireSmoke()) {
                count++;
            }
            if (user.getQuestionnaire().isQuestionnaireSport() == cur.getQuestionnaire().isQuestionnaireSport()) {
                count++;
            }

            if (count >= 3) {
                poma.add(cur);
            }
//            if (poma.size() >= 20) {
//                break;
//            }
        }

        return poma;
    }

    public String getPomaAsJSON(int id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        JSONArray jsnArr = new JSONArray();
        for (User usr : getPoma(user)) {
            JSONObject jsn = new JSONObject();

            jsn.put("Id", usr.getId());
            jsn.put("Name", usr.getUserName());
            jsn.put("Desc", usr.getDesc());
            jsnArr.add(jsn);
        }
        JSONObject jon = new JSONObject();
        jon.put("results", jsnArr);

        return jon.toJSONString();
    }

    public String getPomaAsString() {
        EntityManager em = emf.createEntityManager();
        String res = "";
        for (User u : getPoma(em.find(User.class, 1))) {
            res += u.getUserName() + "\n";
        }
        return res;
    }

    public UserDTO assignUserLike(int userID, int likedID) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User loggedIn = em.find(User.class, userID);
        User liked = em.find(User.class, likedID);
        UserDTO uDTO;

        if (liked.getLiked().contains(loggedIn)) {
            loggedIn.addMatched(liked);
            liked.addMatched(loggedIn);
            uDTO = new UserDTO(loggedIn);
            em.merge(loggedIn);
            em.merge(liked);
        }

        if (!loggedIn.getLiked().contains(liked)) {
            loggedIn.addLiked(liked);
            uDTO = new UserDTO(loggedIn);
            em.merge(loggedIn);
        } else {
            throw new Exception("Relation between users already exist");
        }
        em.getTransaction().commit();
        em.close();
        return uDTO;
    }

    public UserDTO assignUserIgnore(int userID, int ignoredID) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User loggedIn = em.find(User.class, userID);
        User ignored = em.find(User.class, ignoredID);
        UserDTO uDTO;

        if (!loggedIn.getIgnored().contains(ignored)) {
            loggedIn.addIgnored(ignored);
            uDTO = new UserDTO(loggedIn);
            em.merge(loggedIn);
        } else {
            throw new Exception("Relation between users already exist");
        }
        em.getTransaction().commit();
        em.close();
        return uDTO;
    }
    
    public List<UserDTO> getMatchedUsers(int id) {
        EntityManager em = emf.createEntityManager();
        List<UserDTO> dtoList = new ArrayList();
        try {
            User u = em.find(User.class, id);
            for (User user : u.getMatches()) {
                UserDTO uDTO = new UserDTO(user);
                dtoList.add(uDTO);
            }
        } finally {
            em.close();
        }
        return dtoList;
    }
    
}
