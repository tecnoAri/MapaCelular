package com.example.elmapa2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import com.example.elmapa2.R


class NuevaVentana : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_ventana)

        // Obtener los datos enviados desde VentanaSecundariaActivity
        val nombre = intent.getStringExtra("nombre")
        val correo = intent.getStringExtra("correo")
        val comentario = intent.getStringExtra("comentario")



        val botonNuevaVentana = findViewById<Button>(R.id.botonNuevaVentana)
        botonNuevaVentana.setOnClickListener {
            // Abrir la ventana del archivo activity_ventana_secundaria.xml
            val intent = Intent(this, VentanaSecundariaActivity::class.java)
            startActivity(intent)
        }
    }
}