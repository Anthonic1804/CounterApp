package com.home.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.home.counterapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        asignarConteo()
    }

    override fun onStart() {
        super.onStart()
        //METODO PARA DETECTAR UN CLIC
        binding.counterBtn.setOnClickListener {
            count++
            asignarConteo()
        }

        //METODO PARA DETECTAR QUE SE MANTIENE PRECIONADO EL BOTON
        binding.counterBtn.setOnLongClickListener {
            count = 0
            asignarConteo()
            Toast.makeText(this@MainActivity, "CONTEO REINICIADO", Toast.LENGTH_SHORT).show()
            true
        }
    }

    private fun asignarConteo() {
        binding.counterTv.text = count.toString()
    }

    //FUNCION PARA MANTENER EL VALOR DE LA VARIABLE EN MEMORIA
    //AL MOMENTO DE GIRAR LA PANTALLA Y SE REINICIE LA ACTIVIDAD
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(PARAM_COUNT, count)
        }
        super.onSaveInstanceState(outState)
    }

    //RESTAURAR EL VALOR DE LA VARIABLE
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        count = savedInstanceState.getInt(PARAM_COUNT)
        asignarConteo()
        super.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        private const val PARAM_COUNT: String = "count"
    }
}