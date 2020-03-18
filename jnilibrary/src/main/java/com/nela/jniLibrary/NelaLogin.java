package com.nela.jniLibrary;

public class NelaLogin {
    public NelaLogin() {
        super();
    }

    public static String Nela_login() {
     return  NelaLoginJNI.Nela_login();
    }
}
