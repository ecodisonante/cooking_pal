package com.ecodisonante.cookinpal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ecodisonante.cookinpal.model.DataProvider
import com.ecodisonante.cookinpal.model.UserPreferences
import com.ecodisonante.cookinpal.ui.components.CustomAlertInfo
import com.ecodisonante.cookinpal.ui.components.CustomCard
import com.ecodisonante.cookinpal.ui.components.CustomTextField
import com.ecodisonante.cookinpal.ui.components.FatMainButton
import com.ecodisonante.cookinpal.ui.components.MainButton
import com.ecodisonante.cookinpal.ui.theme.CookinPalTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LoginDisplay()
        }
    }
}

@Composable
fun LoginDisplay() {
    CookinPalTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.bg2),
                    contentScale = ContentScale.FillBounds
                )
        ) {
            CustomCard(customHeight = 500) {
                LoginForm()
            }
        }
    }
}

@Composable
fun LoginForm() {
    val context = LocalContext.current
    val usrPref = UserPreferences(context)
    if (usrPref.getUserList() == null) usrPref.saveUserList(DataProvider.usuarios)

    var emailValue by remember { mutableStateOf("") }
    var passwdValue by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var successLogin by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    CustomTextField(
        value = emailValue,
        label = "Correo",
        onValueChange = { emailValue = it }
    )

    Spacer(modifier = Modifier.size(15.dp))

    CustomTextField(
        value = passwdValue,
        label = "Contraseña",
        onValueChange = { passwdValue = it },
        isPassword = true
    )

    Row {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .width(250.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.size(15.dp))

            FatMainButton(
                text = "Ingresar",
                onClick = {
                    val usuario = usrPref.findUserByEmail(emailValue)

                    if (usuario != null && usuario.passwd == passwdValue) {
                        usrPref.saveCurrentUser(usuario)
                        successLogin = true
                        dialogTitle = "Bienvenido ${usuario.name}"
                        dialogMessage = ""
                    } else {
                        successLogin = false
                        dialogTitle = "Credenciales Incorrectas"
                        dialogMessage = "Entiendo que siendo una app de comida, " +
                                "tus dedos de salchicha hayan comedito un error. " +
                                "Puedes intentarlo otra vez."
                    }
                    showDialog = true
                },
            )

            Spacer(modifier = Modifier.size(30.dp))

            MainButton(
                text = "Volver",
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
            )

            Spacer(modifier = Modifier.size(15.dp))

            MainButton(
                text = "Recuperar Contraseña",
                onClick = {
                    context.startActivity(Intent(context, RecoveryActivity::class.java))
                },
            )
        }

        CustomAlertInfo(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onConfirm = {
                if (successLogin) context.startActivity(Intent(context, MainActivity::class.java))
                showDialog = false
            },
            title = dialogTitle,
            message = dialogMessage
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginDisplay()
}

