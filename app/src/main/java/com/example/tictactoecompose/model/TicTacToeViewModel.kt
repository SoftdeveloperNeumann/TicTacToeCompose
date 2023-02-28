package com.example.tictactoecompose.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

object TicTacToeViewModel : ViewModel() {
    var currentUser by mutableStateOf("X")
    var currentUserText = "X"
    var isPlaying = true
    var isWon = false
    var isDraw = false
    val startColor = Color.White
    var allFields by mutableStateOf(Array(3) { Array(3) { "" } })
}
