package com.example.sistemafacturacioninventario.network


import com.example.sistemafacturacioninventario.models.LoginRequest// Importa los modelos necesarios para las peticiones.
import com.example.sistemafacturacioninventario.models.Usuario// Importa los modelos necesarios para las peticiones.
import retrofit2.Call// Importa Retrofit para definir las llamadas a la API.
import retrofit2.http.Body// Importa las anotaciones para las peticiones HTTP.
import retrofit2.http.POST// Importa las anotaciones para las peticiones HTTP.

// Interfaz que define los métodos para interactuar con la API.
interface ApiService {

    // Endpoint para iniciar sesión. Utiliza el método POST y espera un objeto LoginRequest en el cuerpo de la solicitud.
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<Void>

    // Endpoint para registrar un nuevo usuario. Utiliza el método POST y espera un objeto Usuario en el cuerpo de la solicitud.
    @POST("/registro")
    fun register(@Body usuario: Usuario): Call<Void>
}

