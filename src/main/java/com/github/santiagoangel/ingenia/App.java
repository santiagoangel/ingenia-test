

package com.github.santiagoangel.ingenia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santiago
 * 
 * App model based on MySQL Table apps.
 * 
 * 
 */
@Entity
@Table(name = "apps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "App.findAll", query = "SELECT a FROM Apps a"),
    @NamedQuery(name = "App.findByAppname", query = "SELECT a FROM Apps a WHERE a.appname = :appname"),
    @NamedQuery(name = "App.findByCompany", query = "SELECT a FROM Apps a WHERE a.company = :company"),
    @NamedQuery(name = "App.findByDescription", query = "SELECT a FROM Apps a WHERE a.description = :description")})
public class App implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "appname")
    private String appname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "company")
    private String company;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "app")
    private Token token;

    public App() {
    }

    public App(String appname) {
        this.appname = appname;
    }

    public App(String appname, String company) {
        this.appname = appname;
        this.company = company;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @XmlTransient
    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appname != null ? appname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof App)) {
            return false;
        }
        App other = (App) object;
        if ((this.appname == null && other.appname != null) || (this.appname != null && !this.appname.equals(other.appname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.santiagoangel.ingenia.App[ appname=" + appname + " ]";
    }
    
}
