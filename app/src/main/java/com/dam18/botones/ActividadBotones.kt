package com.dam18.botones

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*

class ActividadBotones : AppCompatActivity() {

    private var datos: ArrayList<Int> = ArrayList()
    private var usuario: Int = 0;
    private var max: Int = 3;
    private lateinit var BRojo: Button
    private lateinit var BVerde: Button
    private lateinit var BAmarillo: Button
    private lateinit var BAzul: Button
    private lateinit var Inicio: Button
    private lateinit var mens: Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_botones)
        this.setTitle("Vas en " + (max -2))
        this.BRojo = findViewById<Button>(R.id.bRojo)
        this.BVerde = findViewById<Button>(R.id.bVerde)
        this.BAzul = findViewById<Button>(R.id.bAzul)
        this.BAmarillo = findViewById<Button>(R.id.bAmarillo)
        this.Inicio = findViewById<Button>(R.id.bStart)
        BRojo.setBackgroundColor(Color.RED)
        BVerde.setBackgroundColor(Color.GREEN)
        BAzul.setBackgroundColor(Color.BLUE)
        BAmarillo.setBackgroundColor(Color.YELLOW)
        Inicio.setOnClickListener() {
            Inicio.visibility = View.INVISIBLE
            inicioPrograma()
        }
    }

    fun inicioPrograma(){
         this.setTitle("Vas en " + (max -2))
        this.mens = Toast.makeText(applicationContext, "Simon dice $max colores", Toast.LENGTH_SHORT)
        this.mens.show()
        BRojo.isClickable = false
        BAmarillo.isClickable = false
        BAzul.isClickable = false
        BVerde.isClickable = false
        arrancar()
    }

    fun arrancar() {
        var delayed: Long = 2000;
        for (i in 0..max -1 step 1) {
            val x = Random().nextInt(3)
            when (x) {
                0 -> lightButton(BRojo, Color.argb(80, 255, 0, 0), Color.RED, delayed, i)
                1 -> lightButton(BAmarillo, Color.argb(80, 255, 255, 0), Color.YELLOW, delayed, i)
                2 -> lightButton(BAzul, Color.argb(80, 0, 191, 255), Color.BLUE, delayed, i)
                3 -> lightButton(BVerde, Color.argb(80, 0, 255, 0), Color.GREEN, delayed, i)
            }
            datos.add(x)
            delayed += 2000
        }
        delayed -= 2000
        Handler().postDelayed({
            BRojo.setOnClickListener() {
                checkIt(0)
            }
            BAmarillo.setOnClickListener() {
                checkIt(1)
            }
            BAzul.setOnClickListener() {
                checkIt(2)
            }
            BVerde.setOnClickListener() {
                checkIt(3)
            }
        }, delayed)
    }
    fun lightButton(boton: Button, argb: Int, colorOriginal: Int, delayed: Long, i: Int){

        Handler().postDelayed({
            boton.setBackgroundColor(argb)
            Handler().postDelayed({
                boton.setBackgroundColor(colorOriginal)
            }, 1000)
        }, delayed)
    }
    fun checkIt(nBoton: Int) {
        if (datos.get(usuario) == nBoton) {
            if (usuario < (max -1)) {
                usuario++
                mens.cancel()

            } else if (usuario == (max -1)) {
                usuario++
                mens.setText("Bien")
                max += 1
                usuario = 0
                datos.clear()
                inicioPrograma()
            }

        } else {
            max = 3
            usuario = 0
            datos.clear()
            Inicio.visibility = View.VISIBLE
            mens.setText("MAAAAAAL")
        }
        mens.show()
    }
}
