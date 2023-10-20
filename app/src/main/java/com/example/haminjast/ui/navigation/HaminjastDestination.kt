package com.example.haminjast.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

interface HaminjastDestination {
    val icon: ImageVector
    val route: String
}

object Ads : HaminjastDestination {
    override val icon = Icons.Filled.Home
    override val route = "ads"
}

object Chat : HaminjastDestination {
    override val icon = Icons.Filled.Check
    override val route = "chat"
}

object Me : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "me"
}

object Login : HaminjastDestination {
    override val icon = Icons.Filled.Person
    override val route = "login"
}
