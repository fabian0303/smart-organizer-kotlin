package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_nota_detalle.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao


class NotaDetalle : AppCompatActivity() {
    lateinit var cursoDao: CursoDao
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
        notas = cursoDao.obtenerNotasDeUnCurso(curso_orig.nombre)
        for (nota in notas){
            var aprobativo = nota.esAprobatorio
            var aproba = "Si"
            if(aprobativo==false){
                aproba = "No"
            }
            notas_lista.add("Nota "+i+": "+nota.nota+"   Porcentaje: "+nota.porcentaje+"  Aprobativo: "+aproba)
            i++
        }
        lista_notas.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,notas_lista)
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



