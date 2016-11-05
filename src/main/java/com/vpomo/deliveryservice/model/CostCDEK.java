package com.vpomo.deliveryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Pomogalov V.A.
 */

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIgnoreProperties({"result"})
public class CostCDEK {
    @JsonProperty("price")
    private double price;
    @JsonProperty("deliveryPeriodMin")
    private int deliveryPeriodMin;
    @JsonProperty("deliveryPeriodMax")
    private int deliveryPeriodMax;
    @JsonProperty("deliveryDateMin")
    private String deliveryDateMin;
    @JsonProperty("deliveryDateMax")
    private String deliveryDateMax;
    @JsonProperty("tariffId")
    private int tariffId;
    @JsonProperty("cashOnDelivery")
    private float cashOnDelivery;
    @JsonProperty("priceByCurrency")
    private float priceByCurrency;
    @JsonProperty("currency")
    private String currency;

    private String textError;

    public CostCDEK() {
    }

    public CostCDEK(int price, int deliveryPeriodMin, int deliveryPeriodMax, String deliveryDateMin,
                    String deliveryDateMax, int tariffId, float cashOnDelivery, float priceByCurrency, String currency) {
        this.price = price;
        this.deliveryPeriodMin = deliveryPeriodMin;
        this.deliveryPeriodMax = deliveryPeriodMax;
        this.deliveryDateMin = deliveryDateMin;
        this.deliveryDateMax = deliveryDateMax;
        this.tariffId = tariffId;
        this.cashOnDelivery = cashOnDelivery;
        this.priceByCurrency = priceByCurrency;
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDeliveryPeriodMin() {
        return deliveryPeriodMin;
    }

    public void setDeliveryPeriodMin(int deliveryPeriodMin) {
        this.deliveryPeriodMin = deliveryPeriodMin;
    }

    public int getDeliveryPeriodMax() {
        return deliveryPeriodMax;
    }

    public void setDeliveryPeriodMax(int deliveryPeriodMax) {
        this.deliveryPeriodMax = deliveryPeriodMax;
    }

    public String getDeliveryDateMin() {
        return deliveryDateMin;
    }

    public void setDeliveryDateMin(String deliveryDateMin) {
        this.deliveryDateMin = deliveryDateMin;
    }

    public String getDeliveryDateMax() {
        return deliveryDateMax;
    }

    public void setDeliveryDateMax(String deliveryDateMax) {
        this.deliveryDateMax = deliveryDateMax;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public float getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(float cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public float getPriceByCurrency() {
        return priceByCurrency;
    }

    public void setPriceByCurrency(float priceByCurrency) {
        this.priceByCurrency = priceByCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTextError() {
        return textError;
    }

    public void setTextError(String textError) {
        this.textError = textError;
    }

    @Override
    public String toString() {
        return "{" + "error:" + textError +
                ", price=" + price + ", deliveryPeriodMin=" + deliveryPeriodMin + ", deliveryPeriodMax=" + deliveryPeriodMax +
                ", deliveryDateMin=" + deliveryDateMin + ", deliveryDateMax=" + deliveryDateMax + ", tariffId=" + tariffId +
                ", cashOnDelivery=" + cashOnDelivery + ", priceByCurrency=" + priceByCurrency + ", currency=" + currency +
                "}";
    }
}
