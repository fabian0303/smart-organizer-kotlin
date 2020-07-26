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



    }
    private  fun setUp(){
        title = "Notas"
    }
}
