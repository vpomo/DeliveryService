package com.vpomo.deliveryservice.model;

import java.util.ArrayList;

/**
 * @author Pomogalov V.A.
 */

public class ResponseStatusOrderCDEK {
    //<?xml version="1.0" encoding="UTF-8"?>
    // <StatusReport DateFirst="2000-12-31T17:00:00+00:00" DateLast="2016-11-12T04:59:16+00:00" >
    //   <Order ActNumber="U-FUN-20161112-003" Number="U-FUN-20161112-003" DispatchNumber="1034422688"  DeliveryDate="" RecipientName="" >
    //          <Status Date="2016-11-12T03:42:11+00:00" Code="1" Description="Создан" CityCode="286" CityName="Благовещенск">
    //              <State Date="2016-11-12T03:42:11+00:00" Code="1" Description="Создан" CityCode="286" CityName="Благовещенск" />
    //          </Status>
    //      <Reason Code="" Description="" Date=""></Reason>
    //      <DelayReason Code="" Description="" Date="" ></DelayReason>
    //  </Order>
    // </StatusReport>

    private String dateFirst;
    private String dateLast;
    private String actNumber;
    private String orderNumber;
    private String dispatchNumber;
    private String dateDelivery;
    private String recipientName;

    private String dateState;
    private int code;
    private String codeDescription;
    private int sendCityCode;
    private String sendCityName;

    private ArrayList<StateOrderCDEK> listStateOrderCDEK;

    private String codeReason;
    private String descriptionReason;
    private String dateReason;

    private String codeReasonDelay;
    private String descriptionReasonDelay;
    private String dateReasonDelay;

    public ResponseStatusOrderCDEK() {
        this.listStateOrderCDEK = new ArrayList<>();
    }

    public String getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(String dateFirst) {
        this.dateFirst = dateFirst;
    }

    public String getDateLast() {
        return dateLast;
    }

    public void setDateLast(String dateLast) {
        this.dateLast = dateLast;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDispatchNumber() {
        return dispatchNumber;
    }

    public void setDispatchNumber(String dispatchNumber) {
        this.dispatchNumber = dispatchNumber;
    }

    public String getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(String dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
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

    public String getCodeReason() {
        return codeReason;
    }

    public void setCodeReason(String codeReason) {
        this.codeReason = codeReason;
    }

    public String getDescriptionReason() {
        return descriptionReason;
    }

    public void setDescriptionReason(String descriptionReason) {
        this.descriptionReason = descriptionReason;
    }

    public String getDateReason() {
        return dateReason;
    }

    public void setDateReason(String dateReason) {
        this.dateReason = dateReason;
    }

    public String getCodeReasonDelay() {
        return codeReasonDelay;
    }

    public void setCodeReasonDelay(String codeReasonDelay) {
        this.codeReasonDelay = codeReasonDelay;
    }

    public String getDescriptionReasonDelay() {
        return descriptionReasonDelay;
    }

    public void setDescriptionReasonDelay(String descriptionReasonDelay) {
        this.descriptionReasonDelay = descriptionReasonDelay;
    }

    public String getDateReasonDelay() {
        return dateReasonDelay;
    }

    public void setDateReasonDelay(String dateReasonDelay) {
        this.dateReasonDelay = dateReasonDelay;
    }

    public ArrayList<StateOrderCDEK> getListStateOrderCDEK() {
        return listStateOrderCDEK;
    }

    public void setListStateOrderCDEK(StateOrderCDEK stateOrderCDEK) {
        this.listStateOrderCDEK.add(stateOrderCDEK);
    }

    @Override
    public String toString() {
        return ("{actNumber=" + actNumber + ", orderNumber=" + orderNumber + ", dispatchNumber=" + dispatchNumber + "}");
    }

}

