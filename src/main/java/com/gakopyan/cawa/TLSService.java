package com.gakopyan.cawa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Project: cawa
 * Created by George Akopyan on 25.10.2017
 */
@Service
@Scope("prototype")
public class TLSService {

    @Value("${tls.host}")
    private String tlsHost;

    @Value("${tls.port}")
    private int tlsPort = 9000;

    public String doRequest(String request) throws IOException {

        SocketFactory basicSocketFactory = SocketFactory.getDefault();
        Socket socket = basicSocketFactory.createSocket(tlsHost, tlsPort);
        SSLSocketFactory tlsSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = tlsSocketFactory.createSocket(socket, tlsHost, tlsPort, true);
        InputStream socketInputStream = socket.getInputStream();
        OutputStream socketOutputStream = socket.getOutputStream();

        socketOutputStream.write((request + "\r\n").getBytes());
        socketOutputStream.flush();
        byte[] buffer = new byte[256];

        int bytesRead = socketInputStream.read(buffer);
        String result = new String(Arrays.copyOf(buffer,bytesRead));
        socket.close();
        return result;
    }


}
