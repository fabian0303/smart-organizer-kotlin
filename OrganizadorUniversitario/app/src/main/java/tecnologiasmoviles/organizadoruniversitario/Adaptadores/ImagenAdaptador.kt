package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.item_curso.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Archivo
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class ImagenAdaptador(private val mContext: FragmentActivity, private val listaImagenes: List<Archivo>):
    ArrayAdapter<Archivo>(mContext,0,listaImagenes) {

    //@SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_imagen,parent,false)

        val imagen = listaImagenes[position]

        //layout.curso.text = imagen.nombre
        //layout.curso.colorCursoBtn?.setBackgroundColor(curso.color)
        //layout.curso.colorCursoBtn?.setText(curso.color)

        return layout
    }
}