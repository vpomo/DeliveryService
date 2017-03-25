package com.vpomo.deliveryservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Pomogalov V.A.
 *
 * Объект для отправки данных на сервер ДПД для расчета стоимости доставки
 */


@XmlRootElement(name = "costDPD")
public class CostDPD implements Serializable {
    private static final long serialVersionUID = 6603477834338392140L;

    private String serviceCode;
    private String serviceName;
    private String cost;
    private int days;

    public CostDPD(String serviceCode, String serviceName, String cost, int days){
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.cost = cost;
        this.days = days;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "CostDPD{" + "serviceCode=" + serviceCode + ", serviceName=" + serviceName + ", cost=" + cost + ", days=" + days + "}";
    }

}
