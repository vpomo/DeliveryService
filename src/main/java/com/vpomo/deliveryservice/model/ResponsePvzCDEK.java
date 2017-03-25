package com.vpomo.deliveryservice.model;

import java.util.List;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для чтения ответа от сервера СДЭК
 * при выводе всех пунктов выдачи заказов (ПВЗ) в данном городе
 */

public class ResponsePvzCDEK {

    // Пример ответа от сервера
    /*
         <Pvz Code="MSC-232" Name="Кинотеатр Аврора"
            CityCode="44" City="Москва" WorkTime="пн.-вс.00:00-24:00"
            Address="ПРОФСОЮЗНАЯ, д.154" Phone=""
            Note="Почтомат InPost находится в здании кинотеатра Аврора справа от центрального входа"
            coordX = "37.5050" coordY = "55.6216" Type="POSTOMAT" ownerCode="InPost">
            <WeightLimit WeightMin="0.000" WeightMax="0.000"></WeightLimit>
         </Pvz>
    */
    private String codePvz;
    private String namePvz;
    private String cityId;
    private String cityName;
    private String workTime;
    private String address;
    private String phone;
    private String note;
    private String coordX;
    private String coordY;
    private String typePvz;
    private String ownerCode;
    private String weightMin;
    private String weightMax;

    public String getCodePvz() {
        return codePvz;
    }

    public void setCodePvz(String codePvz) {
        this.codePvz = codePvz;
    }

    public String getNamePvz() {
        return namePvz;
    }

    public void setNamePvz(String namePvz) {
        this.namePvz = namePvz;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getTypePvz() {
        return typePvz;
    }

    public void setTypePvz(String typePvz) {
        this.typePvz = typePvz;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(String weightMin) {
        this.weightMin = weightMin;
    }

    public String getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(String weightMax) {
        this.weightMax = weightMax;
    }

    @Override
    public String toString(){
        return "PvzCDEK{codePvz=" + codePvz + ",namePvz=" + namePvz + "}";
    }
}
