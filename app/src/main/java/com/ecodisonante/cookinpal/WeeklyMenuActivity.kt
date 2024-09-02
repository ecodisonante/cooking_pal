package com.ecodisonante.cookinpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecodisonante.cookinpal.model.Menu
import com.ecodisonante.cookinpal.model.MenuDataProvider
import com.ecodisonante.cookinpal.ui.theme.CookinPalTheme

class WeeklyMenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeeklyMenuDisplay()
        }
    }
}

@Preview
@Composable
fun WeeklyMenuDisplay() {
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
            var i = 1;
            LazyColumn (modifier = Modifier.padding(bottom = 50.dp)) {
                items(MenuDataProvider.menuList) { menu ->
                    MenuRender(menu = menu, "Dia ${i++}")
                }
            }
        }
    }
}

@Composable
fun MenuRender(menu: Menu, dia: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xDDFFFFFF)
            )
        ) {
            Row(
                modifier = Modifier.padding(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.beer2),
                        contentDescription = "Imagen Receta",
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = dia,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Text(
                        text = menu.nombre,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)


                    )
                    Text(
                        text = "Ingredientes",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)

                    )
                    for (ing in menu.ingredientes) {
                        Text(
                            text = "- ${ing.nombre}",
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                    Text(
                        text = "Pasos",
                        fontWeight = FontWeight.Bold
                    )
                    for (ing in menu.pasos) {
                        Text(
                            text = "${ing.orden} - ${ing.proceso}",
                            modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
                        )
                    }
                }
            }
        }
    }

}

