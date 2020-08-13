package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_imagen.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Imagen
import tecnologiasmoviles.organizadoruniversitario.R

class ImagenAdaptador(private val mContext: Context, private val listaImagenes: List<Imagen>):
    ArrayAdapter<Imagen>(mContext,0,listaImagenes) {

    //@SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_imagen_miniatura,parent,false)

        val imagen = listaImagenes[position]

        //layout.descripcionTxt.text = imagen.refCurso.toString()
        val bitmap:Bitmap = convertByteTOImage(imagen.imagen)
        layout.image_view.setImageBitmap(bitmap)
        //layout.curso.colorCursoBtn?.setBackgroundColor(curso.color)
        //layout.curso.colorCursoBtn?.setText(curso.color)

        return layout
    }

    private fun convertByteTOImage(lista:ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(lista,0,lista.size)
    }
}