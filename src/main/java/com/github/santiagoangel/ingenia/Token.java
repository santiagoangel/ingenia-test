

package com.github.santiagoangel.ingenia;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Token class for app auth.
 * @author santiago
 */
@Entity
@Table(name = "token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
    @NamedQuery(name = "Token.findByToken", query = "SELECT t FROM Token t WHERE t.token = :token"),
    @NamedQuery(name = "Token.findByAppname", query = "SELECT t FROM Token t WHERE t.appname = :appname")})
public class Token implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "token")
    private String token;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "appname")
    private String appname;
    @JoinColumn(name = "appname", referencedColumnName = "appname", insertable = false, updatable = false)
    @OneToOne(optional = false)
    @XmlTransient    
    private App app;

    public Token() {
    }

    public Token(String appname) {
        this.appname = appname;
    }

    public Token(String appname, String token) {
        this.appname = appname;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    @XmlTransient
    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
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
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.appname == null && other.appname != null) || (this.appname != null && !this.appname.equals(other.appname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.github.santiagoangel.ingenia.Token[ appname=" + appname + " ]";
    }
    
}
