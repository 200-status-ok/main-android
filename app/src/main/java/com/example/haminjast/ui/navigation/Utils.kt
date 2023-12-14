package com.example.haminjast.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavHostController.navigateToPosterDetail(posterId:Int) {
    this.navigateSingleTopTo("${PosterDetail.route}/$posterId")
}

fun NavHostController.navigateToChat(conversationID: Long,posterID: Long) {
    this.navigateSingleTopTo("${Chat.route}/$conversationID/$posterID")
}