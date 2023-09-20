package com.example.roomchatapp.ui.firestoreDB

import com.example.roomchatapp.ui.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UsersDao {

    private fun getUsersCollection(): CollectionReference {
        val fireStore = Firebase.firestore
        return fireStore.collection("Users")
    }

    fun createUser(user: User, onCompleteListener: OnCompleteListener<Void>) {
        val docRef = getUsersCollection()
            .document(user.id ?: "")
        docRef.set(user)
            .addOnCompleteListener(onCompleteListener)
    }

    fun getUser(userID: String?, onCompleteListener: OnCompleteListener<DocumentSnapshot>) {
        getUsersCollection()
            .document(userID ?: "")
            .get()
            .addOnCompleteListener(onCompleteListener)
    }
}