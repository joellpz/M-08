package com.example.firebasep10;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class homeFragment extends Fragment {

    private NavController navController;
    public AppViewModel appViewModel;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.gotoNewPostFragmentButton).setOnClickListener(v -> navController.navigate(R.id.newPostFragment));

        RecyclerView postsRecyclerView = view.findViewById(R.id.postsRecyclerView);

        Query query = FirebaseFirestore.getInstance().collection("posts").limit(50).orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .setLifecycleOwner(this)
                .build();

        postsRecyclerView.setAdapter(new PostsAdapter(options));

        appViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
    }

    class PostsAdapter extends FirestoreRecyclerAdapter<Post, PostsAdapter.PostViewHolder> {
        public PostsAdapter(@NonNull FirestoreRecyclerOptions<Post> options) {
            super(options);
        }

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_post, parent, false));
        }

        @Override
        protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull final Post post) {
            Glide.with(getContext()).load(post.authorPhotoUrl).circleCrop().into(holder.authorPhotoImageView);
            holder.authorTextView.setText(post.author);
            holder.contentTextView.setText(post.content);
            holder.dateTextView.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(post.timestamp.toDate()).toString());
            if (!post.uid.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                holder.trashImageView.setVisibility(View.GONE);
                holder.retweetImageView.setOnClickListener(view -> retweetPost(post));
            } else {
                holder.retweetImageView.setVisibility(View.GONE);
                holder.trashImageView.setOnClickListener(view -> FirebaseFirestore.getInstance().collection("posts").document(post.docid).delete());
            }

            // Gestion de likes
            final String postKey = getSnapshots().getSnapshot(position).getId();
            final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if (post.likes.containsKey(uid))
                holder.likeImageView.setImageResource(R.drawable.like_on);
            else
                holder.likeImageView.setImageResource(R.drawable.like_off);
            holder.numLikesTextView.setText(String.valueOf(post.likes.size()));
            holder.likeImageView.setOnClickListener(view -> FirebaseFirestore.getInstance().collection("posts")
                    .document(postKey)
                    .update("likes." + uid, post.likes.containsKey(uid) ?
                            FieldValue.delete() : true));
            // Miniatura de media
            if (post.mediaUrl != null) {
                holder.mediaImageView.setVisibility(View.VISIBLE);
                if ("audio".equals(post.mediaType)) {
                    Glide.with(requireView()).load(R.drawable.audio).centerCrop().into(holder.mediaImageView);
                } else {
                    Glide.with(requireView()).load(post.mediaUrl).centerCrop().into(holder.mediaImageView);
                }
                holder.mediaImageView.setOnClickListener(view -> {
                    appViewModel.postSeleccionado.setValue(post);
                    navController.navigate(R.id.mediaFragment);
                });
            } else {
                holder.mediaImageView.setVisibility(View.GONE);
            }

            if (post.retweetFrom != null) {
                holder.retweetTextView.setText(post.retweetFrom + " dijo:");
            } else {
                holder.retweetTextView.setVisibility(View.GONE);
            }
        }

        private void retweetPost(Post post) {
            Task<DocumentSnapshot> copiedPost = FirebaseFirestore.getInstance().collection("posts").document(post.docid).get();
            copiedPost.addOnSuccessListener(documentSnapshot -> {
                Map<String, Object> data = documentSnapshot.getData();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseFirestore.getInstance().collection("users").document(user.getUid())
                        .get()
                        .addOnSuccessListener(documentSnapshot1 -> {
                            String userPhoto, author;
                            if (documentSnapshot1.exists()) {
                                if (documentSnapshot1.get("profileName") != null)
                                    author = documentSnapshot1.get("profileName").toString();
                                else author = user.getEmail();

                                if (documentSnapshot1.get("profilePhoto") != null) {
                                    userPhoto = documentSnapshot1.get("profilePhoto").toString();
                                } else {
                                    userPhoto = "R.drawable.user";
                                }
                            } else {
                                author = user.getDisplayName();
                                userPhoto = user.getPhotoUrl().toString();
                            }
                            Post post1 = new Post(
                                    user.getUid(),
                                    author,
                                    userPhoto,
                                    (data.get("content") != null ? data.get("content").toString() : null),
                                    (data.get("mediaUrl") != null ? data.get("mediaUrl").toString() : null),
                                    (data.get("mediaType") != null ? data.get("mediaType").toString() : null),
                                    Timestamp.now(),
                                    data.get("author").toString()
                            );

                            FirebaseFirestore.getInstance().collection("posts")
                                    .add(post1)
                                    .addOnSuccessListener(documentReference -> {
                                        documentReference.update("docid", documentReference.getId());
                                        appViewModel.setMediaSeleccionado(null, null);

                                        //SCROLL UP
                                        int viewId = Objects.requireNonNull(navController.getCurrentDestination()).getId();
                                        navController.popBackStack();
                                        navController.navigate(viewId);
                                    });
                        });
            });
        }

        class PostViewHolder extends RecyclerView.ViewHolder {
            ImageView authorPhotoImageView, likeImageView, mediaImageView, trashImageView, retweetImageView;
            TextView authorTextView, contentTextView, numLikesTextView, dateTextView, retweetTextView;

            PostViewHolder(@NonNull View itemView) {
                super(itemView);
                authorPhotoImageView = itemView.findViewById(R.id.photoImageView);
                likeImageView = itemView.findViewById(R.id.likeImageView);
                mediaImageView = itemView.findViewById(R.id.mediaImage);
                authorTextView = itemView.findViewById(R.id.authorTextView);
                contentTextView = itemView.findViewById(R.id.contentTextView);
                numLikesTextView = itemView.findViewById(R.id.numLikesTextView);
                dateTextView = itemView.findViewById(R.id.dateTextView);
                trashImageView = itemView.findViewById(R.id.trashImageView);
                retweetImageView = itemView.findViewById(R.id.retweetImageView);
                retweetTextView = itemView.findViewById(R.id.retweetTextView);
            }
        }
    }
}


