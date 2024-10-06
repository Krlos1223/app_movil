package com.example.sistemafacturacioninventario.network

import retrofit2.Retrofit// Importa Retrofit para realizar llamadas a APIs REST.
import retrofit2.converter.gson.GsonConverterFactory// Importa GsonConverterFactory para convertir objetos JSON a objetos Kotlin y viceversa.

// Objeto singleton que configura y proporciona acceso a la instancia de Retrofit.
object RetrofitClient {

    // URL base del servidor donde está alojada la API. Asegúrate de cambiar esto por la URL correcta.
    private const val BASE_URL = "http://192.168.1.28:3000" // Cambia esto por la URL de tu API

    // Configura la instancia de Retrofit usando el patrón Builder.
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Establece la URL base para las peticiones.
        .addConverterFactory(GsonConverterFactory.create()) // Añade el convertidor para JSON.
        .build() // Construye la instancia de Retrofit.

    // Crea una implementación de la interfaz ApiService que se utilizará para realizar las peticiones a la API.
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

