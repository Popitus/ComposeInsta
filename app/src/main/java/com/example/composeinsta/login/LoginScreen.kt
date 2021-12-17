package com.example.composeinsta.login

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinsta.R
import com.example.composeinsta.detail.DetailActivity


/** Login Page **/
@Composable
fun LoginPage(viewModel: LoginViewModel) {


    Box(modifier = Modifier.fillMaxSize()) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), viewModel)
        Footer(Modifier.align(Alignment.BottomCenter), viewModel)
    }
}

/** ------ **/
/** Header **/
@Composable
fun Header(modifier: Modifier) {
    Icon(
        Icons.Default.Close,
        contentDescription = "Close",
        modifier = modifier
    )
}

/** ---- **/
/** Body **/
@Composable
fun Body(modifier: Modifier, viewModel: LoginViewModel) {

    /** Properties **/
    val password: String by viewModel.password.observeAsState("")
    val email: String by viewModel.email.observeAsState("")
    val state: Boolean by viewModel.enable.observeAsState(false)

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ImageLogo()
        TextBoxUser(email) { viewModel.onLoginChanged(it, password) }
        TextBoxPass(password) { viewModel.onLoginChanged(email, it) }
        ForgotPass()
        LoginButton(state)
        SuperDivider()
        ContinueAs()
    }
}

// Body functions
@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo Instagram",
        modifier = Modifier.height(100.dp)
    )
    Spacer(modifier = Modifier.padding(10.dp))
}

@Composable
fun TextBoxUser(userName: String, saveData: (String) -> Unit) {
    OutlinedTextField(
        value = userName,
        onValueChange = { saveData(it) },
        placeholder = { Text(text = "Phone Number, username or email") },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0x40CCCCCC),
            unfocusedBorderColor = Color(0x40CCCCCC)),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(Color(0x20CCCCCC)),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

    )
    Spacer(modifier = Modifier.padding(10.dp))

}

@Composable
fun TextBoxPass(passName: String, saveMail: (String) -> Unit) {
    val passwordIcon = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = passName,
        onValueChange = { saveMail(it) },
        placeholder = { Text(text = "password") },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0x40CCCCCC),
            unfocusedBorderColor = Color(0x40CCCCCC)),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(Color(0x20CCCCCC)),

        trailingIcon = {
            IconButton(onClick = {
                passwordIcon.value = !passwordIcon.value
            }) {
                if (passwordIcon.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.password_eye),
                        contentDescription = "icon visibility off")
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.visibility),
                        contentDescription = "icon visibility on")
                }
            }

        },
        visualTransformation =
        if (passwordIcon.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

    )
    Spacer(modifier = Modifier.padding(10.dp))
}

@Composable
fun ForgotPass() {
    Text(
        text = "Forgot password?",
        textAlign = TextAlign.Right,
        color = Color(0xFF2196F3),
        modifier = Modifier
            .fillMaxWidth(0.9f)

    )
    Spacer(modifier = Modifier.padding(10.dp))

}

@Composable
fun LoginButton(stateButton: Boolean) {
    val context = LocalContext.current

    Button(
        onClick = {
            context.startActivity(Intent(context,DetailActivity::class.java))
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF83C3F5)),
        enabled = stateButton,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(50.dp)

    ) {
        Text(
            text = "Log in",
            fontSize = 15.sp,
            color = Color.White
        )
    }
    Spacer(modifier = Modifier.padding(10.dp))

}

@Composable
fun SuperDivider() {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        Divider(
            modifier = Modifier
                .height(1.dp)
                .width(165.dp)
                .padding(end = 20.dp)
        )
        Text(text = "OR")
        Divider(
            modifier = Modifier
                .height(1.dp)
                .width(165.dp)
                .padding(start = 20.dp)
        )

    }
    Spacer(modifier = Modifier.padding(10.dp))

}

@Composable
fun ContinueAs() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.meta_logo),
            contentDescription = "Meta logo",
            modifier = Modifier.size(30.dp))
        Text(
            text = "Continue as Lucy In The Sky..",
            textAlign = TextAlign.Center,
            color = Color(0xFF2196F3),


            )
    }

    Spacer(modifier = Modifier.padding(10.dp))
}

/** ------ **/
/** Footer **/
@Composable
fun Footer(modifier: Modifier, viewModel: LoginViewModel) {

    val openDialog: Boolean by viewModel.openDialog.observeAsState(false)

    Column(modifier = modifier) {
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(1f)
                .padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Don´t have an account? ")
            Text(
                text = "Sign up",
                color = Color(0xFF2196F3),
                modifier = Modifier
                    .clickable {
                        viewModel.onOpenDialogChanged(true)
                    })
        }
        if (openDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.onOpenDialogChanged(false) },
                title = { Text(text = "Error") },
                text = { Text(text = "Demasiados usuarios en el sistema. Prueba otro día") },
                confirmButton = {
                    Button(onClick = { viewModel.onOpenDialogChanged(false) }) {
                        Text(text = "Ok")
                    }
                },
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))

    }

}



