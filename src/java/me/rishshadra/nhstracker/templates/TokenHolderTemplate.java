/*
 * Written by Rish Shadra.
 * There's really not much more to this license header than that.
 * 
 */
package me.rishshadra.nhstracker.templates;

// package me.rishshadra.gmailer;

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
public class TokenHolderTemplate {

    static final HttpTransport httpTransport = new NetHttpTransport();
    static final JsonFactory jsonFactory = new JacksonFactory();
    static final GoogleCredential credential = new GoogleCredential.Builder()
            .setJsonFactory(jsonFactory)
            .setTransport(httpTransport)
            .setClientSecrets("CLIENT_ID", "CLIENT_SECRET")
            .build()
            .setRefreshToken("REFRESH_TOKEN");

    static {
        try {
            credential.refreshToken();
        } catch (IOException ex) {
            Logger.getLogger(TokenHolderTemplate.class.getName()).log(Level.SEVERE, null, ex);
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
