package com.example.lab06

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AdvancedCounterScreen()
            }
        }
    }
}

data class HistoryItem(
    val value: Int,
    val isIncrement: Boolean
)

@Composable
fun AdvancedCounterScreen() {
    var count by remember { mutableIntStateOf(0) }
    var totalIncrements by remember { mutableIntStateOf(0) }
    var totalDecrements by remember { mutableIntStateOf(0) }

    var maxValue by remember { mutableIntStateOf(0) }
    var minValue by remember { mutableIntStateOf(0) }

    val history = remember { mutableStateListOf<HistoryItem>() }

    val totalChanges = totalIncrements + totalDecrements

    fun updateCounter(increment: Boolean) {
        if (increment) {
            count++
            totalIncrements++
        } else {
            count--
            totalDecrements++
        }

        if (totalChanges == 1 && (totalIncrements + totalDecrements) == 1) {
            maxValue = count
            minValue = count
        } else {
            if (count > maxValue) maxValue = count
            if (count < minValue) minValue = count
        }

        history.add(HistoryItem(value = count, isIncrement = increment))
    }

    fun resetAll() {
        count = 0
        totalIncrements = 0
        totalDecrements = 0
        maxValue = 0
        minValue = 0
        history.clear()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Nombre
            Text(
                text = "Diego Ayala",
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            // Contador
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { updateCounter(increment = false) },
                    modifier = Modifier
                        .size(44.dp)
                        .background(color = Color(0xFF3F51B5), shape = CircleShape)
                ) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Menos", tint = Color.White)
                }

                Text(
                    text = count.toString(),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                IconButton(
                    onClick = { updateCounter(increment = true) },
                    modifier = Modifier
                        .size(44.dp)
                        .background(color = Color(0xFF3F51B5), shape = CircleShape)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Más", tint = Color.White)
                }
            }

            // División principal
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.LightGray
            )

            // Estadísticas
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                StatRow(label = "Total incrementos:", value = totalIncrements.toString())
                StatRow(label = "Total decrementos:", value = totalDecrements.toString())
                StatRow(label = "Valor máximo:", value = maxValue.toString())
                StatRow(label = "Valor mínimo:", value = minValue.toString())
                StatRow(label = "Total cambios:", value = totalChanges.toString())
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Historial
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Historial:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                val chunkedHistory = history.chunked(5)
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    chunkedHistory.forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowItems.forEach { item ->
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            color = if (item.isIncrement) Color(0xFF1B8721) else Color(0xFFB3261E),
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = item.value.toString(),
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Reiniciar
        Button(
            onClick = { resetAll() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5)),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Reiniciar", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    }
}