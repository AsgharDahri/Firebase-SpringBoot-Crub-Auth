package com.authenticationfirebasespringboot.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class firebaseInitializer {
    @PostConstruct
    public void initialization() throws IOException {
        try{
            FileInputStream serviceAccount =
                    new FileInputStream("./ServiceAccountsKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
