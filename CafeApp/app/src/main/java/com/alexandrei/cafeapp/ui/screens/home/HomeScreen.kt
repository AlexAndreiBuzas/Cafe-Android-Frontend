package com.alexandrei.cafeapp.ui.screens.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexandrei.cafeapp.R
import com.alexandrei.cafeapp.ui.components.ItemCard
import com.alexandrei.cafeapp.ui.components.LocationBar
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import com.alexandrei.cafeapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: CoffeeViewModel = hiltViewModel()) {
    val coffeeTypes by viewModel.coffeeTypes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCoffeeTypes()
    }

    Column {
        LocationBar(locationName = "Luar Cetate")

        Spacer(modifier = Modifier.height(16.dp))

        if (coffeeTypes.isNotEmpty()) {
            LazyColumn {
                items(coffeeTypes) { coffee ->
                    ItemCard(
                        title = coffee.name,
                        description = coffee.description,
                        //imageRes = rememberAsyncImagePainter(coffee.imageUrl),
                        imageRes = R.drawable.latte,
                        backgroundColor = Color(0xFFFFF3E0),
                        textColor = Color.Black
                    ) {
                        navController.navigate(Screen.CoffeeDetail.withId(coffee.id.toString()))
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}