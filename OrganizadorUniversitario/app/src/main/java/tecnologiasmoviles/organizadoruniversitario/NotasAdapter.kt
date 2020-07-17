package tecnologiasmoviles.organizadoruniversitario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.item_nota.view.*
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentNota

    class NotasAdapter(private val mContext: FragmentActivity, private val listaNotas: List<Nota>):ArrayAdapter<Nota>(mContext,0,listaNotas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_nota,parent,false)

        val notas = listaNotas[position]

        layout.nombreCurso.text = notas.refCurso.toString()

        val promedio= 5.0

        layout.nota.text = promedio.toString()

        return layout
    }

    /**
     * Función para calcular el promedio de notas que estan en un arreglo
     */
    public fun calcularPromedio(notas_original:Array<Double>):Double{
        val notas_prom = notas_original.copyOf()
        var promedio = 0.0
        for (nota in notas_prom){
            promedio = promedio + nota
        }
        return Math.round((promedio/notas_prom.size) * 10.0) / 10.0
    }
}