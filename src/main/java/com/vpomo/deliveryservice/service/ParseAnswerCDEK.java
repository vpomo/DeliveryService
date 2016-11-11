package com.vpomo.deliveryservice.service;

import com.vpomo.deliveryservice.model.ResponseAllOrderCDEK;
import com.vpomo.deliveryservice.model.ResponseOrderCDEK;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * @author Pomogalov V.A.
 */
/*
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
        "<InfoReport>" +
            "<Order " +
                "Number=\"u-fun-2016-11-10-004\" " +
                "Date=\"2016-11-11\" " +
                "DispatchNumber=\"1034332635\" " +
                "TariffTypeCode=\"137\" " +
                "Weight=\"2.574\" " +
                "DeliverySum=\"655\" " +
                "DateLastChange=\"2016-01-01 00:00:00\" " +
                "CashOnDeliv=\"3080.00\"  " +
                "deliveryMode=\"3\" deliveryVariant=\"COURIER\">" +
            "<SendCity Code=\"286\" PostCode=\"675000\" Name=\"Благовещенск\"/>" +
            "<RecCity Code=\"414\" PostCode=\"603000\" Name=\"Нижний Новгород\"/>" +
            "<AddedService ServiceCode=\"30\" Sum=\"0\" />" +
            "<AddedService ServiceCode=\"2\" Sum=\"300\" />" +
        "</Order>" +
        "<Order
            Number=\"u-fun-2016-11-11-001\"
            Date=\"2016-11-11\"
            DispatchNumber=\"1034332711\"
            TariffTypeCode=\"137\"
            Weight=\"2.574\"
            DeliverySum=\"655\"
            DateLastChange=\"2016-01-01 00:00:00\"
            CashOnDeliv=\"3080.00\"
            deliveryMode=\"3\"
            deliveryVariant=\"COURIER\">" +
        "<SendCity Code=\"286\" PostCode=\"675000\" Name=\"Благовещенск\"/>" +
        "<RecCity Code=\"414\" PostCode=\"603000\" Name=\"Нижний Новгород\"/>" +
        "<AddedService ServiceCode=\"30\" Sum=\"0\" />" +
        "<AddedService ServiceCode=\"2\" Sum=\"300\" />" +
        "</Order></InfoReport>\n"
*/

public class ParseAnswerCDEK {
    public void readFromXMLAllOrderCDEK(String parseString) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = inputFactory.createXMLStreamReader(new StreamSource(new StringReader(parseString)));
            readDocument(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private void readDocument(XMLStreamReader reader) throws XMLStreamException {
        ArrayList<ResponseOrderCDEK> allOrderCDEK = new ArrayList<>();
        int i = -1;
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    // System.out.println("elementName = " + elementName);
                    if (elementName.equals("InfoReport")){

                    } else if (elementName.equals("Order")) {
                    i = i + 1;
                    allOrderCDEK.add(new ResponseOrderCDEK());
                    allOrderCDEK.get(i).setNumberOrder(reader.getAttributeValue(0));
                    allOrderCDEK.get(i).setDateOrder(reader.getAttributeValue(1));
                    allOrderCDEK.get(i).setDispatchNumberOrder(reader.getAttributeValue(2));
                    allOrderCDEK.get(i).setTariff(reader.getAttributeValue(3));
                    allOrderCDEK.get(i).setWeight(Double.parseDouble(reader.getAttributeValue(4)));
                    allOrderCDEK.get(i).setDeliverySum(Double.parseDouble(reader.getAttributeValue(5)));
                    allOrderCDEK.get(i).setDateLastChange(reader.getAttributeValue(6));
                    allOrderCDEK.get(i).setCashOnDeliv(Double.parseDouble(reader.getAttributeValue(7)));
                    allOrderCDEK.get(i).setDeliveryMode(reader.getAttributeValue(8));
                    allOrderCDEK.get(i).setDeliveryVariant(reader.getAttributeValue(9));
                    //System.out.println("Order: " + reader.getAttributeLocalName(3) + "=" + reader.getAttributeValue(3));
                } else if (elementName.equals("SendCity")) {
                    allOrderCDEK.get(i).setCodeSendCity(reader.getAttributeValue(0));
                    allOrderCDEK.get(i).setPostCodeSendCity(reader.getAttributeValue(1));
                    allOrderCDEK.get(i).setNameSendCity(reader.getAttributeValue(2));
                } else if (elementName.equals("RecCity")) {
                    allOrderCDEK.get(i).setCodeRecCity(reader.getAttributeValue(0));
                    allOrderCDEK.get(i).setPostCodeRecCity(reader.getAttributeValue(1));
                    allOrderCDEK.get(i).setNameRecCity(reader.getAttributeValue(2));
                } else if (elementName.equals("AddedService"))
                    System.out.println("AddedService = ");
                    //costDPD.setCost(readCharacters(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        System.out.println(allOrderCDEK.toString());
        //throw new XMLStreamException("Premature end");
    }

}
