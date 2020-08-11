package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.toolBar
import kotlinx.android.synthetic.main.activity_home__curso.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
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
    lateinit var cursoDao: CursoDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__curso)
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        curso_base = intent.getSerializableExtra("Curso") as Curso



        //se le añade el nombre al toolbar
        toolBar.setTitle(curso_base.nombre)
        setSupportActionBar(toolBar)

        //Metodos.
        AbrirGaleria()

    }

    override fun onStart() {
        curso_base=cursoDao.obtenerCursoEspecifico(curso_base.id)
        //se le añade el nombre al toolbar
        toolBar.setTitle(curso_base.nombre)
        setSupportActionBar(toolBar)
        super.onStart()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_curso, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId;
        if(id == R.id.modificar_Curso){
            Toast.makeText(this,"Modificar "+ curso_base.nombre, Toast.LENGTH_LONG).show()
            val intent = Intent(this,modificarCursoActivity::class.java)
            intent.putExtra("Curso_Actual", curso_base)
            startActivity(intent)

        }
        if(id == R.id.eliminar_Curso){
            Toast.makeText(this,"Eliminar "+ curso_base.nombre, Toast.LENGTH_LONG).show()
            cursoDao.eliminarCurso(curso_base)
            onBackPressed()
        }


        return super.onOptionsItemSelected(item)
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