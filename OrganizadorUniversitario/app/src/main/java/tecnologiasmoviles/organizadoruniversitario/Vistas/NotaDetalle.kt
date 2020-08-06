package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nota_detalle.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.Data.NotaDao


class NotaDetalle : AppCompatActivity() {
    lateinit var cursoDao: CursoDao
    lateinit var notaDao: NotaDao
    lateinit var notas_lista: ArrayList<String>
    lateinit var curso_orig: Curso
    lateinit var notas: List<Nota>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_detalle)
        title = "Detalles"



        curso_orig = intent.getSerializableExtra("Curso") as Curso
        notas_lista = arrayListOf<String>()
        var i = 1
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        notaDao = AppDatabase.getInstance(this).notaDao()
        notas = cursoDao.obtenerNotasDeUnCurso(curso_orig.nombre)
        for (nota in notas){
            var aprobativo = nota.esAprobatorio
            var aproba = "Si"
            if(aprobativo==false){
                aproba = "No"
            }
            notas_lista.add("Nota "+i+": "+nota.nota+"   Porcentaje: "+nota.porcentaje+"%  Aprobativo: "+aproba)
            i++
        }
        lista_notas.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,notas_lista)


        lista_notas.setOnItemClickListener { parent, view, position, id ->

            val nota = notas[position]
            val cursoDialog = AlertDialog.Builder(this).create()
            cursoDialog.setTitle("¿Eliminar?")

            cursoDialog.setButton(
                AlertDialog.BUTTON_POSITIVE, "Volver"
            ) { dialog, which -> dialog.dismiss() }

            cursoDialog.setButton(
                AlertDialog.BUTTON_NEGATIVE, "Eliminar"
            ) { dialog, which ->
                val confirmacion = AlertDialog.Builder(this).create()
                confirmacion.setTitle("¿Eliminar?")
                confirmacion.setMessage("¿Seguro de eliminar la nota?")
                confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Si")
                {dialog, wich ->
                    notaDao.eliminarNota(nota)//se elimina la nota seleccionada
                    onPause()
                    Toast.makeText(this, "Operación realizada", Toast.LENGTH_SHORT).show()
                }
                confirmacion.setButton(AlertDialog.BUTTON_NEGATIVE,"No")
                {dialog, wich -> dialog.dismiss()}
                confirmacion.show()
                confirmacion.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
                confirmacion.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
                dialog.dismiss()
            }
            cursoDialog.show() //se muestra el dialog

            //Configuraciones de botones del notaDialog
            val btnPositive = cursoDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val btnNegative = cursoDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            btnPositive.setTextColor(Color.parseColor("#1D7A9F"))
            btnNegative.setTextColor(Color.parseColor("#1D7A9F"))
            val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 10f
            btnPositive.layoutParams = layoutParams
            btnNegative.layoutParams = layoutParams
        }

        val promedio1 = calcularPromedio(notas)
        curso.text = curso_orig.nombre
        promedio.text = promedio1.toString()

        agregarNota.setOnClickListener {
            val intent = Intent(this, agregarNotaActivity::class.java)
            intent.putExtra("Curso", curso_orig)
            startActivity(intent)
        }



    }

    /**
     * Función para calcular el promedio de notas que estan en un arreglo
     */
    fun calcularPromedio(notas_original:List<Nota>):Double{
        var promedio = 0.0
        for (nota in notas_original){
            promedio = promedio + nota.nota.toDouble()*nota.porcentaje.toDouble()
        }
        return Math.round((promedio)) / 100.0
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}



