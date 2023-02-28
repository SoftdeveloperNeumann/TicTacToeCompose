package com.example.tictactoecompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoecompose.R
import com.example.tictactoecompose.engin.feldOnClick
import com.example.tictactoecompose.model.TicTacToeViewModel
import com.example.tictactoecompose.engin.screenHeigt
import com.example.tictactoecompose.engin.screenWidth

@Composable
fun Status(name: String) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
    ) {
        Text(
            text = if (TicTacToeViewModel.isWon)
                stringResource(id = R.string.status_win, TicTacToeViewModel.currentUser)
            else if (TicTacToeViewModel.isDraw){
                stringResource(id = R.string.status_draw, TicTacToeViewModel.currentUser, TicTacToeViewModel.currentUserText)
            }
            else
                stringResource(id = R.string.status_play, TicTacToeViewModel.currentUser),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
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
            if (TicTacToeViewModel.isPlaying) bColor else {
                bColor = TicTacToeViewModel.startColor
                TicTacToeViewModel.startColor
            }
        ),
        contentAlignment = Alignment.Center)
    {
        Text(
            text = if (bColor == Color.White) "" else TicTacToeViewModel.allFields[row][col],
//            modifier = Modifier
//                .background(bColor),
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
        )
    }
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
        horizontalAlignment = Alignment.CenterHorizontally
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

@Composable
fun ActionButton(modifier: Modifier) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            TicTacToeViewModel.isPlaying = false
            TicTacToeViewModel.isWon = false
            TicTacToeViewModel.isDraw = false
            TicTacToeViewModel.currentUser = ""
            TicTacToeViewModel.currentUser = "X"
            TicTacToeViewModel.currentUserText = "X"
            TicTacToeViewModel.allFields = Array(3) { Array(3) { "" } }
        }
    ) {
        Text(text = "Reset")
    }
}