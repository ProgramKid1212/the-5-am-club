package com.etienne.the5amclub.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etienne.the5amclub.LogIn
import com.etienne.the5amclub.R
import com.etienne.the5amclub.UserViewModel
import com.etienne.the5amclub.ui.CircularProgressBar
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@OptIn(ExperimentalCoroutinesApi::class)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun ProfileScreen(
    //These parameters are solely added for the preview, otherwise it won't work
    preFullName: String? = null,
    preEmail: String? = null,
    preStatus: String? = null,
    isPreview: Boolean? = null,
) {
    AppTheme {
        Surface {
            var FullName by remember {
                mutableStateOf("")
            }
            var Email by remember {
                mutableStateOf("")
            }
            var Status by remember {
                mutableStateOf("")
            }
            var loaded by remember {
                mutableStateOf(false)
            }
            var login by remember {
                mutableStateOf(false)
            }

            if (login) {
                Firebase.auth.signOut()
                val context = LocalContext.current
                val intent = Intent(context, LogIn::class.java)
                context.startActivity(intent)
            }

            if (isPreview == true) {
                loaded = isPreview
            } else {
                val viewModel = viewModel<UserViewModel>()
                val hasLoaded by viewModel.loading.collectAsState()

                loaded = hasLoaded
            }


            //Loaded is checked to see whether the data has loaded yet
            if (loaded == true) {
                if (isPreview == true) {
                    FullName = preFullName.toString()
                    Email = preEmail.toString()
                    Status = preStatus.toString()
                } else {
                    //If isPreview is not true, than the data must be loaded from the database instead
                    val viewModel = viewModel<UserViewModel>()
                    val currentRealtimeUser = viewModel.viewModelUser.value

                    FullName = currentRealtimeUser.userFullName.toString()
                    Email = currentRealtimeUser.userEmail.toString()
                    Status = currentRealtimeUser.userStatus.toString()
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Log.d("Profile Screen", "The profile screen calls code now")

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.avatarcolor),
                            contentDescription = "null",
                            // crop the image if it's not a square
                            modifier = Modifier

                                .size(150.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                                .border(5.dp, Color.DarkGray, CircleShape)
                                .offset(x = 0.dp, y = -2.dp)// add a border (optional)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 2.dp)

                        )


                        Text(
                            text = "Account Details:",
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier/*.offset(x = -80.dp, y = 0.dp)*/.padding(bottom = 25.dp)

                        )

                        Row {


                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = FullName,
                                onValueChange = {},
                                shape = CircleShape,
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .absolutePadding(25.dp)
                            )

                        }

                        Row {


                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = Email,
                                onValueChange = {},
                                shape = CircleShape,
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .absolutePadding(25.dp)
                            )
                        }
                        Row {

                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = Status,
                                onValueChange = {},
                                shape = CircleShape,/*colors = TextFieldDefaults.textFieldColors(Color.Cyan),*/
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .absolutePadding(25.dp)
                            )
                        }


                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "null",


                                // crop the image if it's not a square
                                modifier = Modifier

                                    .size(80.dp) // clip to the circle shape
                                    .border(10.dp, Color.Gray)
                                    .offset(x = 0.dp, y = 0.dp)// add a border (optional)
                                    .padding(top = 2.dp)

                            )

                        }
                        Button(onClick = {
                            login = true
                        }) {
                            Text(text = "Log Out")
                        }

                    }
                }
            } else {


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressBar(true)
                }
            }
        }

    }

}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        Surface {
            //ProfileScreen is passed with test values
            //If passed with true, the profile screen composable is told the data is a preview
            ProfileScreen("Alex Romburgh", "alexromburgh@gmail.com", "Registered", true)
        }
    }
}



