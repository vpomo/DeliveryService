package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 */

public class ResponseDeleteOrderCDEK {
    private String messageFromService;
    private String errorCode;

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
}
