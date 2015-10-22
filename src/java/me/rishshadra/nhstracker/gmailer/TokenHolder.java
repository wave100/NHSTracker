/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.rishshadra.nhstracker.gmailer;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish Shadra <rshadra@gmail.com>
 */
public class TokenHolder {

    static final HttpTransport httpTransport = new NetHttpTransport();
    static final JsonFactory jsonFactory = new JacksonFactory();
    static final GoogleCredential credential = new GoogleCredential.Builder()
            .setJsonFactory(jsonFactory)
            .setTransport(httpTransport)
            .setClientSecrets("203382648785-qmr1llj1vv2p698f86dhldgufurboa0b.apps.googleusercontent.com", "4h5luyrOvoJ2CWlVhygQelzO")
            .build()
            .setRefreshToken("1/j8bsdm7oA-I2YV-Db96zLKh5c7iL2Y_vJ6W7i013k3dIgOrJDtdun6zK6XiATCKT");

    static {
        try {
            credential.refreshToken();
        } catch (IOException ex) {
            Logger.getLogger(TokenHolder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private static String getToken() throws IOException {
//        if (credential.getExpiresInSeconds() < 30) {
//            credential.refreshToken();
//        }
//
//        return credential.getAccessToken();
//    }

    private static GoogleCredential getCredential() throws IOException {
        if (credential.getExpiresInSeconds() < 30) {
            credential.refreshToken();
        }

        return credential;
    }

    public static Gmail getGmail() throws IOException {
        return new com.google.api.services.gmail.Gmail.Builder(httpTransport, jsonFactory, getCredential()).setApplicationName("NHSTracker").build();
    }
}
