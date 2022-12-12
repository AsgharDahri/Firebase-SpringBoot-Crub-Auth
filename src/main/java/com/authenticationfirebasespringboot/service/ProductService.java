package com.authenticationfirebasespringboot.service;

import com.authenticationfirebasespringboot.dtos.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    public static final String COLLECTION_NAME = "products";

    public String saveProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document().set(product);

        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String updateProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(product.getName()).set(product);

        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public String deleteProduct(Product product) throws ExecutionException, InterruptedException {

        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection(COLLECTION_NAME).document(product.getName()).delete();

        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public Product getProduct(String name) throws ExecutionException, InterruptedException {

        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Product products = null;
        if(document.exists()){
            products = document.toObject(Product.class);
            return products;
        }
        else{
            return null;
        }
    }
}
