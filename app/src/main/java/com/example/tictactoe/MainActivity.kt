package com.example.tictactoe

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
/*
Simple Tic Tac Toe game made by Mikhail Kartyshov with some initial help of  Dr. Parag Shukla
15.07.2022
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    // we use lateinit because we will use it later; it would be easier for us to appoint vars now
    // here we have buttons from 0 to 8
    lateinit var b0 : Button
    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var filledPos : IntArray // positions that are filled with X or 0
    lateinit var tv : TextView //

    var gameActive = true // status of the game
    var player1 = 0
    var player2 = 1
    var activePlayer = player1 // player1 starts the game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1) // by default all positions are unfilled

        tv = findViewById(R.id.textView2)
        b0 = findViewById(R.id.b0) // appoint button variables to their id, that we used in .xml
        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)

        b0.setOnClickListener(this)
        b1.setOnClickListener(this)
        b2.setOnClickListener(this)
        b3.setOnClickListener(this)
        b4.setOnClickListener(this)
        b5.setOnClickListener(this)
        b6.setOnClickListener(this)
        b7.setOnClickListener(this)
        b8.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        if(!gameActive) // if game is already finished, we can't do anything
            return

        var btnClicked = findViewById<Button>(v!!.id)
        var clickedTag = Integer.parseInt(btnClicked.tag.toString())

        if(filledPos[clickedTag] != -1)
            return

        filledPos[clickedTag] = activePlayer

        if (activePlayer == player1) { // if it is first player's turn
            btnClicked.setText("X") // setting X if player clicked the button
            activePlayer = player2 // making player2 active player
            tv.setText("Second player turn") // texting, that it is second player's turn
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.audi_red) // paint the button, that was clicked
        } else { // if it is second player's turn
            btnClicked.setText("O") // setting 0 if player clicked the button
            activePlayer = player1 // making player1 active player
            tv.setText("First player turn") // texting, that it is first player's turn
            btnClicked.backgroundTintList = getColorStateList(R.color.bmw_orange) // paint the button, that was clicked
        }
        checkForWin()
    }

    private fun checkForWin() {
        var winPos = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6)) // these are winning positions

        for(i in 0 until winPos.size) {
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if(filledPos[val0] != -1) { // check filled positions
                    gameActive = false // change status of the game, if someone won
                    if (filledPos[val0] == player1) { // if the first player won, we show this message
                        showMessage("First Player is the winner. Congrats!")
                    } else { // if the first player didn't win, we show this message
                        showMessage("Second Player is the winner. Congrats!")
                    }
                    return
                }
            }
        }
        var count = 0
        for(i in 0 until filledPos.size) {
            if(filledPos[i] == -1) {
                count++
            }
        }
        if(count == 0) { // if we don't have a winner, then it's a draw, so we show this message
            showMessage("It's a draw. Try again!")
        }

    }

    private fun showMessage(s: String) { // showing message to restart the game
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart Game", DialogInterface.OnClickListener { dialog, which -> restartGame() })
            .show()
    }

    private fun restartGame() { // return all the settings to default to restart the game
        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)  // all positions are unfilled
        activePlayer = player1 // first player is making the first turn
        gameActive = true // game is active
        tv.setText("First player turn") // setting the info text
        b0.setText("") // clearing all the buttons
        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b0.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary) // giving all the buttons their default color
        b1.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b2.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b3.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b4.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b5.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b6.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b7.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b8.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
    }
}