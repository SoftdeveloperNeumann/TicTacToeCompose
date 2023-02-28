package com.example.tictactoecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoecompose.engin.screenHeigt
import com.example.tictactoecompose.engin.screenWidth
import com.example.tictactoecompose.model.TicTacToeViewModel.allFields
import com.example.tictactoecompose.model.TicTacToeViewModel.currentUser
import com.example.tictactoecompose.model.TicTacToeViewModel.currentUserText
import com.example.tictactoecompose.model.TicTacToeViewModel.isPlaying
import com.example.tictactoecompose.model.TicTacToeViewModel.isWon
import com.example.tictactoecompose.ui.ActionButton
import com.example.tictactoecompose.ui.Spielfeld
import com.example.tictactoecompose.ui.Status
import com.example.tictactoecompose.ui.theme.TicTacToeComposeTheme


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

                            ActionButton(modifier = Modifier
                                .padding(16.dp)
                                .align(BottomEnd))
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