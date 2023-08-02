package com.example.elmapa2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent




class VentanaSecundariaActivity : AppCompatActivity() {
    private lateinit var nombreEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var comentarioEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventana_secundaria)

        nombreEditText = findViewById<EditText>(R.id.nombreEditText)
        correoEditText = findViewById<EditText>(R.id.correoEditText)
        comentarioEditText = findViewById<EditText>(R.id.comentarioEditText)


        val botonAgregar = findViewById<Button>(R.id.botonAgregar)
        botonAgregar.setOnClickListener {

            val nombre = nombreEditText.text.toString().trim()
            val correo = correoEditText.text.toString().trim()
            val comentario = comentarioEditText.text.toString().trim()

            if (nombre.isEmpty() || correo.isEmpty() || comentario.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, NuevaVentana::class.java)
                intent.putExtra("nombre", nombre)
                intent.putExtra("correo", correo)
                intent.putExtra("comentario", comentario)
                startActivity(intent)
            }
        }

    }


}

