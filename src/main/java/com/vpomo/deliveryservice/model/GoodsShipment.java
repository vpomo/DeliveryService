package com.vpomo.deliveryservice.model;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для описания содержимого посылки
 */


public class GoodsShipment {
    private double weight;
    private int length;
    private int width;
    private int height;
    private double volume;
    private double priceSite;
    private String articul;
    private double payment;
    private double cost;

    public GoodsShipment (double weight, int length, int width, int height, double volume){
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getPriceSite() {
        return priceSite;
    }

    public void setPriceSite(double priceSite) {
        this.priceSite = priceSite;
    }

    public String getArticul() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "GoodsShipment{" + "weight=" + weight + ", length=" + length + ", width=" +
                width + ", height=" + height + ", volume=" + volume + "}";
    }
}
