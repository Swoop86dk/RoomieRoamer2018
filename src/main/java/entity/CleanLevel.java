/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class CleanLevel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "cleanLevel_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cleanLevel_name", length = 20)
    private String cleanLevelName;
    

    public CleanLevel() {
    }

    public CleanLevel(String cleanLevelName) {
        this.cleanLevelName = cleanLevelName;
    }

    public String getCleanLevelName() {
        return cleanLevelName;
    }

    public void setCleanLevelName(String cleanLevelName) {
        this.cleanLevelName = cleanLevelName;
    }

    public Integer getId()
    {
        return id;
    }
    
}
