package com.nela.module.service;

import android.os.RemoteException;

import com.nela.jniLibrary.NelaLogin;

public class NelaServiceBinder extends INelaService.Stub {
    @Override
    public void Nela_login() throws RemoteException {
        NelaLogin.Nela_login();
    }
}
