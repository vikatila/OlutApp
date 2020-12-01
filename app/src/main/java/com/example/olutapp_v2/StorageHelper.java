package com.example.olutapp_v2;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.olutapp_v2.data.Beer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class StorageHelper {

    private StorageReference storageReference;

    public File downloadImage(String path)
    {
       storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(path);
         File localFile =  null;
        try {
            localFile = File.createTempFile("beerimg", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        storageReference.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Log.d("Download image", taskSnapshot.toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Download image error: ", e.getMessage());
            }
        });


        return localFile;
    }

    public boolean uploadImage(String fileIn, String storagePath)
    {
        Uri fileToUpload = Uri.fromFile(new File(fileIn));
        final boolean[] success = {false};
        storageReference = FirebaseStorage.getInstance().getReference().child(storagePath);

        storageReference.putFile(fileToUpload)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        success[0] = true;
                        Log.d("Upload image", "Success");
                    }
                });
        return success[0];
    }

}
