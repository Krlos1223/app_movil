package com.example.sistemafacturacioninventario

import android.content.Intent// Importa la clase Intent para permitir la navegación entre actividades.
import android.os.Bundle// Importa la clase Bundle para manejar datos entre actividades.
import android.widget.ImageView// Importa ImageView y TextView para mostrar imágenes y texto en la interfaz.
import android.widget.TextView
import androidx.activity.ComponentActivity// Importa ComponentActivity para que esta actividad sea una de las principales en la aplicación.
import androidx.activity.enableEdgeToEdge// Importa la función enableEdgeToEdge para permitir que la actividad use todo el espacio de la pantalla.
import com.example.sistemafacturacioninventario.network.ApiService// Importa la interfaz ApiService para manejar las llamadas a la API.
import retrofit2.Retrofit// Importa Retrofit para realizar solicitudes HTTP a la API.
import retrofit2.converter.gson.GsonConverterFactory// Importa GsonConverterFactory para convertir las respuestas JSON a objetos de Kotlin.

// Clase principal de la actividad, que extiende ComponentActivity.
class MainActivity : ComponentActivity() {
    // Declara una variable para la API
    private lateinit var apiService: ApiService

    // Método que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Configurar Retrofit para hacer solicitudes a la API
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.28:3000") // URL del servidor local; el emulador la usa para acceder
            .addConverterFactory(GsonConverterFactory.create()) // Convierte respuestas JSON en objetos de Kotlin
            .build()

        // Crea la implementación de la API usando Retrofit
        apiService = retrofit.create(ApiService::class.java)

        // Establece el diseño de la actividad
        setContentView(R.layout.activity_main)

        // Configurar el clic en la imagen de registro
        val imageViewRegistro = findViewById<ImageView>(R.id.imageViewRegistro)
        imageViewRegistro.setOnClickListener {
            // Crea el Intent para navegar a la actividad de registro
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent) // Inicia la nueva actividad de registro
        }

        // Configurar el clic en el texto de registro
        val textViewRegistro = findViewById<TextView>(R.id.textViewRegistro)
        textViewRegistro.setOnClickListener {
            // Crea el Intent para navegar a la actividad de registro
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent) // Inicia la nueva actividad de registro
        }

        // Configurar el clic en la imagen de login
        val imageViewLogin = findViewById<ImageView>(R.id.imageViewLogin)
        imageViewLogin.setOnClickListener {
            // Crea el Intent para navegar a la actividad de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // Inicia la nueva actividad de login
        }

        // Configurar el clic en el texto de login
        val textViewLogin = findViewById<TextView>(R.id.textViewLogin)
        textViewLogin.setOnClickListener {
            // Crea el Intent para navegar a la actividad de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) // Inicia la nueva actividad de login
        }
    }
}
