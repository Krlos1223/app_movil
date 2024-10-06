package com.example.sistemafacturacioninventario.models

// Clase de datos que representa la solicitud de inicio de sesión.
data class LoginRequest(

    // El nombre de usuario que se utilizará para autenticar al usuario.
    val nombre_de_usuario: String,

    // La contraseña del usuario, que se usará para validar su identidad.
    val contraseña: String
)

