package com.example.calculatorapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import java.lang.Exception

class CalculatorViewModel : ViewModel(){
    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText
    private val _resultText = MutableLiveData("0")
    val resultText : LiveData<String> = _resultText

    fun onButtonClick(btn: String) {
        Log.i("Clicked Button", btn)
        _equationText.value?.let {
            when (btn) {
                "AC" -> {
                    _equationText.value = ""
                    _resultText.value = "0"
                }
                "C" -> {
                    if (it.isNotEmpty()) {
                        _equationText.value = it.substring(0, it.length - 1)
                        _resultText.value = calculateResult(_equationText.value.toString())
                    }
                }
                "=" -> {
                    val result = try {
                        calculateResult(it)
                    } catch (e: Exception) {
                        "Error"
                    }
                    _resultText.value = result
                    _equationText.value = result
                }
                else -> {
                    _equationText.value = it + btn
                    try {
                        _resultText.value = calculateResult(_equationText.value.toString())
                    } catch (e: Exception) {
                        _resultText.value = "Error"
                    }
                }
            }
        }
    }


    fun calculateResult(equation : String) : String
    {
        val context : Context = Context.enter()
        context.optimizationLevel =-1
        val scriptable : Scriptable = context.initStandardObjects()
        var finalResult = context.evaluateString(scriptable,equation,"Javascript", 1, null).toString()
        if(finalResult.endsWith(".0"))
            finalResult = finalResult.replace(".0","")
        return finalResult
    }
}