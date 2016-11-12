package com.vpomo.deliveryservice.model;

import java.util.ArrayList;

/**
 * @author Pomogalov V.A.
 */

public class ResponseAllOrderCDEK {
/*
          <Order
            Number=\"u-fun-2016-11-11-001\"
            Date=\"2016-11-11\"
            DispatchNumber=\"1034332711\"
            TariffTypeCode=\"137\"
            Weight=\"2.574\"
            DeliverySum=\"655\"
            DateLastChange=\"2016-01-01 00:00:00\"
            CashOnDeliv=\"3080.00\"
            deliveryMode=\"3\"
            deliveryVariant=\"COURIER\">"
        <SendCity Code=\"286\" PostCode=\"675000\" Name=\"Благовещенск\"/>
        <RecCity Code=\"414\" PostCode=\"603000\" Name=\"Нижний Новгород\"/>
        <AddedService ServiceCode=\"30\" Sum=\"0\" />
        <AddedService ServiceCode=\"2\" Sum=\"300\" />
*/

    private String numberOrder;
    private String dispatchNumberOrder;
    private String dateOrder;
    private String tariff;
    private String dateLastChange;
    private String deliveryMode;
    private String deliveryVariant;
    private double weight;
    private double deliverySum;
    private double cashOnDeliv;

    private String codeSendCity;
    private String postCodeSendCity;
    private String nameSendCity;

    private String codeRecCity;
    private String postCodeRecCity;
    private String nameRecCity;
    private ArrayList<AddedServiceInOrderCDEK> addedService;

    public ResponseAllOrderCDEK() {
        this.addedService = new ArrayList<>();
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getDispatchNumberOrder() {
        return dispatchNumberOrder;
    }

    public void setDispatchNumberOrder(String dispatchNumberOrder) {
        this.dispatchNumberOrder = dispatchNumberOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public String getDateLastChange() {
        return dateLastChange;
    }

    public void setDateLastChange(String dateLastChange) {
        this.dateLastChange = dateLastChange;
    }

    public String getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(String deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getDeliveryVariant() {
        return deliveryVariant;
    }

    public void setDeliveryVariant(String deliveryVariant) {
        this.deliveryVariant = deliveryVariant;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDeliverySum() {
        return deliverySum;
    }

    public void setDeliverySum(double deliverySum) {
        this.deliverySum = deliverySum;
    }

    public double getCashOnDeliv() {
        return cashOnDeliv;
    }

    public void setCashOnDeliv(double cashOnDeliv) {
        this.cashOnDeliv = cashOnDeliv;
    }

    public String getCodeSendCity() {
        return codeSendCity;
    }

    public void setCodeSendCity(String codeSendCity) {
        this.codeSendCity = codeSendCity;
    }

    public String getPostCodeSendCity() {
        return postCodeSendCity;
    }

    public void setPostCodeSendCity(String postCodeSendCity) {
        this.postCodeSendCity = postCodeSendCity;
    }

    public String getNameSendCity() {
        return nameSendCity;
    }

    public void setNameSendCity(String nameSendCity) {
        this.nameSendCity = nameSendCity;
    }

    public String getCodeRecCity() {
        return codeRecCity;
    }

    public void setCodeRecCity(String codeRecCity) {
        this.codeRecCity = codeRecCity;
    }

    public String getPostCodeRecCity() {
        return postCodeRecCity;
    }

    public void setPostCodeRecCity(String postCodeRecCity) {
        this.postCodeRecCity = postCodeRecCity;
    }

    public String getNameRecCity() {
        return nameRecCity;
    }

    public void setNameRecCity(String nameRecCity) {
        this.nameRecCity = nameRecCity;
    }

    public ArrayList<AddedServiceInOrderCDEK> getAddedService() {
        return addedService;
    }

    public void setAddedService(AddedServiceInOrderCDEK addedService) {
        this.addedService.add(addedService);
    }

    @Override
    public String toString() {
        return ("ResponseAllOrderCDEK={numberOrder=" + numberOrder + ", dispatchNumberOrder=" + dispatchNumberOrder + ", AddedService=" + addedService.toString() + "}");
    }
}
