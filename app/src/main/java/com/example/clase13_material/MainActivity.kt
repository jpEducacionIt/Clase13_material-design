package com.example.clase13_material

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.util.PatternsCompat
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var buttonContinuar: Button
    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPass: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonContinuar = findViewById(R.id.buttonContinuar)
        textInputEmail = findViewById(R.id.textInputEmail)
        textInputPass = findViewById(R.id.textInputPassword)

        buttonContinuar.setOnClickListener {
            validate()
        }
    }

    private fun validate() {
        val result = arrayOf(validarEmail(), validarPassword())
        if (false in result) return
    }

    private fun validarEmail() : Boolean {
        val email = textInputEmail.editText?.text.toString()
        return if (email.isEmpty()) {
            textInputEmail.error = "este campo es obligatorio"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            textInputEmail.error = "ingrese un correo valido"
            false
        } else {
            textInputEmail.error = null
            true
        }
    }

    private fun validarPassword(): Boolean {
        val pass = textInputPass.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[a-z])" + //1 minuscula
                    "(?=.*[A-Z])" + //1 mayusculas
                    "(?=.*[0-9])" + //1 numero
                    "(?=\\S+$)"  + //sin espacios
                    ".{8,}" + "$") //8 caracteres

        return if(pass.isEmpty()) {
            textInputPass.error = "este campo es obligatorio"
            false
        } else if(!passwordRegex.matcher(pass).matches()) {
            textInputPass.error = "contraseña demasiado débil"
            false
        } else {
            textInputPass.error = null
            true
        }
    }
}
