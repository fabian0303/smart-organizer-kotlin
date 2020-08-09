package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_galeria_imagenes.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.CursoAdapter
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.ImagenAdaptador
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.ImagenDao
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.my_fragment.cursoDao
import tecnologiasmoviles.organizadoruniversitario.my_fragment.view1

class GaleriaImagenesActivity : AppCompatActivity() {
    lateinit var curso_actual: Curso
    lateinit var IMG_Dao: ImagenDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria_imagenes)
        curso_actual = intent.getSerializableExtra("Curso_Actual") as Curso
        IMG_Dao = AppDatabase.getInstance(this).imagenDao()

        val lista_imagenes = IMG_Dao.gelAllImg()
        val lista =findViewById(R.id.lista_Imagenes) as ListView
        val adapter =
            ImagenAdaptador(
                this,
                lista_imagenes
            )
        lista.adapter = adapter

        opcionesParaañadirImagenes()
    }
    private fun opcionesParaañadirImagenes(){
        agregarImagenBtn.setOnClickListener {
            val intent = Intent(this, OpcionesImagenActivity::class.java )
            intent.putExtra("Curso ", curso_actual)
            startActivity(intent)
        }
    }
}