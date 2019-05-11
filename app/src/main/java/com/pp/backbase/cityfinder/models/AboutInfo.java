package com.pp.backbase.cityfinder.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * DTO representing about_info object
 */

public class AboutInfo {

    @JsonProperty("companyName")

    private String companyName;
    @JsonProperty("companyAddress")

    private String companyAddress;
    @JsonProperty("postalCode")

    private String companyPostal;
    @JsonProperty("city")

    private String companyCity;
    @JsonProperty("details")

    private String aboutInfo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPostal() {
        return companyPostal;
    }

    public void setCompanyPostal(String companyPostal) {
        this.companyPostal = companyPostal;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
    }
}
