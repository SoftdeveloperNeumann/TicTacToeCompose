package com.example.tictactoecompose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
// ViewModel nicht Notwendig aber scheinbar best practice
object TicTacToeViewModel : ViewModel() {
    var currentUser by mutableStateOf("X")
    var currentUserText = "X"
    var isPlaying by mutableStateOf(true)
    var isWon  by mutableStateOf(false)
    var isDraw  by mutableStateOf(false)
    val startColor = Color.White
    var rowCount by mutableStateOf(3)
    var allFields by mutableStateOf(Array(rowCount) { Array(rowCount) { "" } })

}
