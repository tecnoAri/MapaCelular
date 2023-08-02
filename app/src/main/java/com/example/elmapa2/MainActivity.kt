package com.example.elmapa2

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.Toast
import android.annotation.SuppressLint
import android.content.Intent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import android.os.Build
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView;
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var spinner3: Spinner
    private lateinit var favoritesButton: Button

    private var selectedSpinner1Value: String? = null
    private var selectedSpinner2Value: String? = null
    private var selectedSpinner3Value: String? = null


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        spinner3 = findViewById(R.id.spinner3)
        favoritesButton = findViewById(R.id.button1)

        webView.settings.javaScriptEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webView.loadUrl("file:///android_asset/map.html")
        webView.webViewClient = WebViewClient()

        val spinner1Values = listOf("Seleccione","Valor 1", "Valor 2", "Valor 3", "Valor 4", "Valor 5")
        val spinner2Values = listOf("Seleccione","Opción 1", "Opción 2", "Opción 3", "Opción 4", "Opción 5")
        val spinner3Values =
            listOf("Seleccione", "Elemento A", "Elemento B", "Elemento C", "Elemento D", "Elemento E")

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner1Values)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner2Values)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner3Values)

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter1
        spinner2.adapter = adapter2
        spinner3.adapter = adapter3

        spinner1.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                selectedSpinner1Value = spinner1Values[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedSpinner1Value=null
            }
        }

        spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                selectedSpinner2Value = spinner2Values[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedSpinner2Value=null
            }
        }

        spinner3.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                selectedSpinner3Value = spinner3Values[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedSpinner3Value=null
            }
        }


        favoritesButton.setOnClickListener { showPopupMenu(it) }


        val buttonCom: Button = findViewById(R.id.button2)
        buttonCom.setOnClickListener{
            val selectedSpinner1Value = selectedSpinner1Value
            val selectedSpinner2Value = selectedSpinner2Value
            val selectedSpinner3Value = selectedSpinner3Value

            val intent = Intent(this, NuevaVentana::class.java)
            intent.putExtra("nombre", selectedSpinner1Value)
            intent.putExtra("correo", selectedSpinner2Value)
            intent.putExtra("comentario", selectedSpinner3Value)
            startActivity(intent)

        }



    }

    private fun openFavoritesActivity() {
        val intent = Intent(this, FavoritesActivity:: class.java)
        intent.putExtra("spinner1Value", selectedSpinner1Value)
        intent.putExtra("spinner2Value", selectedSpinner2Value)
        intent.putExtra("spinner3Value", selectedSpinner3Value)
        startActivity(intent)
    }

    private fun showPopupMenu(view: View) {
        if (selectedSpinner1Value != null && selectedSpinner2Value != null && selectedSpinner3Value != null) {
            val popupMenu = PopupMenu(this, view)
            popupMenu.inflate(R.menu.favoritos)

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.action_add_route -> {
                        Toast.makeText(this, "Agregar ruta seleccionado", Toast.LENGTH_SHORT).show()
                        openFavoritesActivity()
                        true
                    }
                    R.id.action_view_routes -> {
                        Toast.makeText(this, "Ver rutas seleccionado", Toast.LENGTH_SHORT).show()
                        openFavoritesActivity()
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        } else {
            Toast.makeText(this, "Selecciona opciones en todos los Spinners", Toast.LENGTH_SHORT).show()
        }

    }
}

class FavoritesActivity : AppCompatActivity() {
    private lateinit var spinner1ValueTextView: TextView
    private lateinit var spinner2ValueTextView: TextView
    private lateinit var spinner3ValueTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val spinner1Value = intent.getStringExtra("spinner1Value")
        val spinner2Value = intent.getStringExtra("spinner2Value")
        val spinner3Value = intent.getStringExtra("spinner3Value")

        spinner1ValueTextView = findViewById(R.id.spinner1ValueTextView)
        spinner2ValueTextView = findViewById(R.id.spinner2ValueTextView)
        spinner3ValueTextView = findViewById(R.id.spinner3ValueTextView)

        // Mostrar los valores de los spinners en los TextViews
        spinner1ValueTextView.text = spinner1Value
        spinner2ValueTextView.text = spinner2Value
        spinner3ValueTextView.text = spinner3Value


        // Ejemplo: Imprimir los valores en el logcat
        println("Spinner 1: $spinner1Value")
        println("Spinner 2: $spinner2Value")
        println("Spinner 3: $spinner3Value")
    }

    private fun calcularResultado(
        spinner1Value: String?,
        spinner2Value: String?,
        spinner3Value: String?
    ): Int {
        // Aquí puedes realizar los cálculos o lógica necesarios
        // en base a los valores de los spinners y devolver el resultado

        // Ejemplo: Sumar la longitud de los valores seleccionados
        val length1 = spinner1Value?.length ?: 0
        val length2 = spinner2Value?.length ?: 0
        val length3 = spinner3Value?.length ?: 0
        return length1 + length2 + length3
    }


}