package com.vpomo.deliveryservice.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.vpomo.deliveryservice.model.CostCDEK;
import com.vpomo.deliveryservice.model.ResponseCostCDEK;
import com.vpomo.deliveryservice.model.ResponseErrorCostCDEK;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.StringEntity;

/**
 * @author Pomogalov V.A.
 */

public class ServiceCDEK {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String CDEK_ACCOUNT = "ec1ac09f1e819651dbb400ca7963313d";
    private static final String CDEK_SECURE_PASSWORD = "dc6991a53e056302ec39c0a7fdfd00ec";

    public static void calculateDelivery () throws IOException {
        String url = "http://api.edostavka.ru/calculator/calculate_price_by_json.php";
        String dateExecute = "2016-11-20";
        String senderCityId = "286";
        String receiverCityId = "94";
        String requestJSON = makeRequestCalcJSON(dateExecute, senderCityId, receiverCityId);

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
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                resultResponse.append(output);
                //System.out.println("result = ");
                //System.out.println(resultResponse.toString());

                try {
                    ObjectMapper mapperCostCDEK = new ObjectMapper();
                    ResponseCostCDEK responseCostCDEK = mapperCostCDEK.readValue(resultResponse.toString(), ResponseCostCDEK.class);
                    System.out.println(responseCostCDEK.getResult().toString());
                } catch (UnrecognizedPropertyException e) {
                    ObjectMapper mapperError = new ObjectMapper();
                    ResponseErrorCostCDEK responseErrorCostCDEK = mapperError.readValue(resultResponse.toString(), ResponseErrorCostCDEK.class);
                    System.out.println(responseErrorCostCDEK.getError().toString());
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String makeRequestCalcJSON (String dateExecute, String senderCityId, String receiverCityId) {
        String secureString = DigestUtils.md5Hex(dateExecute + "&" + CDEK_SECURE_PASSWORD);

        String makeJSON = "{" +
                "\"version\":\"1.0\"," +
                "\"dateExecute\":\"" + dateExecute + "\", " +
                "\"authLogin\":\"" + CDEK_ACCOUNT  + "\"," +
                "\"secure\":\"" + secureString + "\"," +

                "\"senderCityId\":\"" + senderCityId + "\"," +
                "\"receiverCityId\":\"" + receiverCityId + "\"," +
                "\"tariffId\":\"138\"," +
                "\"goods\":" +
                "[" + "{" +
                "\"weight\":\"0.3\"," +
                "\"length\":\"10\"," +
                "\"width\":\"7\"," +
                "\"height\":\"5\"" +
                "}," +
                "{" +
                "\"weight\":\"0.1\"," +
                "\"volume\":\"0.1\"" +
                "}" + "]" + "}";

        return makeJSON;
    }
}
