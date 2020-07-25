package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.item_curso.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class CursoAdapter(private val mContext: FragmentActivity, private val listacursos: List<Curso>):
    ArrayAdapter<Curso>(mContext,0,listacursos) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_curso,parent,false)

        val curso = listacursos[position]

        layout.curso.text = curso.nombre
        layout.curso.colorCursoBtn?.setBackgroundColor(curso.color)
        //layout.curso.colorCursoBtn?.setText(curso.color)

        return layout
    }
}