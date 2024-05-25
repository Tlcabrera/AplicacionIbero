package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.producto.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.producto)

        btnAgregarProducto.setOnClickListener {
            // Abrir la actividad del formulario cuando se hace clic en el botón
            startActivity(Intent(this, GestionProducto::class.java))
        }
    }
}
