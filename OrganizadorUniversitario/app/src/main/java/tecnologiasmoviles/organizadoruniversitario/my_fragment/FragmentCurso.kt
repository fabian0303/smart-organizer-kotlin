package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import tecnologiasmoviles.organizadoruniversitario.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var cursoDao: CursoDao

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCurso.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentCurso : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view1 = inflater.inflate(R.layout.fragment_curso, container, false)
        val curso1 = Curso("Robotic")
        val curso2= Curso("IoT")
        val curso3 = Curso("App")



        cursoDao = AppDatabase.getInstance(activity!!).cursoDao()

        cursoDao.agregarCurso(curso1)
        cursoDao.agregarCurso(curso2)
        cursoDao.agregarCurso(curso3)


        val lista_cursos = ArrayList<Curso>(cursoDao.obtenerCurso())

        val lista = view1.findViewById(R.id.lista_cursos) as ListView

        val adapter = CursoAdapter(activity!!,lista_cursos)
        lista.adapter = adapter
        return view1
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCurso.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCurso().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}