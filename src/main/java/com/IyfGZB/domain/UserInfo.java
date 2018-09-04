package com.IyfGZB.domain;

import com.IyfGZB.dto.UserProfileEditDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="User_Info")

public  class UserInfo extends BaseModel implements Serializable,UserDetails {

    /**
     * Description of the property id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private String password ;

    @Column(nullable = false)
    private String gender;

    @Column(unique = true)
    private Long mobileNumber;

    private Long alternateMobileNumber;

    @Column(nullable = false)
    private String currentAddress;

    @Column(nullable = false)
    private String permanentAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String isInitiated;

    private Integer roundsChant;

    private String facilitatorName;

    private String seniorFacilitatorName;

    private String counslerName;


    private String nearestIskconTemple;

    @Column(nullable = false)
    private String classLevel;

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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        Set<Role> roleSet=getRoles();
        roleSet.forEach(role ->
                auths.add(new SimpleGrantedAuthority(role.getRole())
                )
        );
        return auths;
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
        return facilitatorName;
    }

    public void setFacilitatorName(String facilitatorName) {
        this.facilitatorName = facilitatorName;
    }

    public String getSeniorFacilitatorName() {
        return seniorFacilitatorName;
    }

    public void setSeniorFacilitatorName(String seniorFacilitatorName) {
        this.seniorFacilitatorName = seniorFacilitatorName;
    }

    public String getCounslerName() {
        return counslerName;
    }

    public void setCounslerName(String counslerName) {
        this.counslerName = counslerName;
    }

    public String getNearestIskconTemple() {
        return nearestIskconTemple;
    }

    public void setNearestIskconTemple(String nearestIskconTemple) {
        this.nearestIskconTemple = nearestIskconTemple;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserInfo updateUser(UserProfileEditDTO userProfileEditDTO, UserInfo userInfo){

        userInfo.setIsInitiated(userProfileEditDTO.getIsInitiated());
        userInfo.setNearestIskconTemple(userProfileEditDTO.getNearestIskconTemple());
        userInfo.setState(userProfileEditDTO.getState());
        userInfo.setBrahmchari(userProfileEditDTO.getBrahmchari());
        userInfo.setAlternateMobileNumber(userProfileEditDTO.getAlternateMobileNumber());
        userInfo.setCity(userProfileEditDTO.getCity());
        userInfo.setCounslerName(userProfileEditDTO.getCounslerName());
        userInfo.setClassLevel(userProfileEditDTO.getClassLevel());
        userInfo.setSeniorFacilitatorName(userProfileEditDTO.getSeniorFacilitatorName());
        userInfo.setRoundsChant(userProfileEditDTO.getRoundsChant());
        userInfo.setUsername(userProfileEditDTO.getUsername());
        userInfo.setEmail(userProfileEditDTO.getEmail());
        userInfo.setGender(userProfileEditDTO.getGender());
        userInfo.setMobileNumber(userProfileEditDTO.getMobileNumber());
        userInfo.setCurrentAddress(userProfileEditDTO.getCurrentAddress());
        userInfo.setPermanentAddress(userProfileEditDTO.getPermanentAddress());
        return  userInfo;

    }

    //    @Override
//    public String toString() {
//        return "UserInfo{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", gender='" + gender + '\'' +
//                ", mobileNumber=" + mobileNumber +
//                ", alternateMobileNumber=" + alternateMobileNumber +
//                ", currentAddress='" + currentAddress + '\'' +
//                ", permanentAddress='" + permanentAddress + '\'' +
//                ", city='" + city + '\'' +
//                ", street='" + street + '\'' +
//                ", isInitiated='" + isInitiated + '\'' +
//                ", roundsChant=" + roundsChant +
//                ", facilitatorName='" + facilitatorName + '\'' +
//                ", seniorFacilitatorName='" + seniorFacilitatorName + '\'' +
//                ", counslerName='" + counslerName + '\'' +
//                ", nearestIskconTemple='" + nearestIskconTemple + '\'' +
//                ", vedicLevel=" + vedicLevel +
//                ", isBrahmchari=" + isBrahmchari +
//                ", roles=" + roles +
//                '}';
//    }
}
