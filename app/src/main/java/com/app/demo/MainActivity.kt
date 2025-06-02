package com.app.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.app.demo.ui.theme.DemoTheme

class MainActivity : ComponentActivity() {

    private var counterViewModel: CounterViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val useViewModel =true

        if(useViewModel){
            counterViewModel = ViewModelProvider(this)[
                CounterViewModel::class.java
            ]
        }

        enableEdgeToEdge()
        setContent {
            DemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   counterViewModel?.let {
                       Greeting(
                           viewModel = it,
                           modifier = Modifier.padding(innerPadding),
                       )
                   }
                }
            }
        }
    }
}

@Composable
fun Greeting( modifier: Modifier = Modifier,viewModel: CounterViewModel) {
   Column(modifier = modifier.fillMaxSize(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
       ) {

       Text("Count:"+ viewModel.count.value.toString())
       Row {
           Button(onClick = {
               viewModel.increment()
           }) {
               Text("increment")
           }
           Spacer(modifier.width(10.dp
           ))
           Button(onClick = {
               viewModel.decrement()
           }) {
               Text("decrement")
           }
       }
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoTheme {

    }
}