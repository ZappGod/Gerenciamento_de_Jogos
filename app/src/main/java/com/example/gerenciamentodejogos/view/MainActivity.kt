package com.example.gerenciamentodejogos.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.gerenciamentodejogos.ui.theme.GerenciamentoDeJogosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GerenciamentoDeJogosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "KORNO KKKKKKKKKKKKKKKKKKKK",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current


    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Ir para detalhe do sexo do zap")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GerenciamentoDeJogosTheme {
        Greeting("KORNO k³")
    }
}