package com.vpomo.deliveryservice;

/**
 * Created by Zver on 31.10.2016.
 */

import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;


public class ServiceDPD {

    public static void main(String args[]) throws Exception {
        //getCitiesCashPay();
        getServiceCostByParcels2();
    }

    public static void getCitiesCashPay() throws Exception {
        try {
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();
            String url = "http://ws.dpd.ru/services/geography2?wsdl";

            //Создаем сообщение
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.1 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            //Создаем объекты, представляющие различные компоненты сообщения
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPBody body = envelope.getBody();

            envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
            envelope.addNamespaceDeclaration("ns", "http://dpd.ru/ws/geography/2015-05-20");

            SOAPElement ticketRequest = body.addChildElement("getCitiesCashPay", "ns");

            SOAPElement request = ticketRequest.addChildElement("request");

            SOAPElement auth = request.addChildElement("auth");
            SOAPElement clientNumber = auth.addChildElement("clientNumber");
            //"clientNumber");
            SOAPElement clientKey = auth.addChildElement("clientKey");

            //SOAPElement countryCode = ticketRequest.addChildElement("countryCode");

            // Заполняем значения
            SOAPFactory sf = SOAPFactory.newInstance();

            clientNumber.addTextNode("1111000228");
            clientKey.addTextNode("64A3AAEE65FD662728338550BA9FCC7B84DE8767");
            //countryCode.addTextNode("RU");

            //Сохранение сообщения
            message.saveChanges();

            //Отправляем запрос и выводим ответ на экран
            SOAPMessage soapResponse = connection.call(message, url);
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(System.out);
            t.transform(sourceContent, result);

            //Закрываем соединение
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getServiceCostByParcels2() throws Exception {
        try {
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();
            String url = "http://ws.dpd.ru/services/calculator2?wsdl";

            //Создаем сообщение
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.1 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            //Создаем объекты, представляющие различные компоненты сообщения
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPBody body = envelope.getBody();

            envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
            envelope.addNamespaceDeclaration("ns", "http://dpd.ru/ws/calculator/2012-03-20");

            SOAPElement ticketRequest = body.addChildElement("getServiceCostByParcels2", "ns");

            SOAPElement request = ticketRequest.addChildElement("request");

            SOAPElement auth = request.addChildElement("auth");
            SOAPElement clientNumber = auth.addChildElement("clientNumber");
            SOAPElement clientKey = auth.addChildElement("clientKey");

            SOAPElement pickup = request.addChildElement("pickup");
            SOAPElement cityIdPickup = pickup.addChildElement("cityId");

            SOAPElement delivery = request.addChildElement("delivery");
            SOAPElement cityIdDelivery = delivery.addChildElement("cityId");

            SOAPElement selfPickup = request.addChildElement("selfPickup");
            SOAPElement selfDelivery = request.addChildElement("selfDelivery");
            SOAPElement serviceCode = request.addChildElement("serviceCode");
            //SOAPElement pickupDate = request.addChildElement("pickupDate");
            //SOAPElement maxDays = request.addChildElement("maxDays");
            //SOAPElement maxCost = request.addChildElement("maxCost");
            //SOAPElement declaredValue = request.addChildElement("declaredValue");

            SOAPElement parcel = request.addChildElement("parcel");
            SOAPElement weight = parcel.addChildElement("weight");
            SOAPElement length = parcel.addChildElement("length");
            SOAPElement width = parcel.addChildElement("width");
            SOAPElement height = parcel.addChildElement("height");
            SOAPElement quantity = parcel.addChildElement("quantity");

            // Заполняем значения
            SOAPFactory sf = SOAPFactory.newInstance();

            clientNumber.addTextNode("1111000228");
            clientKey.addTextNode("64A3AAEE65FD662728338550BA9FCC7B84DE8767");

            cityIdPickup.addTextNode("195880525");
            cityIdDelivery.addTextNode("49265227");

            selfPickup.addTextNode("true");
            selfDelivery.addTextNode("false");
            serviceCode.addTextNode("");
            //pickupDate.addTextNode("2016-12-21");
            //maxDays.addTextNode("20");
            //maxCost.addTextNode("0");
            //declaredValue.addTextNode("0");

            weight.addTextNode("1");
            length.addTextNode("10");
            width.addTextNode("10");
            height.addTextNode("10");
            quantity.addTextNode("1");


            //Сохранение сообщения
            message.saveChanges();

            //Отправляем запрос и выводим ответ на экран
            SOAPMessage soapResponse = connection.call(message, url);
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(System.out);
            t.transform(sourceContent, result);

            //Закрываем соединение
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
