package com.vpomo.deliveryservice.service;

import com.vpomo.deliveryservice.model.CityCDEK;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static com.vpomo.deliveryservice.model.SettingsService.PATH_FILE_CITY_CDEK;

/**
 * @author Pomogalov V.A.
 *
 * Класс для чтения из файла списка городов, где присутствует компания СДЭК
 * Файл в формате CSV.
 * В результате получаем массив инициализированных объектов,
 * содержащих информацию о населенных пунктах
 */
public class ReadCityCDEK {

    /**
     * Функция чтения из файла
     * Используется объект Scanner
     * @return ArrayList<CityCDEK>
     */
    public ArrayList<CityCDEK> readCityFromFile() {
        ArrayList<CityCDEK> listCity = new ArrayList<>();
        CityCDEK cityCDEK = null;
        Scanner scanner = null;
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(PATH_FILE_CITY_CDEK), "UTF-8"));
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                //System.out.println(line);
                scanner = new Scanner(line);
                cityCDEK = new CityCDEK();
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String data = scanner.next();
    //ID;FullName;CityName;OblName;Center;NalSumLimit;EngName;PostCodeList
                    if (i == 0) {
                        cityCDEK.setId(data);
                    } else if (i == 1) {
                        cityCDEK.setFullName(data);
                    } else if (i == 2) {
                        cityCDEK.setCityName(data);
                    } else if (i == 3) {
                        cityCDEK.setOblName(data);
                    } else if (i == 4) {
                        cityCDEK.setCenter(data);
                    } else if (i == 5) {
                        cityCDEK.setNalSumLimit(data);
                    } else if (i == 6) {
                        cityCDEK.setEngName(data);
                    } else if (i == 7) {
                        cityCDEK.setPostCodeList(data);
                    } else {
                        //System.out.println("Некорректные данные");
                    }
                    i = i + 1;
                }
                i = 0;
                listCity.add(cityCDEK);
                // считываем остальные строки в цикле
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCity;
    }

}
