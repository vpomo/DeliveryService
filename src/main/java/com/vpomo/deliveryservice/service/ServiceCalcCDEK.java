package com.vpomo.deliveryservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.vpomo.deliveryservice.model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.entity.StringEntity;

import static com.vpomo.deliveryservice.model.SettingsService.CDEK_ACCOUNT;
import static com.vpomo.deliveryservice.model.SettingsService.CDEK_SECURE_PASSWORD;

/**
 * @author Pomogalov V.A.
 */

public class ServiceCalcCDEK {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static ResponseCostCDEK calculateDelivery(String dateExecute, String senderCityId, String receiverCityId,
                                            List<GoodsShipment> listGoods) throws IOException {
        String url = "http://api.edostavka.ru/calculator/calculate_price_by_json.php";
        ResponseCostCDEK responseCostCDEK = new ResponseCostCDEK();
        String requestJSON = makeRequestCalcJSON(dateExecute, senderCityId, receiverCityId, listGoods);
        String textError = "";

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(url);

            // add header
            request.setHeader("User-Agent", USER_AGENT);
            request.addHeader("accept", "application/json");
            request.addHeader("charset", "utf-8");

            StringEntity stringEntity = new StringEntity(requestJSON);
            stringEntity.setContentType("application/json");
            request.setEntity(stringEntity);

            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            StringBuffer resultResponse = new StringBuffer();

            while ((output = br.readLine()) != null) {
                resultResponse.append(output);
                //System.out.println("result = ");
                //System.out.println(resultResponse.toString());

                try {
                    ObjectMapper mapperCostCDEK = new ObjectMapper();
                    responseCostCDEK = mapperCostCDEK.readValue(resultResponse.toString(), ResponseCostCDEK.class);
                } catch (UnrecognizedPropertyException e) {
                    ObjectMapper mapperError = new ObjectMapper();
                    ResponseErrorCostCDEK responseErrorCostCDEK = mapperError.readValue(resultResponse.toString(), ResponseErrorCostCDEK.class);

                    Iterator<ErrorCostCDEK> errorIterator = responseErrorCostCDEK.getError().iterator();
                    while (errorIterator.hasNext()) {
                        textError = textError + errorIterator.next().getText() + "\n";
                    }
                    responseCostCDEK.getResult().setTextError(textError);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCostCDEK;
    }

    private static String makeRequestCalcJSON(String dateExecute, String senderCityId, String receiverCityId, List<GoodsShipment> listGoods) {
        String secureString = DigestUtils.md5Hex(dateExecute + "&" + CDEK_SECURE_PASSWORD);
        String makeReguestJSON = "{" +
                "\"version\":\"1.0\"," +
                "\"dateExecute\":\"" + dateExecute + "\"," +
                "\"authLogin\":\"" + CDEK_ACCOUNT + "\"," +
                "\"secure\":\"" + secureString + "\"," +

                "\"senderCityId\":\"" + senderCityId + "\"," +
                "\"receiverCityId\":\"" + receiverCityId + "\"," +
                "\"tariffId\":\"137\"," +

                goodsToJSON(listGoods) +

                "}";

        return makeReguestJSON;
    }

    public static Boolean checkDataGoods(List<GoodsShipment> listGoods) {
        Boolean sucess = true;
        int numberGoods = listGoods.size();
        if (numberGoods > 0) {
            for (int i = 0; i < numberGoods; i++) {
                if (listGoods.get(i).getWeight() <= 0) {
                    sucess = false;
                }
                if ((listGoods.get(i).getLength() <= 0) || (listGoods.get(i).getWidth() <= 0) || (listGoods.get(i).getHeight() <= 0)) {
                    if (listGoods.get(i).getVolume() <= 0) {
                        sucess = false;
                    }
                }
            }
        } else {
            sucess = false;
        }
        return sucess;
    }

    public static String goodsToJSON(List<GoodsShipment> listGoods) {
        String result = "\"goods\":" + "[";
        int numberGoods = listGoods.size();
        for (int i = 0; i < numberGoods; i++) {
            if ((listGoods.get(i).getLength() > 0) & (listGoods.get(i).getWidth() > 0) & (listGoods.get(i).getHeight() > 0)) {
                result = result + "{" +
                "\"weight\":\"" + listGoods.get(i).getWeight() + "\"," +
                "\"length\":\"" + listGoods.get(i).getLength() + "\"," +
                "\"width\":\"" + listGoods.get(i).getWidth() + "\"," +
                "\"height\":\"" + listGoods.get(i).getHeight() + "\"";
                if (i == (numberGoods - 1)) {
                    result = result + "}";
                } else {
                    result = result + "},";
                }
            } else {
                result = result + "{" +
                "\"weight\":\"" + listGoods.get(i).getWeight() + "\"," +
                "\"volume\":\"" + listGoods.get(i).getVolume() + "\"";
                if (i == (numberGoods - 1)) {
                    result = result + "}";
                } else {
                    result = result + "},";
                }
            }
        }
        result = result + "]";
        return result;
    }

}
