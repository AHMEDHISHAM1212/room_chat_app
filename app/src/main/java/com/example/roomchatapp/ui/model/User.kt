package com.example.roomchatapp.ui.model

data class User(
    val id: String? = null,
    val userName: String? = null,
    val userEmail: String? = null
) {
    companion object {
        const val CollectionName = "Users"
    }
}
