package com.softdream.drunkersapp.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.softdream.drunkersapp.R

import com.softdream.drunkersapp.presentation.list.CocktailScreen
import com.softdream.drunkersapp.presentation.list.CocktailViewModel

import com.softdream.drunkersapp.ui.theme.DrunkersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            DrunkersAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    DrunkersApp()
                }
            }
        }
    }


    @Composable
    fun DrunkersApp() {
        val cocktailsScreen = stringResource(id = R.string.cocktailsScreen)
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = cocktailsScreen ){
            composable(route = cocktailsScreen){
                val viewModel: CocktailViewModel = hiltViewModel()
                CocktailScreen(state = viewModel.state.value,
                    viewModel = viewModel, onItemClick = {
                    id -> navController.navigate("$cocktailsScreen/$id")
                })
            }
       /* val locations = stringResource(R.string.locationsScreen)
        navController = rememberNavController()
        NavHost(navController, startDestination = locations) {
            composable(route = locations) {
                val viewModel: LocationViewModel = hiltViewModel()
                LocationScreen(
                    viewModel = viewModel,
                    state = viewModel.state.value,
                    onItemClick = { id -> navController.navigate("$locations/$id") })
            }*/
     /*       composable(
                route = "$locations/{location_id}",
                deepLinks = listOf(navDeepLink {
                    uriPattern = "${BuildConfig.DEEPLINK_BASE_URL}{location_id}"
                }),
                arguments = listOf(navArgument("location_id") {
                    type = NavType.StringType
                    defaultValue = ""
                })

            ) {
                val viewModel: LocationDetailViewModel = hiltViewModel()
                LocationDetailScreen(state = viewModel.state.value, viewModel)
            }*/
        }
    }

    @Preview(apiLevel = 29, showBackground = true, showSystemUi = true, device = Devices.NEXUS_5)
    @Composable
    fun DefaultPreview() {
        DrunkersAppTheme {
            DrunkersApp()
        }
    }

}