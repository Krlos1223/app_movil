package com.example.sistemafacturacioninventario.models

// Clase de datos que representa a un usuario en el sistema de facturación e inventario.
data class Usuario(
    // El nombre del usuario.
    val nombre: String,

    // El apellido del usuario.
    val apellido: String,

    // La cédula de identidad del usuario, que puede servir como identificador único.
    val cedula: String,

    // La fecha de nacimiento del usuario, en formato de cadena.
    val fecha_de_nacimiento: String,

    // El rol del usuario dentro del sistema (por ejemplo, administrador, empleado, etc.).
    val rol: String,

    // El nombre de usuario que utilizará para iniciar sesión.
    val nombre_de_usuario: String,

    // La contraseña del usuario, utilizada para la autenticación.
    val contraseña: String
)


