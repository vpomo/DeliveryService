package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 */

public class OrderCDEK {
    private String numberClientOrder;
    private int sendCityCode;
    private int recCityCode;
    private int sendCityPostCode;
    private int recCityPostCode;

    private String recipientName;
    private String recipientEmail;
    private String phone;
    private int tariffTypeCode;
    private double deliveryRecipientCost;
    private String comment;
    private String sellerName;

    private String streetAddress;
    private String houseAddress;
    private String flatAddress;
    private String pvzCodeAddress;

    private String numberPackage;
    private String barCodePackage;
    private String weightPackage;
    private String lengthSizeA;
    private String widthSizeB;
    private String heightSizeC;

    private String wareKeyItem;
    private double castItem;
    private double paymentItem;
    private String weightItem;
    private int amountItem;
    private String commentItem;

    private String dateAttempt;
    private String timeBeginAttempt;
    private String timeEndAttempt;

    private String streetSendAddress;
    private String houseSendAddress;
    private String flatSendAddress;
    private String senderName;

    public OrderCDEK() {
    }

    public OrderCDEK(String numberClientOrder, int sendCityCode, int recCityCode,
                     String recipientName, String recipientEmail, String phone,
                     String comment, String streetAddress, String houseAddress, String flatAddress,
                     String lengthSizeA, String widthSizeB, String heightSizeC, String weightPackage,
                     int amountItem, String wareKeyItem, String weightItem) {
        this.numberClientOrder = numberClientOrder;
        this.sendCityCode = sendCityCode;
        this.recCityCode = recCityCode;
        this.recipientName = recipientName;
        this.recipientEmail = recipientEmail;
        this.phone = phone;
        this.comment = comment;
        this.streetAddress = streetAddress;
        this.houseAddress = houseAddress;
        this.flatAddress = flatAddress;
        this.lengthSizeA = lengthSizeA;
        this.widthSizeB = widthSizeB;
        this.heightSizeC = heightSizeC;
        this.weightPackage = weightPackage;
        this.amountItem = amountItem;
        this.wareKeyItem = wareKeyItem;
        this.weightItem = weightItem;
    }

    public String getNumberClientOrder() {
        return numberClientOrder;
    }

    public void setNumberClientOrder(String numberClientOrder) {
        this.numberClientOrder = numberClientOrder;
    }

    public int getSendCityCode() {
        return sendCityCode;
    }

    public void setSendCityCode(int sendCityCode) {
        this.sendCityCode = sendCityCode;
    }

    public int getRecCityCode() {
        return recCityCode;
    }

    public void setRecCityCode(int recCityCode) {
        this.recCityCode = recCityCode;
    }

    public int getSendCityPostCode() {
        return sendCityPostCode;
    }

    public void setSendCityPostCode(int sendCityPostCode) {
        this.sendCityPostCode = sendCityPostCode;
    }

    public int getRecCityPostCode() {
        return recCityPostCode;
    }

    public void setRecCityPostCode(int recCityPostCode) {
        this.recCityPostCode = recCityPostCode;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTariffTypeCode() {
        return tariffTypeCode;
    }

    public void setTariffTypeCode(int tariffTypeCode) {
        this.tariffTypeCode = tariffTypeCode;
    }

    public double getDeliveryRecipientCost() {
        return deliveryRecipientCost;
    }

    public void setDeliveryRecipientCost(double deliveryRecipientCost) {
        this.deliveryRecipientCost = deliveryRecipientCost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getFlatAddress() {
        return flatAddress;
    }

    public void setFlatAddress(String flatAddress) {
        this.flatAddress = flatAddress;
    }

    public String getPvzCodeAddress() {
        return pvzCodeAddress;
    }

    public void setPvzCodeAddress(String pvzCodeAddress) {
        this.pvzCodeAddress = pvzCodeAddress;
    }

    public String getNumberPackage() {
        return numberPackage;
    }

    public void setNumberPackage(String numberPackage) {
        this.numberPackage = numberPackage;
    }

    public String getBarCodePackage() {
        return barCodePackage;
    }

    public void setBarCodePackage(String barCodePackage) {
        this.barCodePackage = barCodePackage;
    }

    public String getWeightPackage() {
        return weightPackage;
    }

    public void setWeightPackage(String weightPackage) {
        this.weightPackage = weightPackage;
    }

    public String getLengthSizeA() {
        return lengthSizeA;
    }

    public void setLengthSizeA(String lengthSizeA) {
        this.lengthSizeA = lengthSizeA;
    }

    public String getWidthSizeB() {
        return widthSizeB;
    }

    public void setWidthSizeB(String widthSizeB) {
        this.widthSizeB = widthSizeB;
    }

    public String getHeightSizeC() {
        return heightSizeC;
    }

    public void setHeightSizeC(String heightSizeC) {
        this.heightSizeC = heightSizeC;
    }

    public String getWareKeyItem() {
        return wareKeyItem;
    }

    public void setWareKeyItem(String wareKeyItem) {
        this.wareKeyItem = wareKeyItem;
    }

    public double getCastItem() {
        return castItem;
    }

    public void setCastItem(double castItem) {
        this.castItem = castItem;
    }

    public double getPaymentItem() {
        return paymentItem;
    }

    public void setPaymentItem(double paymentItem) {
        this.paymentItem = paymentItem;
    }

    public String getWeightItem() {
        return weightItem;
    }

    public void setWeightItem(String weightItem) {
        this.weightItem = weightItem;
    }

    public int getAmountItem() {
        return amountItem;
    }

    public void setAmountItem(int amountItem) {
        this.amountItem = amountItem;
    }

    public String getCommentItem() {
        return commentItem;
    }

    public void setCommentItem(String commentItem) {
        this.commentItem = commentItem;
    }

    public String getDateAttempt() {
        return dateAttempt;
    }

    public void setDateAttempt(String dateAttempt) {
        this.dateAttempt = dateAttempt;
    }

    public String getTimeBeginAttempt() {
        return timeBeginAttempt;
    }

    public void setTimeBeginAttempt(String timeBeginAttempt) {
        this.timeBeginAttempt = timeBeginAttempt;
    }

    public String getTimeEndAttempt() {
        return timeEndAttempt;
    }

    public void setTimeEndAttempt(String timeEndAttempt) {
        this.timeEndAttempt = timeEndAttempt;
    }

    public String getStreetSendAddress() {
        return streetSendAddress;
    }

    public void setStreetSendAddress(String streetSendAddress) {
        this.streetSendAddress = streetSendAddress;
    }

    public String getHouseSendAddress() {
        return houseSendAddress;
    }

    public void setHouseSendAddress(String houseSendAddress) {
        this.houseSendAddress = houseSendAddress;
    }

    public String getFlatSendAddress() {
        return flatSendAddress;
    }

    public void setFlatSendAddress(String flatSendAddress) {
        this.flatSendAddress = flatSendAddress;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
