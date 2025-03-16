package com.alexandrei.cafeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Order : Screen("order")
    // Add more screens if needed
}