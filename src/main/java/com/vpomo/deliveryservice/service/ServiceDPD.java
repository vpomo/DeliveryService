package com.vpomo.deliveryservice.service;

import com.vpomo.deliveryservice.model.CostDPD;
import javax.validation.constraints.AssertFalse;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.soap.*;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author Pomogalov V.A.
 */

public class ServiceDPD {
    private static final String CLIENT_NUMBER = "1111000228";
    private static final String CLIENT_KEY = "64A3AAEE65FD662728338550BA9FCC7B84DE8767";

    public static void getCitiesCashPay() throws Exception {
        try {
            SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection connection = soapConnFactory.createConnection();
            String url = "http://ws.dpd.ru/services/geography2?wsdl";

            //Create a message
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.1 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            //Create objects that represent the various components of posts
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

            // Fill values
            SOAPFactory sf = SOAPFactory.newInstance();

            clientNumber.addTextNode(CLIENT_NUMBER);
            clientKey.addTextNode(CLIENT_KEY);
            //countryCode.addTextNode("RU");

            //Saving messages
            message.saveChanges();

            //Send the request and response to the output screen
            SOAPMessage soapResponse = connection.call(message, url);
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(System.out);
            t.transform(sourceContent, result);


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

            //Create a message
            MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.1 Protocol");
            SOAPMessage message = messageFactory.createMessage();

            //Create objects that represent the various components of posts
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

            // Fill values
            SOAPFactory sf = SOAPFactory.newInstance();

            clientNumber.addTextNode(CLIENT_NUMBER);
            clientKey.addTextNode(CLIENT_KEY);

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


            //Saving messages
            message.saveChanges();

            //Send the request and response to the output screen
            SOAPMessage soapResponse = connection.call(message, url);
            Source sourceContent = soapResponse.getSOAPPart().getContent();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty(OutputKeys.INDENT, "yes");

            ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
            StreamResult result = new StreamResult(streamOut);

            t.transform(sourceContent, result);

            System.out.println("Displays the server's response in XML format:");
            System.out.println(streamOut.toString());

            ReadCostDPD readCostDPD = new ReadCostDPD();
            List<CostDPD> returnListCost = readCostDPD.readFromXML(sourceContent);

            System.out.println("Displays the Java-objects:");
            Iterator<CostDPD> costIterator = returnListCost.iterator();
            while (costIterator.hasNext()) {
                System.out.println(costIterator.next());
            }

            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
