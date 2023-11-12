package com.example.haminjast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.haminjast.ui.navigation.Ads
import com.example.haminjast.ui.navigation.ChatsList
import com.example.haminjast.ui.navigation.CreatePoster
import com.example.haminjast.ui.navigation.Login
import com.example.haminjast.ui.navigation.Me
import com.example.haminjast.ui.navigation.PosterDetail
import com.example.haminjast.ui.navigation.navigateSingleTopTo
import com.example.haminjast.ui.navigation.navigateToPosterDetail
import com.example.haminjast.ui.screen.MeScreen
import com.example.haminjast.ui.screen.ads.AdsScreen
import com.example.haminjast.ui.screen.chat.ChatsListScreen
import com.example.haminjast.ui.screen.common.HaminjastBottomNavigationBar
import com.example.haminjast.ui.screen.createPoster.CreatePosterScreen
import com.example.haminjast.ui.screen.login.LoginScreen
import com.example.haminjast.ui.screen.posterDetail.PosterDetailScreen
import com.example.haminjast.ui.theme.HaminjastTheme
import com.example.haminjast.ui.theme.NavBarBlue
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

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
                    val showBottomNav =
                        listOf(Ads, ChatsList, Me).map { it.route }
                            .contains(currentDestination?.route)

                    Scaffold(
                        bottomBar = {
                            if (showBottomNav) {
                                HaminjastBottomNavigationBar(
                                    currentDestinationRoute = currentDestination?.route,
                                    onItemClicked = { route ->
                                        navController.navigateSingleTopTo(route)
                                    }
                                )
                            }
                        },
                        content = { innerPadding ->
                            MainNavHost(navController = navController, innerPadding = innerPadding)
                        },
                        floatingActionButton = {
                            if (currentDestination?.route == Ads.route) {
                                ExtendedFloatingActionButton(
                                    onClick = {
//                                        navController.navigateSingleTopTo(CreatePoster.route)
                                        navController.navigateToPosterDetail(0)
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_plus),
                                            null,
                                            tint = Color.White
                                        )
                                    },
                                    text = {
                                        Text(
                                            modifier = Modifier.padding(bottom = 2.dp),
                                            text = stringResource(id = R.string.new_poster),
                                            style = TextStyle(
                                                fontSize = 14.sp,
                                                fontFamily = VazirFont,
                                                fontWeight = FontWeight(400),
                                                color = Color.White,
                                                textAlign = TextAlign.Center,
                                            )
                                        )
                                    },
                                    containerColor = PrimaryBlack
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(currentDestinationRoute: String?, onItemClicked: (String) -> Unit = {}) {
    NavigationBar(modifier = Modifier.height(64.dp), containerColor = Color.White) {
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White),
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_haminjast),
                    contentDescription = stringResource(id = R.string.ads),
                    tint = if (currentDestinationRoute == Ads.route || currentDestinationRoute == null) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.ads),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xCC3A3A3A),
                        textAlign = TextAlign.Right,
                    ),
                    color = if (currentDestinationRoute == Ads.route || currentDestinationRoute == null) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
            selected = currentDestinationRoute == Ads.route || currentDestinationRoute == null,
            onClick = {
                onItemClicked(Ads.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_chat_2),
                    contentDescription = stringResource(id = R.string.chat),
                    tint = if (currentDestinationRoute == ChatsList.route) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
            label = {
                Text(
                    stringResource(id = R.string.chat),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xCC3A3A3A),
                        textAlign = TextAlign.Right,
                    ),
                    color = if (currentDestinationRoute == ChatsList.route) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
            selected = currentDestinationRoute == ChatsList.route,
            onClick = {
                onItemClicked(ChatsList.route)
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(id = R.string.me),
                    tint = if (currentDestinationRoute == Me.route) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
            selected = currentDestinationRoute == Me.route,
            label = {
                Text(
                    stringResource(id = R.string.me),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(400),
                        color = Color(0xCC3A3A3A),
                        textAlign = TextAlign.Right,
                    ),
                    color = if (currentDestinationRoute == Me.route) {
                        NavBarBlue
                    } else {
                        PrimaryBlack.copy(alpha = 0.8f)
                    }
                )
            },
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
            AdsScreen(
                onPosterClicked = {
                    navController.navigateToPosterDetail(0)
                }
            )
        }
        composable(route = ChatsList.route) {
            ChatsListScreen()
        }
        composable(route = Me.route) {
            MeScreen(onLoginClicked = { navController.navigateSingleTopTo(Login.route) })
        }
        composable(route = Login.route) {
            LoginScreen()
        }
        composable(
            route = PosterDetail.routeWithArgs,
            arguments = PosterDetail.arguments
        ) { backStackEntry ->
            val posterId = backStackEntry.arguments?.getInt(PosterDetail.posterIdArg)!!
            PosterDetailScreen(posterId, onBackClicked = {
                navController.popBackStack()
            })
        }
        composable(route = CreatePoster.route) {
            CreatePosterScreen(onCloseClicked = {
                navController.popBackStack()
            })
        }
    }
}
