package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @Basic(optional = false)
    @Column(name = "user_desc", length = 255)
    private String desc;
    @Basic(optional = false)
    @Column(name = "user_picture", length = 255)
    private String picRef;
    @JoinColumn(name = "user_questionnaire")
    @OneToOne
    private Questionnaire questionnaire;
    @JoinTable(name = "user_roles", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList();
    @JoinTable(name = "user_interests", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "interest_name", referencedColumnName = "interest_name")})
    @ManyToMany
    private List<Interest> interests = new ArrayList();
    @JoinTable(name = "user_liked", joinColumns = {
        @JoinColumn(name = "user_1", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_2", referencedColumnName = "user_id")})
    @ManyToMany
    private List<User> liked = new ArrayList();
    @JoinTable(name = "user_ignored", joinColumns = {
        @JoinColumn(name = "user_1", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_2", referencedColumnName = "user_id")})
    @ManyToMany
    private List<User> ignored = new ArrayList();
    @JoinTable(name = "user_matches", joinColumns = {
        @JoinColumn(name = "user_1", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_2", referencedColumnName = "user_id")})
    @ManyToMany
    private List<User> matches = new ArrayList();

    public List<String> getRolesAsStrings()
    {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roleList) {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    public List<String> getInterestsAsStrings()
    {
        if (interests.isEmpty()) {
            return null;
        }
        List<String> interestsAsStrings = new ArrayList();
        for (Interest interest : interests) {
            interestsAsStrings.add(interest.getInterestName());
        }
        return interestsAsStrings;
    }
    
    public List<String> getLikedAsStrings()
    {
        if (liked.isEmpty()) {
            return null;
        }
        List<String> likedAsStrings = new ArrayList();
        for (User user : liked) {
            likedAsStrings.add(user.getUserName());
        }
        return likedAsStrings;
    }

    public List<String> getIgnoredAsStrings()
    {
        if (ignored.isEmpty()) {
            return null;
        }
        List<String> ignoredAsStrings = new ArrayList();
        for (User user : ignored) {
            ignoredAsStrings.add(user.getUserName());
        }
        return ignoredAsStrings;
    }
    
    public List<String> getMatchesAsStrings()
    {
        if (matches.isEmpty()) {
            return null;
        }
        List<String> matchesAsStrings = new ArrayList();
        for (User user : matches) {
            matchesAsStrings.add(user.getUserName());
        }
        return matchesAsStrings;
    }
    
    public User()
    {
    }
    
    
    public User(UserDTO dto){
        this.id=dto.getId();
        this.userName=dto.getUserName();
        
    }
    
    

    //TODO Change when password is hashed
    public boolean verifyPassword(String pw)
    {
        return BCrypt.checkpw(pw, userPass);
//        return(pw.equals(userPass));
    }

    public User(int id, String userName)
    {
        this.userName = userName;
        this.id = id;
    }

    public User(String userName, String userPass)
    {
        this.userName = userName;
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(userPass, salt);
        this.userPass = hash;
    }

    public User(String userName, String userPass, String desc, String picRef)
    {
        this.userName = userName;
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(userPass, salt);
        this.userPass = hash;
        this.desc = desc;
        this.picRef = picRef;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public void addRole(Role userRole)
    {
        this.roleList.add(userRole);
    }

    public List<Interest> getInterests()
    {
        return interests;
    }

    public void setInterests(List<Interest> interests)
    {
        this.interests = interests;
    }

    public void addInterest(Interest userInterest)
    {
        this.interests.add(userInterest);
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getPicRef()
    {
        return picRef;
    }

    public void setPicRef(String picRef)
    {
        this.picRef = picRef;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public List<User> getLiked()
    {
        return liked;
    }

    public void setLiked(List<User> liked)
    {
        this.liked = liked;
    }

    public void addLiked(User user)
    {
        this.liked.add(user);
    }

    public List<User> getIgnored()
    {
        return ignored;
    }

    public void setIgnored(List<User> ignored)
    {
        this.ignored = ignored;
    }

    public void addIgnored(User user)
    {
        this.ignored.add(user);
    }

    public List<User> getMatches()
    {
        return matches;
    }

    public void setMatches(List<User> matches)
    {
        this.matches = matches;
    }

    public void addMatched(User user)
    {
        this.matches.add(user);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", userPass=" + userPass + ", desc=" + desc + ", picRef=" + picRef + ", roleList=" + getRolesAsStrings()+ ", interests=" + getInterestsAsStrings() + ", liked=" + getLikedAsStrings() + ", ignored=" + getIgnoredAsStrings() + ", matches=" + getMatchesAsStrings() + '}';
    }

    public Questionnaire getQuestionnaire()
    {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire)
    {
        this.questionnaire = questionnaire;
    }

    

}
