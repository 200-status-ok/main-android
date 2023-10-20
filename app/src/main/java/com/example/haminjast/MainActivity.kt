package com.example.haminjast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.haminjast.ui.navigation.Ads
import com.example.haminjast.ui.navigation.Chat
import com.example.haminjast.ui.navigation.Login
import com.example.haminjast.ui.navigation.Me
import com.example.haminjast.ui.navigation.navigateSingleTopTo
import com.example.haminjast.ui.screen.AdsScreen
import com.example.haminjast.ui.screen.ChatScreen
import com.example.haminjast.ui.screen.MeScreen
import com.example.haminjast.ui.screen.login.LoginScreen
import com.example.haminjast.ui.theme.HaminjastTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                HaminjastTheme {
                    val navController = rememberNavController()
                    val currentBackStack by navController.currentBackStackEntryAsState()
                    val currentDestination = currentBackStack?.destination

                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                currentDestinationRoute = currentDestination?.route,
                                onItemClicked = { route ->
                                    navController.navigateSingleTopTo(route)
                                }
                            )
                        },
                        content = { innerPadding ->
                            MainNavHost(navController = navController, innerPadding = innerPadding)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentDestinationRoute: String?, onItemClicked: (String) -> Unit = {}) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = stringResource(id = R.string.ads)
                )
            },
            label = { Text(stringResource(id = R.string.ads)) },
            selected = currentDestinationRoute == Ads.route || currentDestinationRoute == null,
            onClick = {
                onItemClicked(Ads.route)
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = stringResource(id = R.string.chat)) },
            label = { Text(stringResource(id = R.string.chat)) },
            selected = currentDestinationRoute == Chat.route,
            onClick = {
                onItemClicked(Chat.route)
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = stringResource(id = R.string.me)) },
            selected = currentDestinationRoute == Me.route,
            label = { Text(stringResource(id = R.string.me)) },
            onClick = {
                onItemClicked(Me.route)
            }
        )
    }
}

@Composable
fun MainNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Ads.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = Ads.route) {
            AdsScreen()
        }
        composable(route = Chat.route) {
            ChatScreen()
        }
        composable(route = Me.route) {
            MeScreen(onLoginClicked = { navController.navigateSingleTopTo(Login.route) })
        }
        composable(route = Login.route) {
            LoginScreen()
        }
    }
}
