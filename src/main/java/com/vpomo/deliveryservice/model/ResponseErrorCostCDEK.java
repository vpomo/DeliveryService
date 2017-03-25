package com.vpomo.deliveryservice.model;

import java.util.List;

/**
 * @author Pomogalov V.A.
 *
 * Вспомогательный объект для чтения сообщения об ошибке от сервера СДЭК
 * при запросе на расчет стоимости доставки товара
 */

public class ResponseErrorCostCDEK {
    private List<ErrorCostCDEK> errorList;

    public List<ErrorCostCDEK> getError() {
        return errorList;
    }

    public void setError(List<ErrorCostCDEK> error) {
        this.errorList = error;
    }

    public String toString() {
        return errorList.toString();
    }
}
