package com.example.roomchatapp.ui.firestoreDB

import com.example.roomchatapp.ui.model.Room
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object RoomsDao {

    private fun getCollections(): CollectionReference {
        val fireStore = Firebase.firestore
        return fireStore.collection(Room.collectionName)
    }

    fun createRoom(
        title: String,
        desc: String,
        ownerId: String,
        selectedCategoryId: Int,
        onCompleteListener: OnCompleteListener<Void>
    ) {
        val collection = getCollections()
        val docRef = collection.document()
        val room = Room(
            id = docRef.id,
            title = title,
            description = desc,
            ownerId = ownerId,
            categoryId = selectedCategoryId
        )

        docRef.set(room)
            .addOnCompleteListener(onCompleteListener)
    }

    fun getAllRooms(onCompleteListener: OnCompleteListener<QuerySnapshot>) {
        getCollections()
            .get()
            .addOnCompleteListener(onCompleteListener)
    }
}