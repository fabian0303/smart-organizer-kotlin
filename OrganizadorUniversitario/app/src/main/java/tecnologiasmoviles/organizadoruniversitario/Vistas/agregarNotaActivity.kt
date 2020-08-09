package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_nota2.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.Data.NotaDao
import tecnologiasmoviles.organizadoruniversitario.R

class agregarNotaActivity : AppCompatActivity() {

    lateinit var registrarNota :Button
    lateinit var cancelarRegistro :Button
    lateinit var notaDao: NotaDao
    lateinit var cursoDao: CursoDao
    lateinit var notas: List<Nota>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_nota2)
        setup()
    }

    private fun setup() {
        registrarNota = findViewById<Button>(R.id.anadirNotaBtn)
        cancelarRegistro = findViewById<Button>(R.id.cancelarAnadirBtn)
        notaDao = AppDatabase.getInstance(this).notaDao()

        val curso_orig = intent.getSerializableExtra("Curso") as Curso

        //spinner aprobativo
        var arrayAprobativo = arrayOf("Si","No")
        var aprobativoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayAprobativo)
        aprobativo_spinner.adapter = aprobativoAdapter


        insertarNota(curso_orig)
        cancelarInsercion()

    }

    /**
     * Registra una nota en la BD
     */
    private fun insertarNota(curso:Curso){
        registrarNota.setOnClickListener {
            if(notaingresada.text.isNotEmpty() && porcentajeingresado.text.isNotEmpty()){
                cursoDao = AppDatabase.getInstance(this).cursoDao()
                val aprobaSeleccionado = aprobativo_spinner.selectedItem.toString()
                val nota1 = notaingresada.text.toString()
                val porcentaje = porcentajeingresado.text.toString()
                var aproba = true
                if(aprobaSeleccionado=="No"){
                    aproba=false
                }
                notas = cursoDao.obtenerNotasDeUnCurso(curso.nombre)
                var suma = 0.0
                for (nota in notas){
                    suma+= nota.porcentaje
                }
                if((suma+porcentaje.toFloat())>100.0){
                    onBackPressed()
                    Toast.makeText(this, "Error: Sobrepasa el 100%", Toast.LENGTH_LONG).show()
                }else{
                    val nota = Nota(0,curso.nombre,nota1.toFloat(),porcentaje.toFloat(),aproba)
                    notaDao.agregarNota(nota)
                    onBackPressed()
                    Toast.makeText(this, "Nota creada exitosamente", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * Nos regresa a la vista anterior.
     */
    private fun cancelarInsercion(){
        cancelarRegistro.setOnClickListener {
            val confirmacion = android.app.AlertDialog.Builder(this).create()
            confirmacion.setTitle("¿Cancelar operación?")
            confirmacion.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Si")
            {dialog, wich ->
                dialog.dismiss()
                onBackPressed()
            }
            confirmacion.setButton(android.app.AlertDialog.BUTTON_NEGATIVE,"No")
            {dialog, wich -> dialog.dismiss()}
            confirmacion.show()
            confirmacion.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
            confirmacion.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
        }
    }

}