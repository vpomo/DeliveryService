package com.vpomo.deliveryservice.view;

import com.vpomo.deliveryservice.model.GoodsShipment;
import com.vpomo.deliveryservice.model.ResponseCostCDEK;
import com.vpomo.deliveryservice.service.ServiceCDEK;

import java.util.ArrayList;

import static com.vpomo.deliveryservice.service.ServiceCDEK.*;
import static com.vpomo.deliveryservice.service.ServiceDPD.getServiceCostByParcels2;

/**
 * @author Pomogalov V.A.
 */

public class MainDelivery {
    public static void main(String args[]) throws Exception {
        String dateExecute = "2016-10-20";
        String senderCityId = "286";
        String receiverCityId = "94";

        ServiceCDEK serviceCDEK = new ServiceCDEK();
        ArrayList<GoodsShipment> listGoodsShipment = new ArrayList<>();

        //GoodsShipment (double weight, int length, int width, int height, double volume)
        GoodsShipment goodsShipment1 = new GoodsShipment(0.5, 10, 7, 5, 0.0);
        listGoodsShipment.add(goodsShipment1);
        GoodsShipment goodsShipment2 = new GoodsShipment(0.1, 0, 0, 0, 0.1);
        listGoodsShipment.add(goodsShipment2);
        GoodsShipment goodsShipment3 = new GoodsShipment(0.3, 10, 7, 5, 0.0);
        listGoodsShipment.add(goodsShipment3);

        if (ServiceCDEK.checkDataGoods(listGoodsShipment)) {
            ResponseCostCDEK calculateDelivery = ServiceCDEK.calculateDelivery(dateExecute, senderCityId, receiverCityId, listGoodsShipment);
            System.out.println("\n" + "Query result:");
            if (calculateDelivery.getResult().getTextError() != null) {
                System.out.println("Error accessing API: " + calculateDelivery.getResult().getTextError());
            } else {
                System.out.println("price=" + calculateDelivery.getResult().getPrice());
                System.out.println("deliveryPeriodMax=" + calculateDelivery.getResult().getDeliveryPeriodMax());
                System.out.println("cashOnDelivery=" + calculateDelivery.getResult().getCashOnDelivery());
            }
        } else {
            System.out.println("Fail :(((((((((((((((");
        }

    }

}
