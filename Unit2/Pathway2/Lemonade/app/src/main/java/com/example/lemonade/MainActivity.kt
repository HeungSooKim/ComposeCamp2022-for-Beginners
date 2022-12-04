package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(2) }

    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> {
                ImageAndtext(
                    labelResId = R.string.label4,
                    descriptionResId = R.string.description4,
                    imageResId = R.drawable.lemon_tree,
                    onClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    })
            }
            2 -> {
                ImageAndtext(
                    labelResId = R.string.label4,
                    descriptionResId = R.string.description4,
                    imageResId = R.drawable.lemon_squeeze,
                    onClick = {
                        squeezeCount--

                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    })
            }
            3 -> {
                ImageAndtext(
                    labelResId = R.string.label4,
                    descriptionResId = R.string.description4,
                    imageResId = R.drawable.lemon_drink,
                    onClick = {
                        currentStep = 4
                    })
            }
            4 -> {
                ImageAndtext(
                    labelResId = R.string.label4,
                    descriptionResId = R.string.description4,
                    imageResId = R.drawable.lemon_restart,
                    onClick = {
                        currentStep = 1
                    })
            }
        }
    }
}

@Composable
fun ImageAndtext(
    labelResId: Int,
    descriptionResId: Int,
    imageResId: Int,
    onClick: () -> Unit,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = stringResource(labelResId))
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(imageResId),
            contentDescription = stringResource(descriptionResId),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp()
    }
}