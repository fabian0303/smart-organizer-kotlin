package tecnologiasmoviles.organizadoruniversitario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_nota.view.*

class NotasAdapter(private val mContext: Context, private val listaNotas: List<Nota>):ArrayAdapter<Nota>(mContext,0,listaNotas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_nota,parent,false)

        val notas = listaNotas[position]

        layout.nombreCurso.text = notas.nombreCurso
        layout.nota.text = notas.numeroNota.toString()

        return layout
    }
}