package com.vpomo.deliveryservice.service;

import com.vpomo.deliveryservice.model.CostDPD;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Pomogalov V.A.
 */

public class ReadCostDPD {


    public List<CostDPD> readFromXML(Source is) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = inputFactory.createXMLStreamReader(is);
            return readDocument(reader);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    private List<CostDPD> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("getServiceCostByParcels2Response"))
                        return readBooks(reader);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        throw new XMLStreamException("Premature end");
    }

    private List<CostDPD> readBooks(XMLStreamReader reader) throws XMLStreamException {
        List<CostDPD> costDPDs = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("return"))
                        costDPDs.add(readCostDPD(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return costDPDs;
            }
        }
        throw new XMLStreamException("Premature end");
    }

    private CostDPD readCostDPD(XMLStreamReader reader) throws XMLStreamException {
        CostDPD costDPD = new CostDPD("", "", "", 0);
        //book.setLanguage(reader.getAttributeValue(null, "language"));

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("serviceCode"))
                        costDPD.setServiceCode(readCharacters(reader));
                        //book.setAuthors(readAuthors(reader));
                    else if (elementName.equals("serviceName"))
                        costDPD.setServiceName(readCharacters(reader));
                        //book.setTitle(readCharacters(reader));
                    else if (elementName.equals("cost"))
                        costDPD.setCost(readCharacters(reader));
                        //book.setCategory(readCategory(reader));
                    else if (elementName.equals("days"))
                        costDPD.setDays(readInt(reader));
                        //book.setYear(readInt(reader));
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return costDPD;
            }
        }
        throw new XMLStreamException("Premature end");
    }

    private String readCharacters(XMLStreamReader reader) throws XMLStreamException {
        StringBuilder result = new StringBuilder();
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.CHARACTERS:
                case XMLStreamReader.CDATA:
                    result.append(reader.getText());
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return result.toString();
            }
        }
        throw new XMLStreamException("Premature end");
    }

    private int readInt(XMLStreamReader reader) throws XMLStreamException {
        String characters = readCharacters(reader);
        try {
            return Integer.valueOf(characters);
        } catch (NumberFormatException e) {
            throw new XMLStreamException("Invalid integer: " + characters);
        }
    }

}
