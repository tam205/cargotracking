package com.example.cargotracking;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.*;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;

public class LocationWorker extends Worker {
    public LocationWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        FirebaseFirestore.getInstance().collection("locations")
                .document("last_checked")
                .set(Collections.singletonMap("checked", System.currentTimeMillis()));

        return Result.success();
    }
}
