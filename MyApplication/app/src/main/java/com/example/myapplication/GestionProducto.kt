package com.example.myapplication
/*import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class GestionProducto: AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var btnAgregarProducto: Button
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.registrarproducto)
        // Inicializar las vistas
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        btnAgregarProducto = findViewById(R.id.btnAgregarProducto)
        // Obtener referencia a la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().reference.child("productos")
        // Agregar evento clic al botón "Agregar Producto"
        btnAgregarProducto.setOnClickListener {
            agregarProducto()
        }
        // Configurar listeners para los botones
        /*findViewById<Button>(R.id.btnAgregarProducto).setOnClickListener {
            val nuevoProducto = Producto(nombre = "Producto 1", precio = 10.0, descripcion = "Descripción del Producto 1")
            GestionProducto().agregarProducto(nuevoProducto) { exito ->
                if (exito) {
                    // Producto agregado correctamente
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    // Error al agregar el producto
                    Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
                }
            }
        }*/

        // Configurar otros botones y operaciones similares para actualizar, eliminar y obtener productos
    }
    private fun agregarProducto() {
        val nombre = editTextNombre.text.toString().trim()
        val precio = editTextPrecio.text.toString().trim()
        val descripcion = editTextDescripcion.text.toString().trim()
        /*val intent = Intent(this, GestionProducto::class.java)
                        startActivity(intent)*/
        if (nombre.isEmpty() || precio.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear un nuevo objeto Producto
        val productoId = databaseReference.push().key ?: ""
        val nuevoProducto = Producto(productoId, nombre, precio.toDouble(), descripcion)
        val database = FirebaseDatabase.getInstance().reference
        val nuevoProductoRef = database.child("productos").push()
        // Agregar el nuevo producto a la base de datos
        databaseReference.child(productoId).setValue(nuevoProducto)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                    limpiarCampos()
                } else {
                    Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun limpiarCampos() {
        editTextNombre.text.clear()
        editTextPrecio.text.clear()
        editTextDescripcion.text.clear()
    }
}
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarproducto)
    }
    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("productos")
    }

    // Método para agregar un producto a la base de datos
    fun agregarProducto(producto: Producto, callback: (Boolean) -> Unit) {
        val id = database.push().key
        if (id != null) {
            database.child(id).setValue(producto)
                .addOnCompleteListener { task ->
                    callback(task.isSuccessful)
                }
        } else {
            callback(false)
        }
    }

    // Método para obtener todos los productos de la base de datos
    fun obtenerProductos(callback: (List<Producto>) -> Unit) {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val productos = mutableListOf<Producto>()
                snapshot.children.forEach { data ->
                    val producto = data.getValue(Producto::class.java)
                    producto?.let { productos.add(it) }
                }
                callback(productos)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejo de error
            }
        })
    }

    // Método para actualizar un producto en la base de datos
    fun actualizarProducto(producto: Producto, callback: (Boolean) -> Unit) {
        database.child(producto.id).setValue(producto)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    // Método para eliminar un producto de la base de datos
    fun eliminarProducto(id: String, callback: (Boolean) -> Unit) {
        database.child(id).removeValue()
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }
}*/
*/

/*import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registrarproducto.*

class FormActivity : AppCompatActivity() {

    private lateinit var viewModel: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarproducto)

        // Inicializar el ViewModel
        viewModel = Producto()

        btnAgregarProducto.setOnClickListener {
            // Obtener los valores ingresados por el usuario
            val nombre = editTextNombre.text.toString().trim()
            val precio = editTextPrecio.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString().trim()
            if (nombre.isEmpty() || precio.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return
            }

            // Crear un nuevo producto con los valores ingresados
            val product = Producto(nombre, precio, descripcion)

            // Registrar el producto utilizando el ViewModel
            viewModel.registerProduct(product)
        }
    }
}*/
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registrarproducto.*
import android.content.Intent

class GestionProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarproducto)

        btnAgregarProducto.setOnClickListener {
            val nombre = editTextNombre.text.toString().trim()
            val precio = editTextPrecio.text.toString().trim()
            val descripcion = editTextDescripcion.text.toString()

            val nuevoProducto = Producto(nombre, precio, descripcion)

            val productoManager = ProductoManager() // Aquí deberías tener tu gestor de productos

            productoManager.agregarProducto(nuevoProducto) { exito ->
                if (exito) {
                    Toast.makeText(this, "Producto registrado exitosamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrar el producto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}



