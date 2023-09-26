package com.example.roomchatapp.ui.model

data class Room(
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val categoryId: Int? = null,
    val ownerId: String? = null
) {

    companion object {
        const val collectionName = "Rooms"
    }
}