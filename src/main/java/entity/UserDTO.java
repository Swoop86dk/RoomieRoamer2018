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
public class UserDTO {

    private Integer id;
    private String userName;
    private String userPass;
    private String desc;
    private String picRef;
    private String roleList = "";
    private String interests = "";
    private String liked = "";
    private String ignored = "";
    private String matches = "";

    public UserDTO(User user) {
        //System.out.println(user);
        this.id = user.getId();
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.desc = user.getDesc();
        this.picRef = user.getPicRef();
        for (String role : user.getRolesAsStrings()) {
            roleList += role + " ";
        }
        if(user.getInterestsAsStrings() != null){
        for (String interest : user.getInterestsAsStrings()) {
            interests += interest + " ";
        }
        }
        if(user.getLikedAsStrings() != null){
        for (String like : user.getLikedAsStrings()) {
            liked += like + " ";
        }
        }
        if(user.getIgnoredAsStrings() != null){
        for (String ignore : user.getIgnoredAsStrings()) {
            ignored += ignore + " ";
        }
        }
        if(user.getMatchesAsStrings() != null){
        for (String match : user.getMatchesAsStrings()) {
            matches += match + " ";
        }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicRef() {
        return picRef;
    }

    public void setPicRef(String picRef) {
        this.picRef = picRef;
    }

    public String getRoleList() {
        return roleList;
    }

    public void setRoleList(String roleList) {
        this.roleList = roleList;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getIgnored() {
        return ignored;
    }

    public void setIgnored(String ignored) {
        this.ignored = ignored;
    }

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

}
