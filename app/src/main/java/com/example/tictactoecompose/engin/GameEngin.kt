package com.example.tictactoecompose.engin

import androidx.compose.ui.graphics.Color
import com.example.tictactoecompose.model.TicTacToeViewModel

var screenWidth: Float = 0f
var screenHeigt: Float = 0f

fun feldOnClick(bColor: Color, row: Int, col: Int): Color {
    var retColor = bColor
    if (!TicTacToeViewModel.isPlaying) TicTacToeViewModel.isPlaying = !TicTacToeViewModel.isPlaying
    if (retColor == Color.White) {

        if (TicTacToeViewModel.currentUser == "X") {
            retColor = Color.Red
            TicTacToeViewModel.currentUser = "O"
            TicTacToeViewModel.currentUserText = "X"
        } else {
            retColor = Color.Green
            TicTacToeViewModel.currentUser = "X"
            TicTacToeViewModel.currentUserText = "O"
        }
        TicTacToeViewModel.allFields[row][col] = TicTacToeViewModel.currentUserText
        if (checkWin()) {
            TicTacToeViewModel.isWon = true
            TicTacToeViewModel.currentUser = TicTacToeViewModel.currentUserText
        }
    }
    return retColor
}

fun checkWin(): Boolean {
    val rows =
        (TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[0][1] && TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[0][2] && TicTacToeViewModel.allFields[0][0] != "") ||
                (TicTacToeViewModel.allFields[1][0] == TicTacToeViewModel.allFields[1][1] && TicTacToeViewModel.allFields[1][0] == TicTacToeViewModel.allFields[1][2] && TicTacToeViewModel.allFields[1][0] != "") ||
                (TicTacToeViewModel.allFields[2][0] == TicTacToeViewModel.allFields[2][1] && TicTacToeViewModel.allFields[2][0] == TicTacToeViewModel.allFields[2][2] && TicTacToeViewModel.allFields[2][0] != "")

    val cols =
        (TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[1][0] && TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[2][0] && TicTacToeViewModel.allFields[0][0] != "") ||
                (TicTacToeViewModel.allFields[0][1] == TicTacToeViewModel.allFields[1][1] && TicTacToeViewModel.allFields[0][1] == TicTacToeViewModel.allFields[2][1] && TicTacToeViewModel.allFields[0][1] != "") ||
                (TicTacToeViewModel.allFields[0][2] == TicTacToeViewModel.allFields[1][2] && TicTacToeViewModel.allFields[0][2] == TicTacToeViewModel.allFields[2][2] && TicTacToeViewModel.allFields[0][2] != "")

    val diagonal =
        (TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[1][1] && TicTacToeViewModel.allFields[0][0] == TicTacToeViewModel.allFields[2][2] && TicTacToeViewModel.allFields[0][0] != "") ||
                (TicTacToeViewModel.allFields[0][2] == TicTacToeViewModel.allFields[1][1] && TicTacToeViewModel.allFields[0][2] == TicTacToeViewModel.allFields[2][0] && TicTacToeViewModel.allFields[0][2] != "")

    return rows || cols || diagonal
}
