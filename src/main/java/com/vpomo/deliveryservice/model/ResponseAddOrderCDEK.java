package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 */

public class ResponseAddOrderCDEK {
    private String numberOrder;
    private String dispatchNumberOrder;
    private String messageFromService;
    private String errorCode;

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

    public String getMessageFromService() {
        return messageFromService;
    }

    public void setMessageFromService(String messageFromService) {
        this.messageFromService = messageFromService;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return ("{messageFromService=" + messageFromService + ", numberOrder=" + numberOrder + ", dispatchNumber = " + dispatchNumberOrder + "}");
    }
}
