package com.attozoic.muzejirade.dataService;

import android.util.Log;

import com.attozoic.muzejirade.model.Post;
import com.attozoic.muzejirade.model.iListItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nenadicivan on 9/30/2017.
 */

public class PostServiceFireBase implements PostServiceFireBaseInterface{
    @Override
    public void getItems(FireBaseDatabaseListener callback, String page) {


        Log.d("fatala","service se pali");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Query query = page == null ? mDatabase.child("posts").orderByChild("createdAt").limitToLast(20) : mDatabase.child("posts").orderByChild("createdAt").endAt(page, "createdAt").limitToLast(20);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("fatala","data se pali");
                List<Post> posts = new ArrayList<Post>();

                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        posts.add(0, postSnapshot.getValue(Post.class));
                    }
                }

                 callback.onSuccess((List) posts);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
                Log.d("fatala","error se pali");
            }
        });

    }
}
