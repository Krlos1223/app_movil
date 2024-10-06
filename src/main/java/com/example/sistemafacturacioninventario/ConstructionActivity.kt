package com.example.sistemafacturacioninventario


import android.os.Bundle// Importa la clase Bundle para manejar datos entre actividades.
import androidx.activity.enableEdgeToEdge// Importa enableEdgeToEdge para permitir un diseño de borde a borde.
import androidx.appcompat.app.AppCompatActivity// Importa AppCompatActivity para que esta actividad siga las normas de diseño de Android.
import androidx.core.view.ViewCompat// Importa ViewCompat para gestionar la compatibilidad de vistas.
import androidx.core.view.WindowInsetsCompat// Importa WindowInsetsCompat para manejar los espacios ocupados por las barras del sistema.

// Clase principal de la actividad de construcción que extiende AppCompatActivity.
class ConstructionActivity : AppCompatActivity() {

    // Método que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el diseño de borde a borde.
        enableEdgeToEdge()

        // Establece el diseño de la actividad desde el archivo XML correspondiente.
        setContentView(R.layout.activity_construction)

        // Configura un listener para aplicar los insets de ventana a la vista principal.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            // Obtiene los insets ocupados por las barras del sistema (barra de estado y barra de navegación).
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // Establece el padding de la vista principal según los insets del sistema.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            // Devuelve los insets para que se apliquen correctamente.
            insets
        }
    }
}
