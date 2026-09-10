package com.example.lab04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LabActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                LabScreen()
            }
        }
    }
}

@Composable
fun LabScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .border(BorderStroke(3.dp, Color(0xFF006400))), // Borde verde exterior de la vista
        contentAlignment = Alignment.Center
    ) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.uvglogo),
            contentDescription = "Escudo de fondo",
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.2f), // Transparencia
            contentScale = ContentScale.Fit
        )

        // Texto de la carátula
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Encabezado
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Universidad del Valle\nde Guatemala",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 34.sp
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Programación de plataformas\nmóviles, Sección 30",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Integrantes y Catedrático
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "INTEGRANTES",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(text = "Diego Ayala", fontSize = 16.sp)
                        Text(text = "José Gonzalez", fontSize = 16.sp)
                        Text(text = "Rodrigo Navas", fontSize = 16.sp)
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "CATEDRÁTICO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Juan Carlos Durini",
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Pie de página
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Diego Ayala",
                    fontSize = 16.sp
                )
                Text(
                    text = "25570",
                    fontSize = 16.sp
                )
            }
        }
    }
}