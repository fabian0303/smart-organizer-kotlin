package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_galeria_imagenes.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class GaleriaImagenesActivity : AppCompatActivity() {
    lateinit var curso_actual: Curso
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria_imagenes)
        curso_actual = intent.getSerializableExtra("Curso_Actual") as Curso

        opcionesParaañadirImagenes()
    }
    private fun opcionesParaañadirImagenes(){
        agregarImagenBtn.setOnClickListener {
            val intent = Intent(this, OpcionesImagenActivity::class.java )
            intent.putExtra("Curso_actual ", curso_actual)
            //Toast.makeText(activity!!, lista_cursos[position].nombre,Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}