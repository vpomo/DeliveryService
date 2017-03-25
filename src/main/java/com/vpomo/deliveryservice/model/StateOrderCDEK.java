package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для чтения ответа от сервера СДЭК
 * при выводе статуса заказа
 */

public class StateOrderCDEK {
    private String dateState;
    private int code;
    private String codeDescription;
    private int sendCityCode;
    private String sendCityName;

    public StateOrderCDEK(String dateState, int code, String codeDescription, int sendCityCode, String sendCityName) {
        this.dateState = dateState;
        this.code = code;
        this.codeDescription = codeDescription;
        this.sendCityCode = sendCityCode;
        this.sendCityName = sendCityName;
    }

    public String getDateState() {
        return dateState;
    }

    public void setDateState(String dateState) {
        this.dateState = dateState;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    public int getSendCityCode() {
        return sendCityCode;
    }

    public void setSendCityCode(int sendCityCode) {
        this.sendCityCode = sendCityCode;
    }

    public String getSendCityName() {
        return sendCityName;
    }

    public void setSendCityName(String sendCityName) {
        this.sendCityName = sendCityName;
    }

    @Override
    public String toString() {
        return ("{date=" + dateState + ", codeDescription=" + codeDescription + "}");
    }
}
