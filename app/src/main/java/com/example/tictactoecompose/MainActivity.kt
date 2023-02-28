package com.example.tictactoecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.tictactoecompose.TicTacToeViewModel.allFields
import com.example.tictactoecompose.TicTacToeViewModel.currentUser
import com.example.tictactoecompose.TicTacToeViewModel.currentUserText
import com.example.tictactoecompose.TicTacToeViewModel.isPlaying
import com.example.tictactoecompose.TicTacToeViewModel.isWon
import com.example.tictactoecompose.TicTacToeViewModel.startColor
import com.example.tictactoecompose.ui.theme.TicTacToeComposeTheme

var screenWidth: Float = 0f
var screenHeigt: Float = 0f

object TicTacToeViewModel : ViewModel() {
    var currentUser by mutableStateOf("X")
    var currentUserText = "X"
    var isPlaying = true
    var isWon = false
    val startColor = Color.White
    var allFields by mutableStateOf(Array(3) { Array(3) { "" } })
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        Status("X")

                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Center
                        ) {
                            Spielfeld(rows = 3, cols = 3)
                            FloatingActionButton(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(BottomEnd),
                                onClick = {
                                    isPlaying = false
                                    isWon = false
                                    currentUser = ""
                                    currentUser = "X"
                                    currentUserText = "X"
                                    allFields = Array(3) { Array(3) { "" } }
                                }
                            ) {
                                Text(text = "Reset")
                            }
                        }
                    }
                }
            }
        }
        val displayMetrics = resources.displayMetrics
        screenHeigt = displayMetrics.heightPixels / resources.displayMetrics.density
        screenWidth = displayMetrics.widthPixels / resources.displayMetrics.density
    }
}

@Composable
fun Status(name: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
    ) {
        Text(
            text = if (isWon)
                stringResource(id = R.string.status_win, currentUser)
            else
                stringResource(id = R.string.status_play, currentUser),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(CenterHorizontally)
                .padding(top = 8.dp),
            fontSize = 24.sp
        )
    }
}

@Composable
fun Feld(modifier: Modifier, row: Int, col: Int) {
    var bColor by remember {
        mutableStateOf(Color.White)
    }
    Box(modifier = modifier
        .fillMaxSize(1f)

        .clickable {
            bColor = feldOnClick(bColor, row, col)
        }
        .background(
            if (isPlaying) bColor else {
                bColor = startColor
                startColor
            }
        ),
        contentAlignment = Center)
    {
        Text(
            text = if (bColor == Color.White) "" else allFields[row][col],
//            modifier = Modifier
//                .background(bColor),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )
    }
}

fun feldOnClick(bColor: Color, row: Int, col: Int): Color {
    var retColor = bColor
    if (!isPlaying) isPlaying = !isPlaying
    if (retColor == Color.White) {

        if (currentUser == "X") {
            retColor = Color.Red
            currentUser = "O"
            currentUserText = "X"
        } else {
            retColor = Color.Green
            currentUser = "X"
            currentUserText = "O"
        }
        allFields[row][col] = currentUserText
        if (checkWin()) {
            isWon = true
            currentUser = currentUserText
        }
    }
    return retColor
}

fun checkWin(): Boolean {
    val rows =
        (allFields[0][0] == allFields[0][1] && allFields[0][0] == allFields[0][2] && allFields[0][0] != "") ||
                (allFields[1][0] == allFields[1][1] && allFields[1][0] == allFields[1][2] && allFields[1][0] != "") ||
                (allFields[2][0] == allFields[2][1] && allFields[2][0] == allFields[2][2] && allFields[2][0] != "")

    val cols =
        (allFields[0][0] == allFields[1][0] && allFields[0][0] == allFields[2][0] && allFields[0][0] != "") ||
                (allFields[0][1] == allFields[1][1] && allFields[0][1] == allFields[2][1] && allFields[0][1] != "") ||
                (allFields[0][2] == allFields[1][2] && allFields[0][2] == allFields[2][2] && allFields[0][2] != "")

    val diagonal =
        (allFields[0][0] == allFields[1][1] && allFields[0][0] == allFields[2][2] && allFields[0][0] != "") ||
                (allFields[0][2] == allFields[1][1] && allFields[0][2] == allFields[2][0] && allFields[0][2] != "")

    return rows || cols || diagonal
}

@Composable
fun Spielfeld(rows: Int, cols: Int) {
    val screenSize = if (screenWidth < screenHeigt) screenWidth else screenHeigt
    Column(
        modifier = Modifier
            .width(screenSize.dp)
            .height(screenSize.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        repeat(rows) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .weight(1f)
            ) {
                repeat(cols) { col ->
                    Feld(
                        Modifier
                            .fillMaxSize(1f)
                            .weight(1f)
                            .border(1.dp, Color.Black),
                        row,
                        col
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTacToeComposeTheme {
        Column {
            Status("Android")
            Spielfeld(rows = 3, cols = 3)
        }
    }
}