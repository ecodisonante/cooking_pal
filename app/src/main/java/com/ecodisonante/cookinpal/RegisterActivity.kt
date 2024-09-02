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
import com.ecodisonante.cookinpal.model.UserDataProvider
import com.ecodisonante.cookinpal.model.User
import com.ecodisonante.cookinpal.model.UserPreferences
import com.ecodisonante.cookinpal.ui.components.CustomCard
import com.ecodisonante.cookinpal.ui.components.CustomTextField
import com.ecodisonante.cookinpal.ui.components.FatMainButton
import com.ecodisonante.cookinpal.ui.components.MainButton
import com.ecodisonante.cookinpal.ui.theme.CookinPalTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterDisplay()
        }
    }
}

@Composable
fun RegisterDisplay() {
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
            CustomCard(customHeight = 500) { RegisterForm() }
        }
    }
}

@Composable
fun RegisterForm() {
    val context = LocalContext.current
    val usrPref = UserPreferences(context)
    if (usrPref.getUserList() == null) usrPref.saveUserList(UserDataProvider.usuarios)

    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var passwdValue by remember { mutableStateOf("") }

    CustomTextField(
        value = nameValue,
        label = "Nombre",
        onValueChange = { nameValue = it }
    )

    Spacer(modifier = Modifier.size(15.dp))

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
                text = "Registrarse",
                onClick = {
                    val existe = usrPref.findUserByEmail(emailValue)

                    if (existe != null) {
                        Toast.makeText(
                            context,
                            "Ya hay un usuario registrado con ese email",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {

                        val nuevo = User(name = nameValue, email = emailValue, passwd = passwdValue)
                        usrPref.addUserToList(nuevo)

                        Toast.makeText(
                            context,
                            "Bienvenido. ya puedes acceder con tu correo y contraseña",
                            Toast.LENGTH_LONG
                        ).show()

                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                },
            )

            Spacer(modifier = Modifier.size(15.dp))

            MainButton(
                text = "Volver",
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterDisplay()
}
