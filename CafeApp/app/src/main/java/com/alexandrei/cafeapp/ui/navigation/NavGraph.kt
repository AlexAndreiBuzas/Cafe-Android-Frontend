package com.alexandrei.cafeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.homeNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.loginNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.myOrdersNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.orderNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.profileNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.registerNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.reservationDetailNavGraph
import com.alexandrei.cafeapp.ui.navigation.navgraphbuilder.reservationNavGraph

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        loginNavGraph(navController)
        registerNavGraph(navController)
        homeNavGraph(navController)
        reservationNavGraph(navController)
        profileNavGraph(navController)
        orderNavGraph(navController)
        myOrdersNavGraph(navController)
        reservationDetailNavGraph(navController)
    }
}

