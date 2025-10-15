package com.example.core.db.uitls

import java.security.MessageDigest

object PasswordUtils {
    fun hash(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(password.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}