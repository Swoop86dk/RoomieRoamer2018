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
@Table(name = "CITYINFO")
public class CityInfo implements Serializable
{
private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "cityInfo_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CITY", length = 20)
    private String cityInfoName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZIPCODE", length = 20)
    private String cityInfoZip;
    

    public CityInfo() {
    }

    public CityInfo(String cityInfoName) {
        this.cityInfoName = cityInfoName;
    }

    public String getCityInfoName() {
        return cityInfoName;
    }

    public void setCityInfoName(String cityInfoName) {
        this.cityInfoName = cityInfoName;
    }

    public Integer getId()
    {
        return id;
    }
    
    public String getCityInfoZip()
    {
        return cityInfoZip;
    }

    public void setCityInfoZip(String cityInfoZip)
    {
        this.cityInfoZip = cityInfoZip;
    }
    
}
