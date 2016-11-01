package com.vpomo.deliveryservice.service;

/**
 * Created by Zver on 30.10.2016.
 */

import javax.xml.soap.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

/**
 * @author Pomogalov V.A.
 */

/**
 * Данный код создает запросы getTicket.Метод getTicket используется
 * для получения билета на подготовку информации по списку идентификаторов
 * отправлений. В запросе передается список идентификаторов отправлений.
 * При успешном вызове метод возвращает идентификатор билета.
 * Идентификатор билета нужен для метода getResponseByTicket.
 * В запросе метода указываются до 3000 идентификаторов отправлений и
 * параметры доступа к API Сервиса отслеживания (логин и пароль).
 * Ответ выводится на экран в формате xml.
 */


public class RussianPost {
    public void getTicket() throws Exception {
        /*
        Пример запроса
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                            xmlns:pos="http://fclient.russianpost.org/postserver"
                            xmlns:fcl="http://fclient.russianpost.org">
        <soapenv:Header/>
        <soapenv:Body>
            <pos:ticketRequest>
                <request>
                    <fcl:Item Barcode="RA123456788RU"/>
                    <fcl:Item Barcode="RA123456789RU"/>
                    <fcl:Item Barcode="RA123456780RU"/>
                </request>
                <login>my_login</login>
                <password>my_password</password>
                <language>RUS</language>
            </pos:ticketRequest>
        </soapenv:Body>
        </soapenv:Envelope>

        Пример ответа
        <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
        <S:Body>
            <ns2:ticketResponse xmlns:ns2="http://fclient.russianpost.org/postserver" xmlns:ns3="http://fclient.russianpost.org">
                <value>20150917162048476CLIENTID</value>
            </ns2:ticketResponse>
        </S:Body>
        </S:Envelope>
        */

        //Create a connection
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnFactory.createConnection();
        String url = "https://tracking.russianpost.ru/fc";

        //Create a message
        MessageFactory messageFactory = MessageFactory.newInstance("SOAP 1.1 Protocol");
        SOAPMessage message = messageFactory.createMessage();

        //Create objects that represent the various components of posts
        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();

        envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("pos", "http://fclient.russianpost.org/postserver");
        envelope.addNamespaceDeclaration("fcl", "http://fclient.russianpost.org");
        SOAPElement ticketRequest = body.addChildElement("ticketRequest", "pos");
        SOAPElement request = ticketRequest.addChildElement("request");
        SOAPElement item1 = request.addChildElement("Item", "fcl");
        SOAPElement item2 = request.addChildElement("Item", "fcl");
        SOAPElement item3 = request.addChildElement("Item", "fcl");
        SOAPElement login = ticketRequest.addChildElement("login");
        SOAPElement password = ticketRequest.addChildElement("password");
        SOAPElement language = ticketRequest.addChildElement("language");

        // Fill values
        SOAPFactory sf = SOAPFactory.newInstance();
        Name barcode = sf.createName("Barcode");
        item1.addAttribute(barcode, "RA123456788RU");
        item2.addAttribute(barcode, "RA123456789RU");
        item3.addAttribute(barcode, "RA123456780RU");
        login.addTextNode("myLogin");
        password.addTextNode("myPassword");
        language.addTextNode("RUS");

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

        //Close connection
        connection.close();
    }

}

