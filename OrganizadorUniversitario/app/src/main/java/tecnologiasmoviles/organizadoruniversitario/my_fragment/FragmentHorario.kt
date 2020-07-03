package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_schedule.*
import tecnologiasmoviles.organizadoruniversitario.R
import kotlin.random.Random

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentHorario : Fragment() {



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

    override fun onCreateView(  inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? )
            : View? {
        val view= inflater.inflate(R.layout.fragment_horario, container, false)
        //val text_nombre = view.findViewById(R.id.miNombre) as TextView
        //val btn_nombre =  view.findViewById(R.id.myButton) as Button
        //val btn_cambio =  view.findViewById(R.id.cambioNombreBtn) as Button

        //========================================
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        var dias = 6
        var bloques = 11

        val grid = view.findViewById(R.id.gridlayout) as GridLayout
        val lunes_text = view.findViewById(R.id.lunesText) as TextView
        val martes_text = view.findViewById(R.id.martesText) as TextView
        val miercoles_text = view.findViewById(R.id.miercolesText) as TextView
        val jueves_text = view.findViewById(R.id.juevesText) as TextView
        val viernes_text = view.findViewById(R.id.viernesText) as TextView
        val sabado_text = view.findViewById(R.id.sabadoText) as TextView

        lunes_text.setText("LU")
        martes_text.setText("Ma")
        miercoles_text.setText("Mi")
        jueves_text.setText("JU")
        viernes_text.setText("VI")
        sabado_text.setText("SA")

        lunes_text.width = width/dias
        martes_text.width = width/dias
        miercoles_text.width = width/dias
        jueves_text.width = width/dias
        viernes_text.width = width/dias
        sabado_text.width = width/dias

        var contador = 0
        var contadorBloque = 0
        while(contador < bloques*6){
            var bloqueText = TextView(context)
            bloqueText.textSize = 11F
            bloqueText.setOnClickListener {
                Toast.makeText(context, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
                val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                bloqueText.setBackgroundColor(color)
                bloqueText.text = "Tecnologías Móviles"
            }
            if(contador%6 == 0){
                contadorBloque++
            }
            bloqueText.text = "Bloque "+contadorBloque
            grid.addView(bloqueText,width/dias,170)
            contador++
        }





        // Inflate the layout for this fragment
        return view
    }

    public interface FragmentAListener{

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHorario.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHorario().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}