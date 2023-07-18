package com.norrisboat.ziuq.android.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.norrisboat.ziuq.android.ui.appCurrentDestinationAsState
import com.norrisboat.ziuq.android.ui.startAppDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.spec.Route

@OptIn(ExperimentalMaterialNavigationApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun AppScaffold(
    startRoute: Route,
    navController: NavHostController,
    topBar: @Composable (Destination, NavBackStackEntry?) -> Unit,
    bottomBar: @Composable (Destination) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    val destination = navController.appCurrentDestinationAsState().value
        ?: startRoute.startAppDestination
    val navBackStackEntry = navController.currentBackStackEntry

    // 👇 only for debugging, you shouldn't use currentBackStack API as it is restricted by annotation
    navController.currentBackStack.collectAsState().value.print()

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    // 👇 ModalBottomSheetLayout is only needed if some destination is bottom sheet styled
//    ModalBottomSheetLayout(
//        bottomSheetNavigator = bottomSheetNavigator,
//        sheetShape = RoundedCornerShape(16.dp)
//    ) {
    Scaffold(
//            topBar = { topBar(destination, navBackStackEntry) },
//            bottomBar = { bottomBar(destination) },
        content = content
    )
//    }
}

private fun List<NavBackStackEntry>.print(prefix: String = "stack") {
    val stack = map { it.destination.route }.toTypedArray().contentToString()
    println("$prefix = $stack")
}
