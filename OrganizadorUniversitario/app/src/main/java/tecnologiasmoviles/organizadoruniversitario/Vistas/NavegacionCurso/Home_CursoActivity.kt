package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.toolBar
import kotlinx.android.synthetic.main.activity_home__curso.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class Home_CursoActivity : AppCompatActivity() {
    /**
     * Variables de la actividad
     */
    lateinit var curso_base: Curso
    lateinit var galeria : ImageButton
    lateinit var audio : ImageButton
    lateinit var video : ImageButton
    lateinit var notas : ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__curso)

        curso_base = intent.getSerializableExtra("Curso") as Curso

        //se le añade el nombre al toolbar
        toolBar.setTitle(curso_base.nombre)
        setSupportActionBar(toolBar)

        AbrirGaleria()

    }
    private fun AbrirGaleria(){
        galeria = findViewById(R.id.galeriaBtn)
        galeria.setOnClickListener {
            Toast.makeText(this, "Abrir galeria",Toast.LENGTH_LONG).show()
            val intent = Intent(this, GaleriaImagenesActivity::class.java )
            intent.putExtra("Curso_Actual", curso_base)
            startActivity(intent)
        }
    }
}