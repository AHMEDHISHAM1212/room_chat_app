package com.example.roomchatapp.ui.model

import com.example.roomchatapp.R

data class Category(
    val id: Int,
    val title: String,
    val imageResID: Int
) {
    companion object {
        fun getCategories() =
            listOf<Category>(
                Category(id = 1, title = "Sports ", imageResID = R.drawable.sports),
                Category(id = 2, title = "Musics ", imageResID = R.drawable.music),
                Category(id = 3, title = "Movies ", imageResID = R.drawable.movies),
            )

        fun getCategoryImageById(categoryId: Int?): Int {
            return when (categoryId) {
                1 -> R.drawable.sports
                2 -> R.drawable.music
                3 -> R.drawable.movies

                else -> {
                    R.drawable.sports
                }
            }
        }

    }

}