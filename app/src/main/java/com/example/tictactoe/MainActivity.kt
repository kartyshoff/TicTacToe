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

    lateinit var b0 : Button
    lateinit var b1 : Button
    lateinit var b2 : Button
    lateinit var b3 : Button
    lateinit var b4 : Button
    lateinit var b5 : Button
    lateinit var b6 : Button
    lateinit var b7 : Button
    lateinit var b8 : Button
    lateinit var filledPos : IntArray
    lateinit var tv : TextView

    var gameActive = true
    var player1 = 0
    var player2 = 1
    var activePlayer = player1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        filledPos = intArrayOf(-1, -1, -1, -1, -1, -1, -1, -1, -1)

        tv = findViewById(R.id.textView2)
        b0 = findViewById(R.id.b0)
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

        if(!gameActive)
            return

        var btnClicked = findViewById<Button>(v!!.id)
        var clickedTag = Integer.parseInt(btnClicked.tag.toString())

        if(filledPos[clickedTag] != -1)
            return

        filledPos[clickedTag] = activePlayer

        if (activePlayer == player1) {
            btnClicked.setText("X")
            activePlayer = player2
            tv.setText("Second player turn")
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.audi_red)
        } else {
            btnClicked.setText("O")
            activePlayer = player1
            tv.setText("First player turn")
            btnClicked.backgroundTintList = getColorStateList(R.color.bmw_orange)
        }

        checkForWin()

    }

    private fun checkForWin() {
        var winPos = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8), intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(0,4,8), intArrayOf(2,4,6))

        for(i in 0 until winPos.size) {
            var val0 = winPos[i][0]
            var val1 = winPos[i][1]
            var val2 = winPos[i][2]

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if(filledPos[val0] != -1) {
                    gameActive = false
                    if (filledPos[val0] == player1) {
                        showMessage("First Player is the winner. Congrats!")
                    } else {
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
        if(count == 0) {
            showMessage("It's a draw. Try again!")
        }

    }

    private fun showMessage(s: String) {
        AlertDialog.Builder(this)
            .setMessage(s)
            .setTitle("Tic Tac Toe")
            .setPositiveButton("Restart Game", DialogInterface.OnClickListener { dialog, which -> restartGame() })
            .show()
    }

    private fun restartGame() {
        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer = player1
        gameActive = true
        tv.setText("First player turn")
        b0.setText("")
        b1.setText("")
        b2.setText("")
        b3.setText("")
        b4.setText("")
        b5.setText("")
        b6.setText("")
        b7.setText("")
        b8.setText("")
        b0.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
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