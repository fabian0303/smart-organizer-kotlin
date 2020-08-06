package tecnologiasmoviles.organizadoruniversitario.my_fragment.fragment_home_curso

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso.OpcionesImagenActivity
import tecnologiasmoviles.organizadoruniversitario.Vistas.NotaDetalle
import tecnologiasmoviles.organizadoruniversitario.Vistas.agregarCursoActivity
import tecnologiasmoviles.organizadoruniversitario.my_fragment.view1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Imagenes_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Imagenes_Fragment : Fragment() {

    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var view1:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 =inflater.inflate(R.layout.fragment_imagenes, container, false)

        val agregarFoto = view1.findViewById(R.id.agregarFotoBtn) as com.google.android.material.floatingactionbutton.FloatingActionButton
        agregarFoto.setColorFilter(Color.WHITE)
        agregarFoto.setOnClickListener {

            val intent = Intent(activity!!, OpcionesImagenActivity::class.java )
            startActivity(intent)
        }

        return view1
    }




}