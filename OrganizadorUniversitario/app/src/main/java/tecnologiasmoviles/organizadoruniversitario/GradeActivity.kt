package tecnologiasmoviles.organizadoruniversitario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_grade.*

class GradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade)
        setUp()
        val notas_curso1 = Nota("Ingenieria Economica", arrayOf(5.7,4.1,4.1,5.2,6.8))
        val notas_curso2= Nota("Calculo I", arrayOf(5.5,4.3,4.0,5.2,6.0))
        val notas_curso3 = Nota("Calculo II", arrayOf(5.0,4.0,4.0,5.0,6.0))

        val lista_notas = listOf(notas_curso1,notas_curso2,notas_curso3)

        val adapter = NotasAdapter(this,lista_notas)

        lista.adapter = adapter

    }
    private  fun setUp(){
        title = "Notas"
    }
}
