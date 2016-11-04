package com.vpomo.deliveryservice.view;

import com.vpomo.deliveryservice.service.ServiceCDEK;

import static com.vpomo.deliveryservice.service.ServiceCDEK.*;
import static com.vpomo.deliveryservice.service.ServiceDPD.getServiceCostByParcels2;

/**
 * @author Pomogalov V.A.
 */

public class MainDelivery {
    public static void main(String args[]) throws Exception {
        //getCitiesCashPay();
        //getServiceCostByParcels2();
        ServiceCDEK serviceCDEK = new ServiceCDEK();
        ServiceCDEK.calculateDelivery();
    }

}
