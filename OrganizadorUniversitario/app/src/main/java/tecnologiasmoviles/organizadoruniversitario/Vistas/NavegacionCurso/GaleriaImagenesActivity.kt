package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_galeria_imagenes.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home__curso.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.ImagenAdaptador
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.ImagenDao
import tecnologiasmoviles.organizadoruniversitario.R

class GaleriaImagenesActivity : AppCompatActivity() {
    lateinit var curso_actual: Curso
    lateinit var IMG_Dao: ImagenDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_galeria_imagenes)
        curso_actual = intent.getSerializableExtra("Curso_Actual") as Curso
        IMG_Dao = AppDatabase.getInstance(this).imagenDao()


        //se le añade el nombre al toolbar
        toolBar_galeria.setTitle(curso_actual.nombre)
        toolBar_galeria.setSubtitle("Galeria")
        setSupportActionBar(toolBar_galeria)
        OpcionesImagenes()
    }

    override fun onStart() {
        //Obtenemos las imagenes del curso
        val lista_imagenes = IMG_Dao.gelAllImgOfCurso(curso_actual.id)
        val lista =findViewById(R.id.lista_Imagenes) as GridView
        val adapter = ImagenAdaptador( this, lista_imagenes )
        lista.adapter = adapter
        super.onStart()
    }
    private fun OpcionesImagenes(){
        agregarImagenBtn.setOnClickListener {
            val intent = Intent(this, OpcionesImagenActivity::class.java )
            intent.putExtra("Curso", curso_actual)
            startActivity(intent)
        }
    }
}