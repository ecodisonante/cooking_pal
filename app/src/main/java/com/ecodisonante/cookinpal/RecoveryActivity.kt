package com.ecodisonante.cookinpal

import android.content.Intent
import android.os.Bundle
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

class RecoveryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecoveryDisplay()
        }
    }
}

@Composable
fun RecoveryDisplay() {
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
            CustomCard {
                RecoveryForm()
            }
        }
    }
}


@Composable
fun RecoveryForm() {
    val context = LocalContext.current
    val usrPref = UserPreferences(context)
    if (usrPref.getUserList() == null) usrPref.saveUserList(DataProvider.usuarios)

    var emailValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    CustomTextField(
        value = emailValue,
        label = "Correo",
        onValueChange = { emailValue = it }
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
                text = "Recuperar",
                onClick = {
                    val usuario = usrPref.findUserByEmail(emailValue)

                    if (usuario != null) {
                        message = "tu clave es ${usuario.passwd}"
                    } else {
                        message = "usuario no encontrado"
                    }

                    showDialog = true
                },
            )

            Spacer(modifier = Modifier.size(15.dp))

            MainButton(
                text = "Volver",
                onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
            )

            CustomAlertInfo(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                onConfirm = { showDialog = false },
                title = "Revisa tu  correo",
                message = "Hemos enviado un mensaje para que recuperes tu contraseña." +
                        "\n\n(psst... $message)"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecoveryPreview() {
    RecoveryDisplay()
}

