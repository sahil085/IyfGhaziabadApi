package com.IyfGZB.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User_Info")
@Scope("session")
public  class UserInfo extends BaseModel implements Serializable {

    /**
     * Description of the property id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String username;
    @Column(unique = true)
    private String email ;

    @Column(nullable = false)
    private String password ;

    @Column(nullable = false)
    private String gender;

    @Column(unique = true)
    private Long mobileNumber;

    @Column(nullable = false)
    private Long alternateMobileNumber;

    @Column(nullable = false)
    private String currentAddress;

    @Column(nullable = false)
    private String permanentAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String isInitiated;

    @Column(nullable = false)
    private Integer roundsChant;

    @Column(nullable = false)
    private String FacilitatorName;

    @Column(nullable = false)
    private String nearestIskconTemple;

    @Column(nullable = false)
    private Integer vedicLevel;

    @Column(nullable = false)
    private Boolean isBrahmchari;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Role> roles= new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(Long alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getIsInitiated() {
        return isInitiated;
    }

    public void setIsInitiated(String isInitiated) {
        this.isInitiated = isInitiated;
    }

    public Integer getRoundsChant() {
        return roundsChant;
    }

    public void setRoundsChant(Integer roundsChant) {
        this.roundsChant = roundsChant;
    }

    public String getFacilitatorName() {
        return FacilitatorName;
    }

    public void setFacilitatorName(String facilitatorName) {
        FacilitatorName = facilitatorName;
    }

    public String getNearestIskconTemple() {
        return nearestIskconTemple;
    }

    public void setNearestIskconTemple(String nearestIskconTemple) {
        this.nearestIskconTemple = nearestIskconTemple;
    }

    public Integer getVedicLevel() {
        return vedicLevel;
    }

    public void setVedicLevel(Integer vedicLevel) {
        this.vedicLevel = vedicLevel;
    }

    public Boolean getBrahmchari() {
        return isBrahmchari;
    }

    public void setBrahmchari(Boolean brahmchari) {
        isBrahmchari = brahmchari;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
