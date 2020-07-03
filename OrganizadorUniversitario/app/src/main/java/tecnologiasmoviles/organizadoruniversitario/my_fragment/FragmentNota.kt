package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_grade.*
import kotlinx.android.synthetic.main.fragment_nota.*
import kotlinx.android.synthetic.main.fragment_nota.view.*
import kotlinx.android.synthetic.main.fragment_nota.view.lista1
import kotlinx.android.synthetic.main.fragment_nota.view.lista1
import tecnologiasmoviles.organizadoruniversitario.Nota
import tecnologiasmoviles.organizadoruniversitario.NotaDetalle
import tecnologiasmoviles.organizadoruniversitario.NotasAdapter
import tecnologiasmoviles.organizadoruniversitario.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        val view1 = inflater.inflate(R.layout.fragment_nota, container, false)
        val notas_curso1 = Nota("Ingenieria Economica", arrayOf(5.7,4.1,4.1,5.2,6.8))
        val notas_curso2= Nota("Calculo I", arrayOf(5.5,4.3,4.0,5.2,6.0))
        val notas_curso3 = Nota("Calculo II", arrayOf(5.0,4.0,4.0,5.0,6.0))


        val lista_notas = listOf(notas_curso1,notas_curso2,notas_curso3)

        val lista = view1.findViewById(R.id.lista1) as ListView
        val adapter = NotasAdapter(activity!!,lista_notas)
        lista.adapter = adapter
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(activity!!, NotaDetalle::class.java )
            intent.putExtra("Nota", lista_notas[position])
            startActivity(intent)
        }
        // Inflate the layo ut for this fragment
        return view1
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
}