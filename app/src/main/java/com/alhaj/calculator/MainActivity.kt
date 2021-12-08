package com.alhaj.calculator

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.text.method.ScrollingMovementMethod
import com.example.calculator.R

class MainActivity : AppCompatActivity() {

    var operationClicked=false
    var lastDigit: Double? = null
    var lastOperation: Operations? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_1)

        setOnClickListeners()
        val scrollingMovementMethod = ScrollingMovementMethod()
        tv_main.movementMethod = scrollingMovementMethod

        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    private fun setOnClickListeners() {
        tv_0.setOnClickListener {
            addText("0")
        }
        tv_1.setOnClickListener {
            addText("1")

        }
        tv_2.setOnClickListener {
            addText("2")

        }
        tv_3.setOnClickListener {
            addText("3")

        }
        tv_4.setOnClickListener {
            addText("4")

        }
        tv_5.setOnClickListener {
            addText("5")

        }
        tv_6.setOnClickListener {
            addText("6")

        }
        tv_7.setOnClickListener {
            addText("7")

        }
        tv_8.setOnClickListener {
            addText("8")

        }
        tv_9.setOnClickListener {
            addText("9")

        }

        tv_dot.setOnClickListener {
            if (operationClicked){
                addText(".")
            }else{
                if (!tv_main.text.contains("."))
                    addText(".")
            }
        }

        tv_plus.setOnClickListener {
            operationSelected(Operations.PLUS)
        }
        tv_minus.setOnClickListener {
            operationSelected(Operations.MINUS)
        }
        tv_divide.setOnClickListener {
            operationSelected(Operations.DIVIDE)
        }
        tv_into.setOnClickListener {
            operationSelected(Operations.INTO)
        }

        tv_equal.setOnClickListener {
            try {
                if (lastOperation != null && !operationClicked) {
                    val temp = when (lastOperation) {
                        Operations.PLUS -> lastDigit!! + tv_main.text.toString().toDouble()
                        Operations.MINUS -> lastDigit!! - tv_main.text.toString().toDouble()
                        Operations.DIVIDE -> lastDigit!! / tv_main.text.toString().toDouble()
                        Operations.INTO -> lastDigit!! * tv_main.text.toString().toDouble()
                        null -> 0.0
                    }
                    setText(temp.toString())
                }
                lastDigit = tv_main.text.toString().toDouble()
                operationClicked = true
            }catch (e:Exception){
                clear()
                showToast("Error ${e.message}")
            }
        }

        tv_plus_minus.setOnClickListener {
            if (operationClicked){
                setText("0")
            }else{
                setText((tv_main.text.toString().toDouble() * -1).toString())
            }
        }

        tv_ac.setOnClickListener {
            clear()
        }
    }

    private fun clear() {
        operationClicked = false
        tv_main.text = "0"
        lastDigit = null
        lastOperation = null
    }

    fun operationSelected(operation: Operations){
        try {
            if (lastOperation != null && !operationClicked) {
                val temp = when (lastOperation) {
                    Operations.PLUS -> lastDigit!! + tv_main.text.toString().toDouble()
                    Operations.MINUS -> lastDigit!! - tv_main.text.toString().toDouble()
                    Operations.DIVIDE -> lastDigit!! / tv_main.text.toString().toDouble()
                    Operations.INTO -> lastDigit!! * tv_main.text.toString().toDouble()
                    null -> 0.0
                }
                setText(temp.toString())
            }
            lastDigit = tv_main.text.toString().toDouble()
            lastOperation = operation
            operationClicked = true
        }catch (e:Exception){
            clear()
            showToast("Error ${e.message}")
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }

    private fun setText(text: String) {
        if (text.endsWith(".0")){
            tv_main.text = text.dropLast(2)
        }else{
            tv_main.text = text
        }
        while (tv_main.canScrollVertically(1)) {
            tv_main.scrollBy(0, 10)
        }
    }

    private fun addText(string: String) {
        if (operationClicked){
            operationClicked = false
            tv_main.text = string
        }else{
            if (tv_main.text.toString()=="0"){
                tv_main.text = string
            }else{
                tv_main.text = tv_main.text.toString() + string
            }
        }
        while (tv_main.canScrollVertically(1)) {
            tv_main.scrollBy(0, 10)
        }
    }
}