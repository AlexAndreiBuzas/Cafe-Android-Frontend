package com.alexandrei.cafeapp.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object CoffeeDetail : Screen("order") {
        fun withId(coffeeId: String) = "order/$coffeeId"
    }
    data object Reservation : Screen("reservation")
    data object MyOrders : Screen("my-orders")
    data object Profile : Screen("profile")
    data object ReservationDetail : Screen("reservationDetail") {
        fun withSeats(seats: Int) = "reservationDetail/$seats"
    }
}