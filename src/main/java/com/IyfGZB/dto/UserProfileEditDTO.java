package com.IyfGZB.dto;

import javax.persistence.Column;

/**
 * Created by sahil on 13/8/18.
 */
public class UserProfileEditDTO {

    private String username;
    private String email ;


    private String gender;

    private Long mobileNumber;

    private Long alternateMobileNumber;

    private String currentAddress;

    private String permanentAddress;

    private String city;

    private String state;

    private String street;

    private String isInitiated;

    private Integer roundsChant;

    private String facilitatorName;

    private String seniorFacilitatorName;

    private String counslerName;

    private String nearestIskconTemple;

    private Integer vedicLevel;

    private Boolean isBrahmchari;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
}
