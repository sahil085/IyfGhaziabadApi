package com.IyfGZB.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User_Info")
@Scope("session")
public  class UserInfo implements UserDetails{

    /**
     * Description of the property id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    /**
     * Description of the property email.
     */
    @Column(unique = true)
    private String username ;
    /**
     * Description of the property password.
     */
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password ;
    /**
     * Description of the property role , to grant authority to the user .
     */
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_roles",
            joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private Set<Role> role;
    /**
     * Description of the property full name.
     */
    private String fullName;

    public UserInfo(){

    }

    public UserInfo(String username, String password, String fullName){
        this.username=username;
        this.password= password;
        this.fullName=fullName;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        role.forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRole()));
        });
        return authorities;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role +
                ",]";
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }



}
