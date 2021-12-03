package com.example.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    fun btnCalcular_onClick(preco: Float?, autonomia: Float?, distancia: Float?): Float {

        if ((distancia != null) && (autonomia != null) && (preco != null)) {
            return distancia / autonomia * preco
        }
        return 0f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //called upon creating the view; Should i let the funcions inside, or just use another package?
        //Should i only inicializate the objects and make so those objects are called?
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var resultado: Float

        val txtPreco = findViewById<EditText>(R.id.txt_preco)
        val txtAutonomia = findViewById<EditText>(R.id.txt_autonomia)
        val txtDistancia = findViewById<EditText>(R.id.txt_distancia)
        val lblResultado = findViewById<TextView>(R.id.lbl_final)

        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        btnCalcular.setOnClickListener {

            var preco: Float? = txtPreco.text.toString().toFloatOrNull()
            var autonomia: Float? = txtAutonomia.text.toString().toFloatOrNull()
            var distancia: Float? = txtDistancia.text.toString().toFloatOrNull()

            if ((preco == null) || (autonomia == null) || (distancia == null))
                Toast.makeText(
                    this@MainActivity,
                    "Por favor, redija numeros válidos",
                    Toast.LENGTH_LONG
                ).show()
            else {
                Toast.makeText(this@MainActivity, "Não beba antes de dirigir", Toast.LENGTH_SHORT).show()
                resultado = btnCalcular_onClick(preco, autonomia, distancia)
                lblResultado.text = "R$ ${String.format("%.2f", resultado)}"
            }


        }


    }


}