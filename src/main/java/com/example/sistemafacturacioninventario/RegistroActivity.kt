package com.example.sistemafacturacioninventario

import android.app.DatePickerDialog // Importa el diálogo para seleccionar fechas
import android.content.Intent // Importa la clase para manejar intenciones de navegación
import android.os.Bundle // Importa la clase para manejar datos de la actividad
import android.widget.Button // Importa la clase para botones
import android.widget.EditText // Importa la clase para campos de texto
import android.widget.Toast // Importa la clase para mostrar mensajes emergentes
import androidx.appcompat.app.AppCompatActivity // Importa la clase base para actividades
import com.example.sistemafacturacioninventario.models.Usuario // Importa el modelo de usuario
import com.example.sistemafacturacioninventario.network.RetrofitClient // Importa el cliente de red para hacer solicitudes
import retrofit2.Call // Importa la clase para llamadas de red
import retrofit2.Callback // Importa la clase para manejar respuestas de red
import retrofit2.Response // Importa la clase para la respuesta de una llamada
import java.text.SimpleDateFormat // Importa la clase para formatear fechas
import java.util.* // Importa clases utilitarias como Calendar

class RegistroActivity : AppCompatActivity() {

    // Declaramos los campos de texto y el botón que se usarán en la actividad
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etCedula: EditText
    private lateinit var etFechaNacimiento: EditText
    private lateinit var etRol: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama al método de la clase base
        setContentView(R.layout.activity_register) // Establece el diseño de la actividad

        // Inicializamos los campos de texto y el botón con sus respectivas vistas del layout
        etNombre = findViewById(R.id.etNombre)
        etApellido = findViewById(R.id.etApellido)
        etCedula = findViewById(R.id.etCedula)
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento)
        etRol = findViewById(R.id.etRol)
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etContraseña)
        btnRegister = findViewById(R.id.btnRegister)

        // Configura un evento para abrir un selector de fecha cuando el usuario toca el campo de fecha de nacimiento
        etFechaNacimiento.setOnClickListener {
            showDatePickerDialog() // Llama al método para mostrar el selector de fecha
        }

        // Configura un evento para registrar al usuario cuando se toca el botón
        btnRegister.setOnClickListener {
            register() // Llama al método para registrar al usuario
        }
    }

    // Método para mostrar un diálogo que permite al usuario seleccionar una fecha
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance() // Obtiene la fecha actual
        val year = calendar.get(Calendar.YEAR) // Obtiene el año actual
        val month = calendar.get(Calendar.MONTH) // Obtiene el mes actual
        val day = calendar.get(Calendar.DAY_OF_MONTH) // Obtiene el día actual

        // Crea un diálogo de selección de fecha
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay -> // Maneja la fecha seleccionada
                // Formatea la fecha seleccionada y la muestra en el campo de texto
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                etFechaNacimiento.setText(selectedDate) // Muestra la fecha seleccionada
            },
            year,
            month,
            day
        )
        datePickerDialog.show() // Muestra el diálogo
    }

    // Método para registrar al usuario
    private fun register() {
        // Convierte la fecha de nacimiento de formato de texto a un formato que la API pueda entender
        val fechaNacimientoString = convertStringToDate(etFechaNacimiento.text.toString())

        // Crea un nuevo objeto Usuario con los datos ingresados
        val usuario = Usuario(
            nombre = etNombre.text.toString(),
            apellido = etApellido.text.toString(),
            cedula = etCedula.text.toString(),
            fecha_de_nacimiento = fechaNacimientoString ?: "",  // Aquí pasamos la fecha formateada
            rol = etRol.text.toString(),
            nombre_de_usuario = etUsuario.text.toString(),
            contraseña = etPassword.text.toString()
        )

        // Realiza la solicitud de registro a la API
        RetrofitClient.apiService.register(usuario).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                // Verifica si la respuesta fue exitosa
                if (response.isSuccessful) {
                    Toast.makeText(this@RegistroActivity, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show() // Muestra un mensaje de éxito

                    // Redirige a la MainActivity (pantalla principal)
                    val intent = Intent(this@RegistroActivity, MainActivity::class.java)
                    startActivity(intent) // Inicia la nueva actividad

                    // Finaliza la actividad actual para que no se pueda volver presionando atrás
                    finish()

                } else {
                    // Si hubo un error en el registro, muestra un mensaje de error
                    Toast.makeText(this@RegistroActivity, "Error al registrar: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Si hay un error de red, muestra un mensaje de error
                Toast.makeText(this@RegistroActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Método para convertir la fecha ingresada como texto a un formato específico (yyyy-MM-dd)
    private fun convertStringToDate(dateString: String): String? {
        // Define el formato que el usuario ingresa y el que la API espera
        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // El formato que el usuario ingresa
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // El formato que la API espera
        return try {
            // Intenta parsear la fecha y devolverla en el nuevo formato
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } // Retorna la fecha en el formato requerido
        } catch (e: Exception) {
            e.printStackTrace() // Imprime el error si ocurre
            null // Retorna null si hay un error
        }
    }
}
