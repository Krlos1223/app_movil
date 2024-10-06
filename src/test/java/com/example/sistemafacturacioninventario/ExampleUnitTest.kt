package com.example.sistemafacturacioninventario

import com.example.sistemafacturacioninventario.models.LoginRequest
import com.example.sistemafacturacioninventario.network.ApiService
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivityTest {

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.28:3000") // Cambia esto a tu URL de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    @Test
    fun testLogin() {
        val loginRequest = LoginRequest(nombre_de_usuario = "usuarioPrueba", contraseña = "contraseñaPrueba")
        val response = apiService.login(loginRequest).execute()

        assertNotNull(response)
        assertTrue(response.isSuccessful)
        // Puedes agregar más aserciones según tu lógica de negocio
    }
}
