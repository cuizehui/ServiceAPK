package com.nela.common.tools;

import android.os.RemoteException;

import com.nela.module.service.IAliveService;

public class CallWrapper {

    public static void setMessagingUse(boolean use) {
        synchronized (ServiceManager.getIntefaceLockObj()) {
            IAliveService i = ServiceManager.getAliveService();
            if (i != null) {
                try {
                    i.setMessagingUse(use);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
