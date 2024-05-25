package com.example.myapplication
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductoManager {
    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("productos")
    }

    fun agregarProducto(producto: Producto, callback: (Boolean) -> Unit) {
        val id = database.push().key // Generar un nuevo ID para el producto
        if (id != null) {
            database.child(id).setValue(producto)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback(true)
                    } else {
                        callback(false)
                    }
                }
        } else {
            callback(false)
        }
    }
}
