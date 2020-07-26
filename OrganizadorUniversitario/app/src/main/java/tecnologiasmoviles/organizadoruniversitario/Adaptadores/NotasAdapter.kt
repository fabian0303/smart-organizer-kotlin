package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.item_nota.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R

class NotasAdapter(private val mContext: FragmentActivity, private val listaCursos: List<Curso>,private val cursoDao: CursoDao):ArrayAdapter<Curso>(mContext,0,listaCursos){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_nota,parent,false)

        val curso = listaCursos[position]

        layout.nombreCurso.text = curso.nombre

        val lista_notas = cursoDao.obtenerNotasDeUnCurso(curso.nombre)
        val promedio= calcularPromedio(lista_notas)

        layout.nota.text = promedio.toString()

        return layout
    }

    /**
     * Función para calcular el promedio de notas que estan en un arreglo
     */
    public fun calcularPromedio(notas_original:List<Nota>):Double{
        var promedio = 0.0
        for (nota in notas_original){
            promedio = promedio + nota.nota.toDouble()*nota.porcentaje.toDouble()
        }
        return Math.round((promedio)) / 100.0
    }
}