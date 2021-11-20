package com.mousavi.composeinstagramlikeloading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.mousavi.composeinstagramlikeloading.ui.theme.ComposeInstagramLikeLoadingTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInstagramLikeLoadingTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    InfiniteLoading1()
                    InfiniteLoading2()
                    InfiniteLoading3()
                }
            }
        }
    }
}

@Composable
fun InfiniteLoading3() {

    var anim by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(Unit) {
        animate(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000)
            )
        ) { value, _ ->
            anim = value
        }
    }

    Box(
        modifier = Modifier
            .size(width = 210.dp, height = 50.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Box(
            modifier = Modifier
                .scale(anim)
                .clip(shape = CircleShape)
                .size(50.dp)
                .background(Color.Gray)
        )

        Box(
            modifier = Modifier
                .graphicsLayer { translationX = anim * 80.dp.toPx() }
                .clip(shape = CircleShape)
                .size(50.dp)
                .background(Color.Gray)
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(x = 80.dp.roundToPx(), y = 0) }
                .graphicsLayer { translationX = anim * 80.dp.toPx() }
                .clip(shape = CircleShape)
                .size(50.dp)
                .background(Color.Gray)
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(x = 160.dp.roundToPx(), y = 0) }
                .scale(1 - anim)
                .clip(shape = CircleShape)
                .size(50.dp)
                .background(Color.Gray)
        )
    }


}

@Composable
fun InfiniteLoading2() {
    val animatedValues = (1..3).mapIndexed { index, value ->
        var anim by remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(Unit) {
            delay(30L * index)
            animate(
                initialValue = 0f,
                targetValue = 30f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 300, delayMillis = 200),
                    repeatMode = RepeatMode.Reverse
                ),
            ) { value, _ ->
                anim = value * (3 - index) / 3 * 1.2f
            }
        }

        anim
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        animatedValues.forEach {
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = -it.dp)
                    .padding(horizontal = 4.dp)
                    .clip(shape = CircleShape)
                    .size(50.dp)
                    .background(Color.Gray)
            )
        }
    }
}


@Composable
fun InfiniteLoading1() {
    val animatedValues = (1..3).mapIndexed { index, value ->
        var anim by remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(Unit) {
            delay(80L * index)

            animate(
                initialValue = 0f,
                targetValue = 20f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 300),
                    repeatMode = RepeatMode.Reverse
                ),
            ) { value, _ ->
                anim = value
            }
        }

        anim
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        animatedValues.forEach {
            Box(
                modifier = Modifier
                    .offset(x = 0.dp, y = -it.dp)
                    .padding(horizontal = 4.dp)
                    .clip(shape = CircleShape)
                    .size(50.dp)
                    .background(Color.Gray)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeInstagramLikeLoadingTheme {
    }
}