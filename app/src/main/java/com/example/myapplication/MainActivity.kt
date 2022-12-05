package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    var inputtext: EditText? = null
    var screen: TextView? = null
    var displaytext: TextView? = null
    var currentOperator = ""
    var display = ""
    var res: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val deleteBtn = findViewById<View>(R.id.butdelet) as Button
        deleteBtn.setOnClickListener { deletenumber() }
        screen = findViewById<View>(R.id.input_box) as TextView
        displaytext = findViewById(R.id.result_box)
        screen!!.text = display
        inputtext = findViewById(R.id.input_box)
    }

    fun onClearButton(v: View?) {
        inputtext!!.text.clear()
        displaytext!!.text = ""
    }

    fun deletenumber() {
        inputtext!!.text.delete(getinput().length - 1, getinput().length)
    }

    private fun appendToLast(str: String) {
        inputtext!!.text.append(str)
    }

    fun onClickNumber(v: View) {
        val b = v as Button
        display += b.text
        appendToLast(display)
        display = ""
    }

    fun onClickOperator(v: View) {
        val b = v as Button
        display += b.text
        if (endsWithOperatore()) {
            replace(display)
            currentOperator = b.text.toString()
            display = ""
        } else {
            appendToLast(display)
            currentOperator = b.text.toString()
            display = ""
        }
    }

    private fun endsWithOperatore(): Boolean {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("/") || getinput().endsWith(
            "x"
        )
    }

    private fun replace(str: String) {
        inputtext!!.text.replace(getinput().length - 1, getinput().length, str)
    }

    private fun getinput(): String {
        return inputtext!!.text.toString()
    }

    fun equalresult(v: View?) {
        val input = getinput()
        res = if (!endsWithOperatore()) {
            val expression: Expression = ExpressionBuilder(input).build()
            val result: Double = expression.evaluate()
            java.lang.Double.toString(result)
        } else {
            "Значення не введені"
        }
    }

    fun onClickNext(v: View) {
        when (v.id) {
            R.id.butnext -> {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, res)
                startActivity(intent)
            }
            else -> {}
        }

    }
}