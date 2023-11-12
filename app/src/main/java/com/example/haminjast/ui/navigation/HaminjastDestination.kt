package com.example.haminjast.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface HaminjastDestination {
    val icon: ImageVector
    val route: String
}

object Ads : HaminjastDestination {
    override val icon = Icons.Filled.Home
    override val route = "ads"
}

object ChatsList : HaminjastDestination {
    override val icon = Icons.Filled.Check
    override val route = "chat_list"
}

object Me : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "me"
}

object Login : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "login"
}

object PosterDetail : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "poster_detail"

    const val posterIdArg = "poster_id"
    val arguments = listOf(
        navArgument(posterIdArg) { type = NavType.IntType }
    )

    val routeWithArgs = "${route}/{${posterIdArg}}"
}

object Chat : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "chat"
}

object CreatePoster : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "create_poster"
}
