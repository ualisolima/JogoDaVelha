package com.example.wallinsondeivesbatistalima.jogodavelha;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by wallinsondeivesbatistalima on 6/28/16.
 */
public class Conection {
    Socket client;

    protected void conectServer(){
        try {
            client = new Socket("192.168.1.11", 4444);
            System.out.println(" Conectou  : paz filhos da puta");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
