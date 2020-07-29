package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import tecnologiasmoviles.organizadoruniversitario.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.CursoAdapter
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.Vistas.agregarCursoActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var cursoDao: CursoDao
lateinit var view1:View

class FragmentCurso : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view1 = inflater.inflate(R.layout.fragment_curso, container, false)
        val curso1 = Curso( "Tecnologías Móviles", Color.CYAN)
        val curso2 = Curso( "Ingeniería Económica", Color.GREEN)

        cursoDao = AppDatabase.getInstance(activity!!).cursoDao()

        cursoDao.agregarCurso(curso1)
        cursoDao.agregarCurso(curso2)

        val agregarCurso = view1.findViewById(R.id.agregarCursoBtn) as com.google.android.material.floatingactionbutton.FloatingActionButton
        agregarCurso.setColorFilter(Color.WHITE)
        agregarCurso.setOnClickListener {
            val intent = Intent(activity!!, agregarCursoActivity::class.java)
            startActivity(intent)

        }


        return view1
    }

    override fun onStart() {
        super.onStart()
        val lista_cursos = ArrayList<Curso>(cursoDao.obtenerCurso())

        val lista = view1.findViewById(R.id.lista_cursos) as ListView

        val adapter = CursoAdapter( activity!!, lista_cursos )

        lista.adapter = adapter

    }


}