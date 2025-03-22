package com.codewithmahad.dicex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithmahad.dicex.ui.theme.DiceXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceXTheme {
                DiceRollerApp()
            }
        }
    }
}
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {

    var dice1 by rememberSaveable { mutableStateOf(1) }
    var dice2 by rememberSaveable { mutableStateOf(6) }

    // Helper function to get the drawable resource based on the dice value
    fun diceImageResource(value: Int): Int {
        return when (value) {
            1 -> R.drawable.perspective_dice_six_faces_one
            2 -> R.drawable.perspective_dice_six_faces_two
            3 -> R.drawable.perspective_dice_six_faces_three
            4 -> R.drawable.perspective_dice_six_faces_four
            5 -> R.drawable.perspective_dice_six_faces_five
            else -> R.drawable.perspective_dice_six_faces_six
        }
    }

    fun getMessageForScore(total: Int): String {
        return when (total) {
            2 -> "Snake eyes! Tough luck, better roll again!"
            3 -> "Every roll has its ups and downs."
            4 -> "Don't give up, Try again!"
            5 -> "Not bad, but you can do better!"
            6 -> "A modest roll; keep pushing for more!"
            7 -> "Great roll – keep that streak going!"
            8 -> "Nice work, you're on a roll!"
            9 -> "Solid roll, Well Done!"
            10 -> "Impressive – almost there!"
            11 -> "Amazing! Nearly perfect!"
            12 -> "Jackpot! Perfect roll, Congratulations!"
            else -> ""
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                dice1 = (1..6).random()
                dice2 = (1..6).random()
            }
            .background(color = Color.Black), contentAlignment = Alignment.Center) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            // Total score Text aligned at the top center
            Text(
                text = "Score: ${dice1 + dice2}",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 52.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(diceImageResource(dice1)),
                    contentDescription = dice1.toString(),
                    modifier = Modifier.size(190.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(Modifier.width(22.dp))
                Image(
                    painter = painterResource(diceImageResource(dice2)),
                    contentDescription = dice2.toString(),
                    modifier = Modifier.size(190.dp),
                    contentScale = ContentScale.Fit
                )
            }
            // Message based on the current score
            val totalScore = dice1 + dice2
            Text(
                text = getMessageForScore(totalScore),
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 36.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(42.dp))
            Text(
                text = "Tap anywhere to roll",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier.padding(bottom = 50.dp)
            )
            Spacer(Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerApp() {
    val innerPadding = 8.dp
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    )
}
