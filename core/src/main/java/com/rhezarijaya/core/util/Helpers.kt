package com.rhezarijaya.core.util

object Helpers {
    fun isFavoriteInstalled() = try {
        Class.forName("com.rhezarijaya.favorite.ui.fragment.favorite.FavoriteFragment")
        true
    } catch (e: Exception) {
        false
    }
}