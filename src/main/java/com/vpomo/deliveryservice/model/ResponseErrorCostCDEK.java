package com.vpomo.deliveryservice.model;

import java.util.List;

/**
 * @author Pomogalov V.A.
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
