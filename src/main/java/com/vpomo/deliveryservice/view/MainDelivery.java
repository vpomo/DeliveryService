package com.vpomo.deliveryservice.view;

import com.vpomo.deliveryservice.model.*;
import com.vpomo.deliveryservice.service.*;
import com.vpomo.deliveryservice.service.ServiceCalcCDEK;
import java.util.ArrayList;

/**
 * @author Pomogalov V.A.
 */

public class MainDelivery {
    public static void main(String args[]) throws Exception {
        String dateExecute = "2016-10-20";
        String senderCityId = "286";
        String receiverCityId = "414";


        ServiceCalcCDEK serviceCalcCDEK = new ServiceCalcCDEK();
        ArrayList<GoodsShipment> listGoodsShipment = new ArrayList<>();

        //GoodsShipment (double weight, int length, int width, int height, double volume)
        GoodsShipment goodsShipment1 = new GoodsShipment(2.37, 33, 30, 13, 0.0);
        listGoodsShipment.add(goodsShipment1);
       // GoodsShipment goodsShipment2 = new GoodsShipment(0.1, 0, 0, 0, 0.1);
       // listGoodsShipment.add(goodsShipment2);
       // GoodsShipment goodsShipment3 = new GoodsShipment(0.3, 10, 7, 5, 0.0);
       // listGoodsShipment.add(goodsShipment3);

        if (ServiceCalcCDEK.checkDataGoods(listGoodsShipment)) {
            ResponseCostCDEK calculateDelivery = ServiceCalcCDEK.calculateDelivery(dateExecute, senderCityId, receiverCityId, listGoodsShipment);
            System.out.println("\n" + "Query result:");
            if (calculateDelivery.getResult().getTextError() != null) {
                System.out.println("Error accessing API: " + calculateDelivery.getResult().getTextError());
            } else {
                System.out.println("price=" + calculateDelivery.getResult().getPrice());
                System.out.println("deliveryPeriodMax=" + calculateDelivery.getResult().getDeliveryPeriodMax());
                System.out.println("cashOnDelivery=" + calculateDelivery.getResult().getCashOnDelivery());
            }
        } else {
            System.out.println("Goods parameters entered incorrectly");
        }

        ServiceOrderCDEK serviceOrderCDEK = new ServiceOrderCDEK();
        ParseAnswerCDEK parseAnswerCDEK = new ParseAnswerCDEK();
        String resultRequest = "";
/*
        "  <Order " +
                " Number=\"ORD1234571\"" +
                " RecCityCode=\"94\"" +
                " SendCityCode=\"286\"" +
                " RecipientName=\"" + testCyr + "\"" +
                " RecipientEmail=\"\"" +
                " Phone=\"2255665\"" +
                " Comment=\"order comment\"" +
                " DeliveryRecipientCost=\"472.00\"" +
                " RecipientCurrency=\"RUB\"" +
                " ItemsCurrency=\"RUB\"" +
                " tariffTypeCode=\"137\">\n" +
                "   <Address " +
                " PvzCode=\"-\"" +
                " Street=\"Lenina,Vladimir\"" +
                " House=\"94\"" +
                " Flat=\"-\"/>\n" +
                "    <Package " +
                " number=\"AA000000031\"" +
                " barCode=\"AA000000031\"" +
                " SizeA=\"10\"" +
                " SizeB=\"10\"" +
                " SizeC=\"10\"" +
                " weight=\"500\">\n" +
                "      <Item " +
                " amount=\"4\"" +
                " cost=\"1400.00\"" +
                " payment=\"0.0\"" +
                " wareKey=\"ORD123023-1\"" +
                " comment=\"400\"" +
                " weight=\"500\" />\n" +
                "      <SendAddress " +
                " Street=\"Ignatyevskoe shosse\"" +
                " House=\"14/8\"" +
                " Flat=\"-\"" +
                " SenderName=\"U - fun\" />\n" +
                "    </Package>\n" +
                "  </Order>\n" +

                OrderCDEK orderCDEK = new OrderCDEK(String numberOrder, );
*/
        OrderCDEK orderCDEK = new OrderCDEK(
                "u-fun-2016-11-11-001", // Номер заказа Интернет-магазина
                286, 414, // 286 - код Благовещенска , 414 - код г. Нижний Новгород
                "Рубежов Илья Александрович", // ФИО получателя
                "rubezhov@gmail.com", // e-mail получателя
                "+7-(928)-231-45-78", //Телефон получателя
                "Подарок на праздник", // Комментарий к заказу
                "ул. Пионерская", // Название улицы получателя
                "94а", // Номер дома получателя
                "23", // Номер квартиры получателя
                "33", // Длина посылки в мм
                "30", // Ширина посылки в мм
                "13", // Высота посылки в мм
                "1000", // Вес в граммах
                1, // Содержимое посылки число предметов
                "Ботинки черные", //Описание вложения
                "1000" // Вес одного предмета
        );

        String newOrderNumberCDEK = "";
        String newOrderUFUN = "U-FUN-20161114-002";

        ResponseAddOrderCDEK addOrderCDEK;
        System.out.println("Make new order ...");
        resultRequest = serviceOrderCDEK.newOrder(newOrderUFUN, orderCDEK);
        System.out.println("resultRequest=" + resultRequest);

        //Add order
        addOrderCDEK = parseAnswerCDEK.readFromXMLAddOrderCDEK(resultRequest);
        if (addOrderCDEK.getErrorCode() == null) {
            System.out.println("Успешно создан новый заказ.\n" +
                    "Номер заказа Интернет-магазина: " + addOrderCDEK.getNumberOrder() +
                    ", Номер заказа в системе СДЭК: " + addOrderCDEK.getDispatchNumberOrder());
            newOrderNumberCDEK = addOrderCDEK.getDispatchNumberOrder();
        } else {
            System.out.println("Ошибка при создании нового заказа: " + addOrderCDEK.getErrorCode() +
                    "\n" + addOrderCDEK.getMessageFromService() + "\n\n");
        }

        //View status all order's in period
        System.out.println("\n\n View status all order's in period: from 2010-07-16 to 2016-12-19 \n\n");
        resultRequest = serviceOrderCDEK.requestAllOrderInPeriod("2016-01-01", "2016-12-19");
        ArrayList<ResponseAllOrderCDEK> allOrderCDEK;

        allOrderCDEK = parseAnswerCDEK.readFromXMLAllOrderCDEK(resultRequest);
        if (allOrderCDEK != null) {
            System.out.println(allOrderCDEK.toString());
        } else {
            System.out.println("Нет ни одного заказа\n\n");
        }

        //Status by order
        System.out.println("\n\n Status by order");
        resultRequest = serviceOrderCDEK.statusOrderByNumberCDEK(newOrderNumberCDEK);
        //resultRequest = serviceOrderCDEK.statusOrderByNumberDate("u-fun-2016-11-09-001", "2016-11-09");
        ResponseStatusOrderCDEK statusOrderCDEK = new ResponseStatusOrderCDEK();
        statusOrderCDEK = parseAnswerCDEK.readFromXMLStatusOrderCDEK(resultRequest);
        if (statusOrderCDEK.getDispatchNumber() != null) {
            System.out.println(statusOrderCDEK.toString());
        } else {
            System.out.println("Такого заказа в базе СДЭК не существует");
        }

        //Print order. Make file: "order.pdf"
        System.out.println("\n\n Print order ...");
        serviceOrderCDEK.printOrder(newOrderNumberCDEK);

        //Delete order
        System.out.println("\n\n Delete order");
        resultRequest = serviceOrderCDEK.deleteOrder(newOrderUFUN, newOrderNumberCDEK);
        System.out.println(resultRequest);
        ResponseDeleteOrderCDEK delOrderCDEK;
        delOrderCDEK = parseAnswerCDEK.readFromXMLDeleteOrderCDEK(resultRequest);
        if (delOrderCDEK.getErrorCode() != null) {
            System.out.println("Ошибка при удалении заказа: " + delOrderCDEK.getErrorCode() + "\n\n");
        } else {
            System.out.println("Успешная операция с базой СДЭК. " + delOrderCDEK.getMessageFromService() + "\n\n");
        }

    }
}
