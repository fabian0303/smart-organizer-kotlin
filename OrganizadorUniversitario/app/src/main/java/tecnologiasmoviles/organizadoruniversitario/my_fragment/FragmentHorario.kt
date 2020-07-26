package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.BloqueDao
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.Vistas.asignarBloqueActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentHorario : Fragment() {

    lateinit var bloqueDao: BloqueDao
    val textViews = ArrayList<TextView>()

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

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? )
            : View? {
        val view= inflater.inflate(R.layout.fragment_horario, container, false)
        //val text_nombre = view.findViewById(R.id.miNombre) as TextView
        //val btn_nombre =  view.findViewById(R.id.myButton) as Button
        //val btn_cambio =  view.findViewById(R.id.cambioNombreBtn) as Button

        //========================================

        bloqueDao = AppDatabase.getInstance(activity!!).bloqueDao()

        val grid = view.findViewById(R.id.gridlayout) as GridLayout
        val scrollView = view.findViewById(R.id.scrollView) as ScrollView

        val botonFlotante = view.findViewById(R.id.añadirAsignatura_btn) as com.google.android.material.floatingactionbutton.FloatingActionButton
        botonFlotante.setColorFilter(Color.WHITE)
        botonFlotante.setOnClickListener {
            val intent = Intent(context, asignarBloqueActivity::class.java)
            startActivity(intent)
        }
        //se muestra boton flotante por 3 segundos y luego desaparece
        botonFlotante.postDelayed({ botonFlotante.hide() }, 3000)

        val ancho = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        var dias = 6
        var bloques = 11
        val anchoDias = (ancho-90)/dias

        val lunes_text = view.findViewById(R.id.lunesText) as TextView
        val martes_text = view.findViewById(R.id.martesText) as TextView
        val miercoles_text = view.findViewById(R.id.miercolesText) as TextView
        val jueves_text = view.findViewById(R.id.juevesText) as TextView
        val viernes_text = view.findViewById(R.id.viernesText) as TextView
        val sabado_text = view.findViewById(R.id.sabadoText) as TextView



        grid.rowCount = bloques
        grid.columnCount = dias + 1

        lunes_text.setText("Lun")
        martes_text.setText("Mar")
        miercoles_text.setText("Mié")
        jueves_text.setText("Jue")
        viernes_text.setText("Vie")
        sabado_text.setText("Sáb")
        lunes_text.width = anchoDias
        martes_text.width = anchoDias
        miercoles_text.width = anchoDias
        jueves_text.width = anchoDias
        viernes_text.width = anchoDias
        sabado_text.width = anchoDias

        var contador = 0
        var hora = 8
        var minutos = 3

        //Ciclo para generar bloques en el horario
        while( contador < (bloques)*(dias+1)){

            val bloqueText = TextView(activity)

            bloqueText.setBackgroundResource(R.drawable.back) //borde de color gris para los textview
            bloqueText.id = contador
            bloqueText.textSize = 11F
            bloqueText.height = 190
            //bloqueText.gravity  = Gravity.CENTER

            if(contador%7 == 0){ //discrimina las posiciones donde se muestran las horas
                bloqueText.text = " "+hora.toString()+":"+minutos.toString()+"0"
                bloqueText.width = 90
                grid.addView(bloqueText)
                hora++
                minutos++
                if(minutos == 6){
                    minutos = 0
                    hora++
                }
            }
            else{
                bloqueText.width = anchoDias/*
                bloqueText.setOnClickListener {
                    val bloque = getBloque(bloqueText.id)
                    val dia = getDia(bloqueText.id)
                    bloqueText.text = "$dia | Bloque $bloque"
                    Toast.makeText(activity, "$dia | Bloque $bloque", Toast.LENGTH_SHORT).show()
                    val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                    bloqueText.setBackgroundColor(color)
                }*/
                grid.addView(bloqueText)
                textViews.add(bloqueText)
            }
            contador++
        }

        //se agrega un bloque vacío para temas de diseño en pantalla
        val bloqueText = TextView(activity)
        bloqueText.height = 50
        bloqueText.width = 90
        grid.addView(bloqueText)
        contador++

        //Funcion recursiva para mostrar/ocultar el botón flotante
        //Al tocar la pantalla, se muestra durante 3 segundos el botón flotante
        //El touch listener se desactiva y se vuelve a activar a los 3 segundos
        fun showHideBotonFlotante (){
            scrollView.setOnTouchListener { view, event ->
                botonFlotante.show()
                botonFlotante.postDelayed({ botonFlotante.hide() }, 3000)
                scrollView.setOnTouchListener { _, _ ->  false }
                scrollView.postDelayed({showHideBotonFlotante()},3000)
                false
            }
        }
        showHideBotonFlotante()
        // Inflate the layout for this fragment
        return view
    }

    fun setBloque(curso: String, dia: String, bloque: String, color: Int){
        if(dia=="Lunes"){
            val bloqueINT = bloque.toInt()
            textViews[0+(6*(bloqueINT-1))].text = curso
            textViews[0+(6*(bloqueINT-1))].setBackgroundColor(color)
            textViews[0+(6*(bloqueINT-1))].invalidate()
        }
        else if(dia=="Martes"){
            val bloqueINT = bloque.toInt()
            textViews[1+(6*(bloqueINT-1))].text = curso
            textViews[1+(6*(bloqueINT-1))].setBackgroundColor(color)
        }
        else if(dia=="Miercoles"){
            val bloqueINT = bloque.toInt()
            textViews[2+(6*(bloqueINT-1))].text = curso
            textViews[2+(6*(bloqueINT-1))].setBackgroundColor(color)
        }
        else if(dia=="Jueves"){
            val bloqueINT = bloque.toInt()
            textViews[3+(6*(bloqueINT-1))].text = curso
            textViews[3+(6*(bloqueINT-1))].setBackgroundColor(color)
        }
        else if(dia=="Viernes"){
            val bloqueINT = bloque.toInt()
            textViews[4+(6*(bloqueINT-1))].text = curso
            textViews[4+(6*(bloqueINT-1))].setBackgroundColor(color)
        }
        else if(dia=="Sabado"){
            val bloqueINT = bloque.toInt()
            textViews[5+(6*(bloqueINT-1))].text = curso
            textViews[5+(6*(bloqueINT-1))].setBackgroundColor(color)
        }
    }

    fun getBloque (bloqueIndex: Int): Int {
        var bloque = 0
        if(bloqueIndex>0 && bloqueIndex<7){
            bloque = 1
        }
        else if(bloqueIndex>7 && bloqueIndex<14){
            bloque = 2
        }
        else if(bloqueIndex>14 && bloqueIndex<21){
            bloque = 3
        }
        else if(bloqueIndex>21 && bloqueIndex<28){
            bloque = 4
        }
        else if(bloqueIndex>28 && bloqueIndex<35){
            bloque = 5
        }
        else if(bloqueIndex>35 && bloqueIndex<42){
            bloque = 6
        }
        else if(bloqueIndex>42 && bloqueIndex<49){
            bloque = 7
        }
        else if(bloqueIndex>49 && bloqueIndex<56){
            bloque = 8
        }
        else if(bloqueIndex>56 && bloqueIndex<63){
            bloque = 9
        }
        else if(bloqueIndex>63 && bloqueIndex<70){
            bloque = 10
        }
        else if(bloqueIndex>70 && bloqueIndex<77){
            bloque = 11
        }
        else if(bloqueIndex>77 && bloqueIndex<84){
            bloque = 12
        }
        return bloque
    }

    fun getDia(bloqueIndex: Int): String {
        var dia = ""

        if(bloqueIndex==1||bloqueIndex==8||bloqueIndex==15||bloqueIndex==22||
            bloqueIndex==29||bloqueIndex==36||bloqueIndex==43||bloqueIndex==50
            ||bloqueIndex==57||bloqueIndex==64||bloqueIndex==71||bloqueIndex==78){
            dia = "Lunes"
        }
        else if(bloqueIndex==2||bloqueIndex==9||bloqueIndex==16||bloqueIndex==23||
            bloqueIndex==30||bloqueIndex==37||bloqueIndex==44||bloqueIndex==51
            ||bloqueIndex==58||bloqueIndex==65||bloqueIndex==72||bloqueIndex==79){
            dia = "Martes"
        }
        else if(bloqueIndex==3||bloqueIndex==10||bloqueIndex==17||bloqueIndex==24||
            bloqueIndex==31||bloqueIndex==38||bloqueIndex==45||bloqueIndex==52
            ||bloqueIndex==59||bloqueIndex==66||bloqueIndex==73||bloqueIndex==80){
            dia = "Miercoles"
        }
        else if(bloqueIndex==4||bloqueIndex==11||bloqueIndex==18||bloqueIndex==25||
            bloqueIndex==32||bloqueIndex==39||bloqueIndex==46||bloqueIndex==53
            ||bloqueIndex==60||bloqueIndex==67||bloqueIndex==74||bloqueIndex==81){
            dia = "Jueves"
        }
        else if(bloqueIndex==5||bloqueIndex==12||bloqueIndex==19||bloqueIndex==26||
            bloqueIndex==33||bloqueIndex==40||bloqueIndex==47||bloqueIndex==54
            ||bloqueIndex==61||bloqueIndex==68||bloqueIndex==75||bloqueIndex==82){
            dia = "Viernes"
        }
        else if(bloqueIndex==6||bloqueIndex==13||bloqueIndex==20||bloqueIndex==27||
            bloqueIndex==34||bloqueIndex==41||bloqueIndex==48||bloqueIndex==55
            ||bloqueIndex==62||bloqueIndex==69||bloqueIndex==76||bloqueIndex==83){
            dia = "Sabado"
        }

        return dia
    }

    override fun onStart() {
        super.onStart()
        val lista_bloques = ArrayList<Bloque>(bloqueDao.obtenerBloque())
        var contador = 0
        while(contador < lista_bloques.size){
            setBloque(lista_bloques[contador].nombreCurso,lista_bloques[contador].dia,lista_bloques[contador].bloque, lista_bloques[contador].color)
            contador++
        }
    }
}