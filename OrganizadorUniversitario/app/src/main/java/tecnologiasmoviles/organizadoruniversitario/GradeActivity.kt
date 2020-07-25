package tecnologiasmoviles.organizadoruniversitario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_grade.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.NotasAdapter
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Vistas.NotaDetalle
import tecnologiasmoviles.organizadoruniversitario.my_fragment.notaDao

class GradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)
        setUp()


        val notas_curso1 =
            Nota(
                1,
                1,
                5.toFloat(),
                10.toFloat(),
                false
            )
        //val notas_curso2= Nota("Calculo I", arrayOf(5.5,4.3,4.0,5.2,6.0))
        //val notas_curso3 = Nota("Calculo II", arrayOf(5.0,4.0,4.0,5.0,6.0))

        notaDao = AppDatabase.getInstance(this).notaDao()
        notaDao.agregarNota(notas_curso1)

        val lista_notas = ArrayList<Nota>(notaDao.obtenerNota())

        // val notas_curso1 = Nota("Ingenieria Economica", arrayOf(5.7,4.1,4.1,5.2,6.8))
       // val notas_curso2= Nota("Calculo I", arrayOf(5.5,4.3,4.0,5.2,6.0))
       // val notas_curso3 = Nota("Calculo II", arrayOf(5.0,4.0,4.0,5.0,6.0))

       // val lista_notas = listOf(notas_curso1,notas_curso2,notas_curso3)

        val adapter =
            NotasAdapter(
                this,
                lista_notas
            )

        lista.adapter = adapter

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, NotaDetalle::class.java )
           // intent.putExtra("Nota", lista_notas[position])
            startActivity(intent)
        }

    }
    private  fun setUp(){
        title = "Notas"
    }
}
