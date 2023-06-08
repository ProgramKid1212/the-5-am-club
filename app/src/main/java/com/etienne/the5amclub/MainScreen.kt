package com.etienne.the5amclub

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.etienne.the5amclub.BottomBarScreen
import com.etienne.the5amclub.bottomnavbar.BottomNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {BottomBar(navController = navController)}
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Profile,
        BottomBarScreen.Home,
        BottomBarScreen.Workouts,
        BottomBarScreen.Utilities,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation{
        screens.forEach{ screen ->
            addItem(screen = screen, currentDestination = currentDestination , navController = navController)
        }
    }
}


@Composable
fun RowScope.addItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
  BottomNavigationItem(
      label = {
          Text(text = screen.title)
      },
      icon = {
          Icon(imageVector = screen.icon,
              contentDescription = "Navigation Icon"
          )
      },
      selected = currentDestination?.hierarchy?.any {
          it.route == screen.route
      }==true,
      onClick = {
          navController.navigate(screen.route)
      }
  )

}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}



