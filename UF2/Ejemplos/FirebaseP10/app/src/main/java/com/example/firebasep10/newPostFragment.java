package com.example.firebasep10;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class newPostFragment extends Fragment {

    Button publishButton;
    EditText postConentEditText;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        publishButton = view.findViewById(R.id.publishButton);
        postConentEditText = view.findViewById(R.id.postContentEditText);

        navController = Navigation.findNavController(view);

        publishButton.setOnClickListener(view1 -> publicar());
    }

    private void publicar() {
        String postContent = postConentEditText.getText().toString();

        if (TextUtils.isEmpty(postContent)) {
            postConentEditText.setError("Required");
            return;
        }

        publishButton.setEnabled(false);

        guardarEnFirestore(postContent);
    }

    private void guardarEnFirestore(String postContent) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Post post;
        if (user.getPhotoUrl()!=null){
            post = new Post(user.getUid(), user.getDisplayName(), user.getPhotoUrl().toString(), postContent,  Timestamp.now());
        }else{
            post = new Post(user.getUid(), user.getDisplayName(), null, postContent, Timestamp.now());
        }


        FirebaseFirestore.getInstance().collection("posts")
                .add(post)
                .addOnSuccessListener(documentReference -> navController.popBackStack());
    }
}