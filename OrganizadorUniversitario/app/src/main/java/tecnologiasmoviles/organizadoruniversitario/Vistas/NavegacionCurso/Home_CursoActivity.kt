package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolBar
import kotlinx.android.synthetic.main.activity_home.viewPager
import kotlinx.android.synthetic.main.activity_home__curso.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.MyPageAdapter_curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class Home_CursoActivity : AppCompatActivity() {
    lateinit var curso_base: Curso
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home__curso)

        curso_base = intent.getSerializableExtra("Curso") as Curso

        //se le añade el nombre al toolbar
        toolBar.setTitle(curso_base.nombre)
        setSupportActionBar(toolBar)
        //Muestro los tabLayout
        val fragmentAdapter = MyPageAdapter_curso( supportFragmentManager )
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}