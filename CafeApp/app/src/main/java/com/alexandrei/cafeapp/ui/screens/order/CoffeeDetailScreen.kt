package com.alexandrei.cafeapp.ui.screens.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alexandrei.cafeapp.R
import com.alexandrei.cafeapp.data.network.response.order.CustomizationOption
import com.alexandrei.cafeapp.ui.navigation.Screen

@Composable
fun CoffeeDetailScreen(
    coffeeId: String,
    navController: NavHostController,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedSize by remember { mutableStateOf("Medium") }
    val quantity = remember { mutableIntStateOf(1) }
    val scrollState = rememberScrollState()
    var selectedOptions by remember { mutableStateOf(emptySet<CustomizationOption>()) }

    LaunchedEffect(Unit) {
        viewModel.fetchCoffees()
        viewModel.uiState.collect { state ->
            if (state.coffeeList.isNotEmpty()) {
                viewModel.setSelectedCoffee(coffeeId)
                viewModel.loadCustomizations()
                //cancel()
            }
        }
        //viewModel.loadCustomizations()
    }

    LaunchedEffect(uiState.orderPlaced) {
        if (uiState.orderPlaced) {
            kotlinx.coroutines.delay(3000)
            viewModel.resetOrderPlaced()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        uiState.selectedCoffee?.name?.let { name ->
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.latte),
            contentDescription = "Coffee Image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Select Size:",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Small", "Medium", "Large").forEach { size ->
                FilterChip(
                    selected = selectedSize == size,
                    onClick = { selectedSize = size },
                    label = { Text(size) },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Customizations:")

        val groupedOptions = uiState.customizations.groupBy { it.category }

        groupedOptions.forEach { (category, options) ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category,
                modifier = Modifier.padding(vertical = 4.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Column {
                options.forEach { option ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = selectedOptions.contains(option),
                                onValueChange = {
                                    selectedOptions =
                                        if (it) selectedOptions + option else selectedOptions - option
                                }
                            )
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = selectedOptions.contains(option),
                            onCheckedChange = null
                        )
                        Text(
                            text = option.option,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Quantity:",
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            IconButton(onClick = { if (quantity.intValue > 1) quantity.intValue-- }) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease")
            }
            Text(text = quantity.intValue.toString())
            IconButton(onClick = { quantity.intValue++ }) {
                Icon(Icons.Default.Add, contentDescription = "Increase")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val coffeeName = uiState.selectedCoffee?.name ?: return@Button
                val customizations = selectedOptions.toList()

                viewModel.placeOrder(
                    coffeeType = coffeeName,
                    size = selectedSize,
                    customizations = customizations
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Place Order")
        }

        LaunchedEffect(uiState.orderPlaced) {
            if (uiState.orderPlaced) {
                navController.navigate(Screen.MyOrders.route) {
                    popUpTo(Screen.Home.route)
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(32.dp))
}