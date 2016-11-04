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
    private int price;
    @JsonProperty("deliveryPeriodMin")
    private int deliveryPeriodMin;
    private int deliveryPeriodMax;
    private String deliveryDateMin;
    private String deliveryDateMax;
    private int tariffId;
    private float priceByCurrency;
    private String currency;

    @JsonProperty("result")
    private String result;


    public CostCDEK() {

    }

    public CostCDEK(int price, int deliveryPeriodMin, int deliveryPeriodMax, String deliveryDateMin,
                    String deliveryDateMax, int tariffId, float priceByCurrency, String currency) {
        this.price = price;
        this.deliveryPeriodMin = deliveryPeriodMin;
        this.deliveryPeriodMax = deliveryPeriodMax;
        this.deliveryDateMin = deliveryDateMin;
        this.deliveryDateMax = deliveryDateMax;
        this.tariffId = tariffId;
        this.priceByCurrency = priceByCurrency;
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CostCDEK{" +
                "price=" + price + ", deliveryPeriodMin=" + deliveryPeriodMin + ", deliveryPeriodMax=" + deliveryPeriodMax +
                ", deliveryDateMin=" + deliveryDateMin + ", deliveryDateMax=" + deliveryDateMax + ", tariffId=" + tariffId +
                ", priceByCurrency=" + priceByCurrency + ", currency=" + currency +
                "}";
    }
}
