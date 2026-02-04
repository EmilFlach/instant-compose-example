package org.example.project

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource

import instant_compose_example.composeapp.generated.resources.Res
import instant_compose_example.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val gradients = remember {
                listOf(
                    GradientInfo("Neon Pickle", listOf(Color(0xFFADFF2F), Color(0xFF32CD32), Color(0xFF006400))),
                    GradientInfo("Angry Flamingo", listOf(Color(0xFFFFB6C1), Color(0xFFFF69B4), Color(0xFFFF1493))),
                    GradientInfo("Sad Blueberry", listOf(Color(0xFF87CEEB), Color(0xFF4682B4), Color(0xFF000080))),
                    GradientInfo("Burnt Toast", listOf(Color(0xFFD2B48C), Color(0xFF8B4513), Color(0xFF4B2E1D), Color(0xFF000000))),
                    GradientInfo("Unicorn Sneeze", listOf(Color(0xFFFFD1DC), Color(0xFFB19CD9), Color(0xFF77DD77), Color(0xFF89CFF0))),
                    GradientInfo("Toxic Sludge", listOf(Color(0xFF00FF00), Color(0xFFADFF2F), Color(0xFF2F4F4F), Color(0xFF000000))),
                    GradientInfo("Electric Bananas", listOf(Color(0xFFFFFF00), Color(0xFFFFE135), Color(0xFFFFD700), Color(0xFFDAA520))),
                    GradientInfo("Cosmic Dust Bunny", listOf(Color(0xFF2E0854), Color(0xFF4B0082), Color(0xFF8A2BE2), Color(0xFF9370DB))),
                    GradientInfo("Spicy Salmon", listOf(Color(0xFFFFA07A), Color(0xFFFA8072), Color(0xFFE9967A), Color(0xFFFF4500))),
                    GradientInfo("Bored Grape", listOf(Color(0xFFE6E6FA), Color(0xFFD8BFD8), Color(0xFFDA70D6), Color(0xFF800080))),
                    GradientInfo("Frozen Peas", listOf(Color(0xFFCCFFCC), Color(0xFF90EE90), Color(0xFF3CB371), Color(0xFF2E8B57))),
                    GradientInfo("Melted Crayon", listOf(Color(0xFFFF0000), Color(0xFFFF7F00), Color(0xFFFFFF00), Color(0xFF00FF00), Color(0xFF0000FF), Color(0xFF4B0082), Color(0xFF8B00FF))),
                    GradientInfo("Zesty Lemonade", listOf(Color(0xFFFFF700), Color(0xFFFFD700), Color(0xFFFFA500))),
                    GradientInfo("Midnight Snack", listOf(Color(0xFF0C0C0C), Color(0xFF1A1A2E), Color(0xFF16213E), Color(0xFF0F3460))),
                    GradientInfo("Atomic Tangerine", listOf(Color(0xFFFF9966), Color(0xFFFF5E62))),
                    GradientInfo("Grumpy Cloud", listOf(Color(0xFFD3D3D3), Color(0xFFA9A9A9), Color(0xFF808080), Color(0xFF696969)))
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "Funny Gradients",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
                items(gradients) { gradient ->
                    GradientCard(gradient)
                }
            }
        }
    }
}

data class GradientInfo(val name: String, val colors: List<Color>)

@Composable
fun GradientCard(gradient: GradientInfo) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) 0.95f else 1f)
    val elevation by animateDpAsState(if (isPressed) 2.dp else 6.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .scale(scale)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { /* Could add navigation or expand here */ }
            ),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = gradient.colors,
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f) // Approximate angle
                    )
                )
        ) {
            // Add a subtle overlay for better depth
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.2f))
                        )
                    )
            )

            Text(
                text = gradient.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp),
                color = Color.White,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = Offset(2f, 2f),
                        blurRadius = 4f
                    )
                )
            )
        }
    }
}