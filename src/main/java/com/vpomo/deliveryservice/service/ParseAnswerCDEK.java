package com.vpomo.deliveryservice.service;

import com.vpomo.deliveryservice.model.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * @author Pomogalov V.A.
 */

public class ParseAnswerCDEK {

    public ResponseStatusOrderCDEK readFromXMLStatusOrderCDEK(String parseString) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        ResponseStatusOrderCDEK statusOrderCDEK;
        try {
            reader = inputFactory.createXMLStreamReader(new StreamSource(new StringReader(parseString)));
            statusOrderCDEK = readStatusOrder(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return statusOrderCDEK;
    }

    public ResponseDeleteOrderCDEK readFromXMLDeleteOrderCDEK(String parseString) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        ResponseDeleteOrderCDEK delOrderCDEK;
        try {
            reader = inputFactory.createXMLStreamReader(new StreamSource(new StringReader(parseString)));
            delOrderCDEK = readDeleteOrder(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return delOrderCDEK;
    }

    public ResponseAddOrderCDEK readFromXMLAddOrderCDEK(String parseString) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        ResponseAddOrderCDEK addOrderCDEK;
        try {
            reader = inputFactory.createXMLStreamReader(new StreamSource(new StringReader(parseString)));
            addOrderCDEK = readAddOrder(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return addOrderCDEK;
    }

    public ArrayList<ResponseAllOrderCDEK> readFromXMLAllOrderCDEK(String parseString) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        ArrayList<ResponseAllOrderCDEK> allOrderCDEK;
        try {
            reader = inputFactory.createXMLStreamReader(new StreamSource(new StringReader(parseString)));
            allOrderCDEK = readAllOrder(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return allOrderCDEK;
    }

    private ResponseDeleteOrderCDEK readDeleteOrder(XMLStreamReader reader) throws XMLStreamException {
        ResponseDeleteOrderCDEK delOrderCDEK = new ResponseDeleteOrderCDEK();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    // System.out.println("elementName = " + elementName);
                    if (elementName.equals("DeleteRequest")) {
                        if (reader.getAttributeLocalName(1) == "ErrorCode") {
                            delOrderCDEK.setErrorCode(reader.getAttributeValue(1) + "\n" + reader.getAttributeValue(2));
                        } else {
                            // System.out.println("reader.getAttributeLocalName(1)=" + reader.getAttributeLocalName(1));
                            delOrderCDEK.setMessageFromService(reader.getAttributeValue(1));
                        }
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        return delOrderCDEK;
    }

    private ResponseAddOrderCDEK readAddOrder(XMLStreamReader reader) throws XMLStreamException {
        ResponseAddOrderCDEK addOrderCDEK = new ResponseAddOrderCDEK();
        int step = 0;
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    // System.out.println("elementName = " + elementName);
                    if (elementName.equals("response")) {
                        step = 1;
                    } else if (elementName.equals("Order")) {
                        if (step == 1) {
                            addOrderCDEK.setNumberOrder(reader.getAttributeValue(0));
                            if (reader.getAttributeLocalName(1) == "ErrorCode") {
                                addOrderCDEK.setErrorCode(reader.getAttributeValue(1) + "\n" + reader.getAttributeValue(2));
                            } else {
                                // System.out.println("reader.getAttributeLocalName(1)=" + reader.getAttributeLocalName(1));
                                addOrderCDEK.setDispatchNumberOrder(reader.getAttributeValue(1));
                                step = step + 1;
                            }
                        }
                        if (step == 2) {
                            addOrderCDEK.setMessageFromService(reader.getAttributeValue(0));
                        }
                    } else if (elementName.equals("DeliveryRequest")) {
                        addOrderCDEK.setErrorCode(reader.getAttributeValue(0));
                        addOrderCDEK.setMessageFromService(reader.getAttributeValue(1));
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        if (step == 0) addOrderCDEK.setErrorCode("error");
        return addOrderCDEK;
    }

    private ArrayList<ResponseAllOrderCDEK> readAllOrder(XMLStreamReader reader) throws XMLStreamException {
        ArrayList<ResponseAllOrderCDEK> allOrderCDEK = new ArrayList<>();
        ArrayList<AddedServiceInOrderCDEK> addedService = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    // System.out.println("elementName = " + elementName);
                    if (elementName.equals("InfoReport")) {
                        i = -1;
                    } else if (elementName.equals("Order")) {
                        i = i + 1;
                        j = -1;
                        allOrderCDEK.add(new ResponseAllOrderCDEK());
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
                    } else if (elementName.equals("AddedService")) {
                        j = j + 1;
                        allOrderCDEK.get(i).setAddedService(new AddedServiceInOrderCDEK(Integer.parseInt(reader.getAttributeValue(0)), Double.parseDouble(reader.getAttributeValue(1))));
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        if (i == -1) allOrderCDEK = null;
        return allOrderCDEK;
    }

    private ResponseStatusOrderCDEK readStatusOrder(XMLStreamReader reader) throws XMLStreamException {
        ResponseStatusOrderCDEK statusOrderCDEK = new ResponseStatusOrderCDEK();
        ArrayList<StateOrderCDEK> stateOrderCDEK = new ArrayList<>();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    // System.out.println("elementName = " + elementName);
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
                    if (elementName.equals("StatusReport")) {
                        statusOrderCDEK.setDateFirst(reader.getAttributeValue(0));
                        statusOrderCDEK.setDateLast(reader.getAttributeValue(1));
                    } else if (elementName.equals("Order")) {
                        statusOrderCDEK.setActNumber(reader.getAttributeValue(0));
                        statusOrderCDEK.setOrderNumber(reader.getAttributeValue(1));
                        statusOrderCDEK.setDispatchNumber(reader.getAttributeValue(2));
                        statusOrderCDEK.setDateDelivery(reader.getAttributeValue(3));
                        statusOrderCDEK.setRecipientName(reader.getAttributeValue(4));
                        //System.out.println("Order: " + reader.getAttributeLocalName(3) + "=" + reader.getAttributeValue(3));
                    } else if (elementName.equals("Status")) {
                        statusOrderCDEK.setDateState(reader.getAttributeValue(0));
                        statusOrderCDEK.setCode(Integer.parseInt(reader.getAttributeValue(1)));
                        statusOrderCDEK.setCodeDescription(reader.getAttributeValue(2));
                        statusOrderCDEK.setSendCityCode(Integer.parseInt(reader.getAttributeValue(3)));
                        statusOrderCDEK.setSendCityName(reader.getAttributeValue(4));
                    } else if (elementName.equals("State")) {
                        statusOrderCDEK.setListStateOrderCDEK(new StateOrderCDEK(reader.getAttributeValue(0), Integer.parseInt(reader.getAttributeValue(1)),
                                reader.getAttributeValue(2), Integer.parseInt(reader.getAttributeValue(3)), reader.getAttributeValue(4)
                        ));
                        //System.out.println("State=" + statusOrderCDEK.getListStateOrderCDEK().toString());
                    } else if (elementName.equals("Reason")) {
                        statusOrderCDEK.setCodeReason(reader.getAttributeValue(0));
                        statusOrderCDEK.setDateReason(reader.getAttributeValue(1));
                    } else if (elementName.equals("DelayReason")) {
                        statusOrderCDEK.setCodeReasonDelay(reader.getAttributeValue(0));
                        statusOrderCDEK.setDateReasonDelay(reader.getAttributeValue(1));
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        return statusOrderCDEK;
    }

}
