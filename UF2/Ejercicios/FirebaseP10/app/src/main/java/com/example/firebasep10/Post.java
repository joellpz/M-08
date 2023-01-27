package com.example.firebasep10;

import com.google.firebase.Timestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post {
    public String uid;
    public String docid;
    public String author;
    public String authorPhotoUrl;
    public String content;
    public String mediaUrl;
    public String mediaType;
    public Timestamp timestamp;
    public Map<String, Boolean> likes = new HashMap<>();

    // Constructor vacio requerido por Firestore
    public Post() {}
    public Post(String uid, String author, String authorPhotoUrl, String content, String mediaUrl, String mediaType, Timestamp timestamp) {
        this.uid = uid;
        this.author = author;
        this.authorPhotoUrl = authorPhotoUrl;
        this.content = content;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        this.timestamp = timestamp;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }
}