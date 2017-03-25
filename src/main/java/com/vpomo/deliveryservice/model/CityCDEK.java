package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Объект для чтения из CSV-файла списка городов в которых работает компания СДЭК
 */

public class CityCDEK {
    private String id;
    private String fullName;
    private String cityName;
    private String oblName;
    private String center;
    private String nalSumLimit;
    private String engName;
    private String postCodeList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOblName() {
        return oblName;
    }

    public void setOblName(String oblName) {
        this.oblName = oblName;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getNalSumLimit() {
        return nalSumLimit;
    }

    public void setNalSumLimit(String nalSumLimit) {
        this.nalSumLimit = nalSumLimit;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getPostCodeList() {
        return postCodeList;
    }

    public void setPostCodeList(String postCodeList) {
        this.postCodeList = postCodeList;
    }

    //ID;FullName;CityName;OblName;Center;NalSumLimit;EngName;PostCodeList
    @Override
    public String toString() {
        return ("{id=" + id + ", fullName=" + fullName + ", cityName=" + cityName
                + ", oblName=" + oblName + ", center=" + center + ", nalSumLimit=" + nalSumLimit
                + ", engName=" + engName + ", postCodeList=" + postCodeList + "}");
    }

}
