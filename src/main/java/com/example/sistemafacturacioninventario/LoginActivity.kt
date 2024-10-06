package com.example.sistemafacturacioninventario


import android.content.Intent// Importa la clase Intent para permitir la navegación entre actividades.
import android.os.Bundle// Importa la clase Bundle para manejar datos entre actividades.
import android.widget.Button// Importa Button y EditText para crear botones y campos de texto en la interfaz.
import android.widget.EditText
import android.widget.Toast// Importa Toast para mostrar mensajes emergentes al usuario.
import androidx.appcompat.app.AppCompatActivity// Importa AppCompatActivity para que esta actividad siga las normas de diseño de Android.
import com.example.sistemafacturacioninventario.models.LoginRequest// Importa LoginRequest, que es un modelo que representa la solicitud de inicio de sesión.
import com.example.sistemafacturacioninventario.network.RetrofitClient// Importa RetrofitClient para gestionar las llamadas a la API.
import retrofit2.Call// Importa las clases necesarias para hacer llamadas asíncronas a la API.
import retrofit2.Callback
import retrofit2.Response

// Clase principal de la actividad de inicio de sesión que extiende AppCompatActivity.
class LoginActivity : AppCompatActivity() {

    // Declara variables para los campos de entrada y el botón.
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    // Método que se ejecuta al crear la actividad.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Establece el diseño de la actividad desde el archivo XML correspondiente.
        setContentView(R.layout.activity_login)

        // Inicializa los campos de texto y el botón a partir de su ID en el XML.
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        // Configura el clic en el botón de inicio de sesión.
        btnLogin.setOnClickListener {
            login() // Llama al método para iniciar sesión
        }
    }

    // Método para gestionar el proceso de inicio de sesión.
    private fun login() {

        // Crea una nueva solicitud de inicio de sesión usando los datos ingresados.
        val loginRequest = LoginRequest(
            nombre_de_usuario = etUsername.text.toString(),
            contraseña = etPassword.text.toString()
        )

        // Llama a la API para realizar el inicio de sesión.
        RetrofitClient.apiService.login(loginRequest).enqueue(object : Callback<Void> {
            // Maneja la respuesta de la API al iniciar sesión.
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Si el inicio de sesión es exitoso, muestra un mensaje.
                    Toast.makeText(this@LoginActivity, "Login exitoso", Toast.LENGTH_SHORT).show()

                    // Navega a la actividad ConstructionActivity.
                    val intent = Intent(this@LoginActivity, ConstructionActivity::class.java)
                    startActivity(intent) // Inicia la nueva actividad

                    // Finaliza la actividad de Login para que no se pueda volver a ella.
                    finish()
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error.
                    Toast.makeText(this@LoginActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }

            // Maneja fallos en la conexión a la API.
            override fun onFailure(call: Call<Void>, t: Throwable) {

                // Muestra un mensaje de error de red.
                Toast.makeText(this@LoginActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
