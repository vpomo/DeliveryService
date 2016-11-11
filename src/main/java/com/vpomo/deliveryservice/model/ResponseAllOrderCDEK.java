package com.vpomo.deliveryservice.model;

import java.util.List;

/**
 * @author Pomogalov V.A.
 */

public class ResponseAllOrderCDEK {

    private List<ResponseOrderCDEK> responseOrderCDEKs;

    public List<ResponseOrderCDEK> getResponseOrderCDEKs() {
        return responseOrderCDEKs;
    }

    public void setResponseOrderCDEKs(List<ResponseOrderCDEK> responseOrderCDEKs) {
        this.responseOrderCDEKs = responseOrderCDEKs;
    }

    @Override
    public String toString() {
        return ("{" + "}");
    }
}
