package com.example.di_topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.di_topappbar.ui.theme.DI_TopAppBarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DI_TopAppBarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aplicacion() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                onOpcionSeleccionada = { opcion ->
                    scope.launch {
                        snackbarHostState.showSnackbar("Has seleccionado la $opcion")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Pantalla Principal")
            Image(
                painter = painterResource(id = R.drawable.imagen),
                contentDescription = "Imagen de ejemplo",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(onOpcionSeleccionada: (String) -> Unit) {
    var menuDesplegado by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = "TopAppBar", fontSize = 24.sp)
        },
        actions = {
            IconButton(onClick = { menuDesplegado = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Menú"
                )
            }
            DropdownMenu(
                expanded = menuDesplegado,
                onDismissRequest = { menuDesplegado = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Opción 1") },
                    onClick = {
                        menuDesplegado = false
                        onOpcionSeleccionada("Opción 1")
                    }
                )
                DropdownMenuItem(
                    text = { Text("Opción 2") },
                    onClick = {
                        menuDesplegado = false
                        onOpcionSeleccionada("Opción 2")
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray
        )
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Aplicacion()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DI_TopAppBarTheme {
        Greeting("Android")
    }
}