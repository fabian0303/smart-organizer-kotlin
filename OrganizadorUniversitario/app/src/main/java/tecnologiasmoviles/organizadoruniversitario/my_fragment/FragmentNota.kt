package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import tecnologiasmoviles.organizadoruniversitario.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.CursoAdapter
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.NotasAdapter
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.Data.NotaDao
import tecnologiasmoviles.organizadoruniversitario.Vistas.NotaDetalle

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var notaDao: NotaDao
private lateinit var cursoDao1: CursoDao
lateinit var view2: View

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentNota.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentNota : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var lv:ListView;

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
        view2 = inflater.inflate(R.layout.fragment_nota, container, false)

        cursoDao1 = AppDatabase.getInstance(activity!!).cursoDao()


        val lista_cursos = cursoDao1.obtenerCurso()
        val lista = view2.findViewById(R.id.lista1) as ListView
        val adapter =
            NotasAdapter(
                activity!!,
                lista_cursos,
                cursoDao1
            )
        lista.adapter = adapter

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity!!, NotaDetalle::class.java )
            intent.putExtra("Curso", lista_cursos[position])

            startActivity(intent)
        }

        // Inflate the layo ut for this fragment
        return view2
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentNota.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentNota().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val lista_cursos = cursoDao1.obtenerCurso()
        val lista = view2.findViewById(R.id.lista1) as ListView
        val adapter =
            NotasAdapter(
                activity!!,
                lista_cursos,
                cursoDao1
            )
        lista.adapter = adapter

    }
}