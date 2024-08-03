package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val calculatorViewModel = ViewModelProvider(this)[CalculatorViewModel :: class.java]
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
               Scaffold (modifier = Modifier.fillMaxSize()){ innerPadding ->
                   Calculator(modifier = Modifier.padding(innerPadding),calculatorViewModel)
               }
            }
        }
    }
}

