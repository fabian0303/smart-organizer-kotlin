package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.item_spinner_row.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R

class SpinnerRowAdapter(private val mContext: FragmentActivity, private val listacursos: List<Curso>):
    ArrayAdapter<Curso>(mContext, 0, listacursos) {

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = convertView ?:LayoutInflater.from(mContext).inflate(R.layout.item_spinner_row,parent,false)
        layout.curso_row.text = listacursos[position].nombre
        layout.color_button.backgroundTintList = ColorStateList.valueOf(listacursos[position].color)
        layout.color_button.isEnabled = false
        //layout.curso.colorCursoBtn?.setText(curso.color)

        return layout
    }
}