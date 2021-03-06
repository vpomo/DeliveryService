package com.vpomo.deliveryservice.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.exceptions.InvalidPdfException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.codec.digest.DigestUtils;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vpomo.deliveryservice.model.OrderCDEK;
import static com.vpomo.deliveryservice.model.SettingsService.CDEK_ACCOUNT;
import static com.vpomo.deliveryservice.model.SettingsService.CDEK_SECURE_PASSWORD;

/**
 * @author Pomogalov V.A.
 *
 * Класс для отправки запросов к API компании СДЭК
 * Данные отправляются в формате XML-пакетов
 *
 * Для отправки запроса на сервер используется подкласс HttpURLConnection
 * Для печати квитанции в формате PDF используется библиотека iText
 */

public class ServiceOrderCDEK {

    /**
     * Получение списка пунктов выдачи заказа
     *
     * @param cityId
     * @return String
     */
    public String getPvzList(String cityId) {
        String result = "error";
        try {
            result = postRequest("http://int.cdek.ru/pvzlist.php?cityid=" + cityId + "&type=ALL", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Создание нового заказа
     *
     * @param numberAkt
     * @param orderCDEK
     * @return String
     */
    public String newOrder(String numberAkt, OrderCDEK orderCDEK) {
        String result = "error";
        try {
            result = postRequest("http://gw.edostavka.ru:11443/new_orders.php", newOrderXML(numberAkt, orderCDEK));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получение списка всех заказов за заданный промежуток времени
     *
     * @param dateBegin
     * @param dateEnd
     * @return String
     */
    public String requestAllOrderInPeriod(String dateBegin, String dateEnd) {
        String result = "error";
        try {
            result = postRequest("http://int.cdek.ru/info_report.php", viewStatusAllOrderInPeriod(dateBegin, dateEnd));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Удаление заказа
     *
     * @param numberAkt
     * @param numberCDEK
     * @return String
     */
    public String deleteOrder(String numberAkt, String numberCDEK) {
        String result = "error";
        try {
            result = postRequest("http://int.cdek.ru/delete_orders.php", deleteOrderXML(numberAkt, numberCDEK));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param numberCDEK
     * @return String
     */
    public String statusOrderByNumberCDEK(String numberCDEK) {
        String result = "error";
        try {
            result = postRequest("http://int.cdek.ru/status_report_h.php", statusOrderByNumberCDEKXML(numberCDEK));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param numberOrder
     * @param dateOrder
     * @return String
     */
    public String statusOrderByNumberDate(String numberOrder, String dateOrder) {
        String result = "error";
        try {
            result = postRequest("http://int.cdek.ru/status_report_h.php", statusOrderByNumberDateXML(numberOrder, dateOrder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Печать созданного заказа
     *
     * @param numberCDEK
     */
    public void printOrder(String numberCDEK) {
        String result = "error";
        BufferedReader reader;
        try {
            getPDFRequest("http://int.cdek.ru/orders_print.php", printOrderXML(numberCDEK));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Формирование XML-пакета
     *
     * @param numberCDEK
     * @return String
     */
    public String printOrderXML(String numberCDEK) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<OrdersPrint CopyCount=\"2\" OrderCount=\"1\" Date=\"" + dateExecute + "\" Account=\"" + CDEK_ACCOUNT + "\" Secure=\"" + secureString + "\">\n" +
//                "   <Order DispatchNumber=\"" + numberCDEK + "\" Number=\"" + numberOrder + "\" Date=\"" + dateOrder + "\"/>\n" +
                "   <Order DispatchNumber=\"" + numberCDEK + "\" />\n" +
                "</OrdersPrint>\n");
        return XML.toString();
    }

    /**
     * Создание нового заказа
     * Формирование XML-пакета
     *
     * @param numberAkt
     * @param orderCDEK
     * @return String
     */
    public String newOrderXML(String numberAkt, OrderCDEK orderCDEK) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        orderCDEK.setCastItem(2590.00);//Стоимость товара
        orderCDEK.setPaymentItem(2590.00); //Наложенный платеж
        orderCDEK.setDeliveryRecipientCost(490.00); //Дополнительный сбор


        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<DeliveryRequest account=\"" + CDEK_ACCOUNT + "\" date=\"" + dateExecute + "\" number=\"" + numberAkt + "\" orderCount=\"1\" secure=\"" + secureString + "\">\n" +
                "  <Order " +
                " Number=\"" + numberAkt + "\"" +
                " SendCityCode=\"" + orderCDEK.getSendCityCode() + "\"" +
                " RecCityCode=\"" + orderCDEK.getRecCityCode() + "\"" +
                " RecipientName=\"" + orderCDEK.getRecipientName() + "\"" +
                " RecipientEmail=\"" + orderCDEK.getRecipientEmail() + "\"" +
                " Phone=\"" + orderCDEK.getPhone() + "\"" +
                " Comment=\"" + orderCDEK.getComment() + "\"" +
                " DeliveryRecipientCost=\"" + orderCDEK.getDeliveryRecipientCost() + "\"" +
                " RecipientCurrency=\"RUB\"" +
                " ItemsCurrency=\"RUB\"" +
                " tariffTypeCode=\"137\">\n" +
                "   <Address " +
                " PvzCode=\"-\"" +
                " Street=\"" + orderCDEK.getStreetAddress() + "\"" +
                " House=\"" + orderCDEK.getHouseAddress() + "\"" +
                " Flat=\"" + orderCDEK.getFlatAddress() + "\"/>\n" +
                "    <Package " +
                " number=\"1\"" +
                " barCode=\"1\"" +
                " SizeA=\"" + orderCDEK.getLengthSizeA() + "\"" +
                " SizeB=\"" + orderCDEK.getWidthSizeB() + "\"" +
                " SizeC=\"" + orderCDEK.getHeightSizeC() + "\"" +
                " weight=\"" + orderCDEK.getWeightPackage() + "\">\n" +
                "      <Item " +
                " amount=\"" + orderCDEK.getAmountItem() + "\"" +
                " cost=\"" + orderCDEK.getCastItem() + "\"" +
                " wareKey=\"" + orderCDEK.getWareKeyItem() + "\"" +
                " weight=\"" + orderCDEK.getWeightItem() + "\"" +
                " payment=\"" + orderCDEK.getPaymentItem() + "\"/>\n" +
//                "      <AddService " +
//                        " ServiceCode=\"27\"/>\n" +
                "      <AddService " +
                " ServiceCode=\"30\"/>\n" +
                "      <SendAddress " +
                " Street=\"Игнатьевское шоссе\"" +
                " House=\"14/8\"" +
                " Flat=\"-\"" +
                " SenderName=\"Интернет-магазин U-FUN\" />\n" +
                "    </Package>\n" +
                "  </Order>\n" +
                "</DeliveryRequest>\n");
        //System.out.println(XML.toString());
        String returnXML = "";
        try {
            returnXML = URLEncoder.encode(XML.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnXML;
    }

    /**
     * Формирование XML-пакета
     *
     * @param dateBegin
     * @param dateEnd
     * @return String
     */
    public String viewStatusAllOrderInPeriod(String dateBegin, String dateEnd) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<InfoRequest Date=\"" + dateExecute + "\" Account=\"" + CDEK_ACCOUNT + "\" Secure=\"" + secureString + "\">\n" +
                "   <ChangePeriod DateBeg=\"" + dateBegin + "\" DateEnd=\"" + dateEnd + "\"/>\n" +
                "</InfoRequest>\n");
        return XML.toString();
    }

    /**
     * Формирование XML-пакета
     *
     * @param numberAkt
     * @param numberCDEK
     * @return String
     */
    public String deleteOrderXML(String numberAkt, String numberCDEK) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<DeleteRequest account=\"" + CDEK_ACCOUNT + "\" date=\"" + dateExecute + "\" number=\"" + numberCDEK + "\" orderCount=\"1\" secure=\"" + secureString + "\">\n" +
                "   <Order Number=\"" + numberAkt + "\"/>\n" +
                "</DeleteRequest>\n");
        //System.out.println(XML.toString());
        return XML.toString();
    }

    /**
     * Формирование XML-пакета
     *
     * @param numberCDEK
     * @return String
     */
    public String statusOrderByNumberCDEKXML(String numberCDEK) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<StatusReport account=\"" + CDEK_ACCOUNT + "\" date=\"" + dateExecute + "\" showHistory=\"1\" showReturnOrder=\"1\" secure=\"" + secureString + "\">\n" +
                "   <Order DispatchNumber=\"" + numberCDEK + "\"/>\n" +
                "</StatusReport>\n");
        //System.out.println(XML.toString());
        return XML.toString();
    }

    /**
     * Формирование XML-пакета
     *
     * @param numberOrder
     * @param dateOrder
     * @return String
     */
    public String statusOrderByNumberDateXML(String numberOrder, String dateOrder) {
        StringBuilder XML = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = getSecure(dateExecute);
        XML.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<StatusReport account=\"" + CDEK_ACCOUNT + "\" date=\"" + dateExecute + "\" showHistory=\"1\" showReturnOrder=\"1\" secure=\"" + secureString + "\">\n" +
                "   <Order Number=\"" + numberOrder + "\" Date=\"" + dateOrder + "\"/>\n" +
                "</StatusReport>\n");
        System.out.println(XML.toString());
        return XML.toString();
    }

    /**
     * Шифрование строки
     *
     * @param dateExecute
     * @return String
     */
    public String getSecure(String dateExecute) {
        String secureString = DigestUtils.md5Hex(dateExecute + "&" + CDEK_SECURE_PASSWORD);
        return secureString;
    }

    /**
     * Шифрование строки
     *
     * @return String
     */
    public String getSecure() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateExecute = dateFormat.format(new Date());
        String secureString = DigestUtils.md5Hex(dateExecute + "&" + CDEK_SECURE_PASSWORD);
        return secureString;
    }

    /**
     * Отправка запроса по указанному адресу и прием ответа от сервера
     *
     * @param address
     * @param XML
     * @return String
     * @throws MalformedURLException
     * @throws IOException
     */
    public String postRequest(String address, String XML) throws MalformedURLException, IOException {
        String urlParameters = "xml_request=" + XML;
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
        connection.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        connection.disconnect();

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(urlParameters);
        writer.flush();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer resultResponse = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            resultResponse.append(line);
        }
        writer.close();
        reader.close();
        return resultResponse.toString();
    }

    /**
     * Отправка запроса по указанному адресу и прием ответа от сервера в виде файла PDF
     * Сохранение принятого файла
     *
     * @param address
     * @param XML
     * @throws MalformedURLException
     * @throws IOException
     */
    public void getPDFRequest(String address, String XML) throws MalformedURLException, IOException {
        String urlParameters = "xml_request=" + XML;
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
        connection.setUseCaches(false);

        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        connection.disconnect();

        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        writer.write(urlParameters);
        writer.flush();

        PdfReader reader = new PdfReader(connection.getInputStream());
        PdfStamper stamp = null;
        try {
            stamp = new PdfStamper(reader, new FileOutputStream("order.pdf"));
        } catch (InvalidPdfException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        stamp.setFormFlattening(true);
        try {
            stamp.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        writer.close();
        reader.close();
    }
}
