package com.vpomo.deliveryservice.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.omg.CORBA.portable.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Pomogalov V.A.
 */

public class ReadOrderCDEK {
    public void viewStatusAllOrder(String source) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(new InputSource(new ByteArrayInputStream(source.getBytes("utf-8"))));

            // Получаем корневой элемент
            Node root = document.getDocumentElement();

            System.out.println("\n\n\n List of all orders:");
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList orders = root.getChildNodes();
            System.out.println("orders.getLength()=" + orders.getLength());
            for (int i = 0; i < orders.getLength(); i++) {
                Node order = orders.item(i);

                // Если нода не текст, то это книга - заходим внутрь
                System.out.println("i = " + i);
                System.out.println("order.getNodeName() = " + order.getNodeName());
                //NodeList nodeList = order.getTextContent();
                System.out.println("order.getTextContent() = " + order.getNamespaceURI());

                if (order.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
                    NodeList orderProps = order.getChildNodes();
                    System.out.println("orderProps.getLength() = " + orderProps.getLength());
                    if (orderProps instanceof Element){
                        Element e = (Element) orderProps;
                        System.out.println("***********************");
                        // работаем как с элементом (у него есть атрибуты и схема)
                    }

                    for (int j = 0; j < orderProps.getLength(); j++) {
                        System.out.println("orderProps.item(j).getNodeName() = " + orderProps.item(j).getNodeName());

                        //Node orderProp = orderProps.item(j).getNodeName();
                        System.out.println("orderProps.item(j).getNodeName() = " + orderProps.item(j).getLocalName());
                        // Если нода не текст, то это один из параметров книги - печатаем
                      //  if (orderProp.getNodeType() != Node.TEXT_NODE) {
                      //      //System.out.println(orderProp.getNodeName() + ":" + orderProp.getChildNodes().item(0).getTextContent());
                      //  }
                    }
                    System.out.println("===========>>>>");
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}


