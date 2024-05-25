package com.example.gestionproducto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText


class AgregarProductoActivity : AppCompatActivity() {

    private val database = FirebaseDatabase.getInstance().reference.child("productos")
    private lateinit var editTextNombre: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var btnAgregarProducto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_formulario_agregar_producto)
//   val btnAgregarProducto = findViewById<Button>(R.id.btnAgregarProducto)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)


        findViewById<Button>(R.id.btnAgregarProducto).setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val precio = editTextPrecio.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            // Crear un nuevo producto en Firebase
            val productoId = database.push().key
            if (productoId != null) {
                val producto = Producto(productoId, nombre, precio, descripcion)
                database.child(productoId).setValue(producto)
            }

            finish() // Finalizar la actividad después de agregar el producto
        }
    }
}
