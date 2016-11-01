package com.vpomo.deliveryservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author Pomogalov V.A.
 */


@XmlRootElement
public class ServiceCostDPD implements Serializable {
    private static final long serialVersionUID = 6603477834338392140L;

    private String serviceCode;
    private String serviceName;
    private String cost;
    private String days;

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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

}
