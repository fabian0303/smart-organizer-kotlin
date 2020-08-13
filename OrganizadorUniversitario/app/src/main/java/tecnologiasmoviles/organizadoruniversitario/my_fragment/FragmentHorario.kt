package tecnologiasmoviles.organizadoruniversitario.my_fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_configuracion_horario.*
import kotlinx.android.synthetic.main.activity_home.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.Clases.Horario
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.BloqueDao
import tecnologiasmoviles.organizadoruniversitario.Data.HorarioDao
import tecnologiasmoviles.organizadoruniversitario.R
import tecnologiasmoviles.organizadoruniversitario.Vistas.asignarBloqueActivity
import kotlinx.android.synthetic.main.fragment_horario.* // Here


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentHorario : Fragment() {

    lateinit var bloqueDao: BloqueDao
    lateinit var horarioDao: HorarioDao
    val textViews = ArrayList<TextView>()
    val horarioInicioBloques = ArrayList<String>()
    val horarioFinBloques = ArrayList<String>()
    var horaInicio = 8 //8 horas
    var minutosInicio = 30 //30 minutos
    var duracionClase = 1 //1 hora
    var minutosReceso = 10 //10 minutos
    var dias = 6
    var numeroBloques = 11

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
        val view = inflater.inflate(R.layout.fragment_horario, container, false)

        //========================================
        bloqueDao = AppDatabase.getInstance(activity!!).bloqueDao()
        generarHorario()

        val scrollView = view.findViewById(R.id.scrollView) as ScrollView
        val grid = view.findViewById(R.id.gridlayout) as GridLayout

        val ancho = resources.displayMetrics.widthPixels
        val anchoDias = (ancho-90)/dias

        val lunes_text = view.findViewById(R.id.lunesText) as TextView
        val martes_text = view.findViewById(R.id.martesText) as TextView
        val miercoles_text = view.findViewById(R.id.miercolesText) as TextView
        val jueves_text = view.findViewById(R.id.juevesText) as TextView
        val viernes_text = view.findViewById(R.id.viernesText) as TextView
        val sabado_text = view.findViewById(R.id.sabadoText) as TextView

        grid.rowCount = numeroBloques
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
        //Ciclo para generar bloques en el horario
        while( contador < (numeroBloques)*(dias+1)){
            //Se crean textview, los cuales representan los bloques en el horario
            val bloqueText = TextView(activity)
            bloqueText.setBackgroundResource(R.drawable.back) //borde de color gris para los textview
            bloqueText.id = contador
            bloqueText.textSize = 10F
            bloqueText.setTypeface(Typeface.DEFAULT_BOLD)
            bloqueText.gravity = Gravity.CENTER_HORIZONTAL
            bloqueText.height = 190

            //Condicion para discriminar si las posiciones corresponden a la hora (columna izquierda del horario)
            if(contador%(dias+1) == 0){
                if(minutosInicio.toString().length == 1){
                    horarioInicioBloques.add(horaInicio.toString()+":0"+minutosInicio.toString())
                    horarioFinBloques.add((horaInicio+duracionClase).toString()+":0"+minutosInicio.toString())
                    bloqueText.text = " "+horaInicio.toString()+":0"+minutosInicio.toString()
                }
                else{
                    horarioInicioBloques.add(horaInicio.toString()+":"+minutosInicio.toString())
                    horarioFinBloques.add((horaInicio+duracionClase).toString()+":"+minutosInicio.toString())
                    bloqueText.text = " "+horaInicio.toString()+":"+minutosInicio.toString()
                }
                bloqueText.width = 90
                grid.addView(bloqueText)
                horaInicio = horaInicio + duracionClase
                minutosInicio = minutosInicio + minutosReceso
                if(minutosInicio >= 60){
                    minutosInicio = minutosInicio - 60
                    horaInicio++
                }
            }
            else{
                bloqueText.width = anchoDias
                bloqueText.setTextColor(Color.WHITE)
                bloqueText.setOnLongClickListener{
                    if(cursoDao.obtenerCurso().isEmpty()){
                        Toast.makeText(activity!!, "No existen cursos creados para asignar", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val bloque = getBloque(bloqueText.id)
                        val dia = getDia(bloqueText.id)
                        //Toast.makeText(activity, "$dia | Bloque $bloque", Toast.LENGTH_SHORT).show()

                        val intent = Intent(
                            context,
                            asignarBloqueActivity::class.java
                        ) //Se llama a la activity para asignar bloque
                        intent.putExtra(
                            "inicio",
                            horarioInicioBloques
                        ) //Se le pasan los arreglos a la activity
                        intent.putExtra("fin", horarioFinBloques)
                        intent.putExtra("bloque", bloque)
                        intent.putExtra("dia", dia)
                        startActivity(intent)
                    }
                    true
                }
                /*
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

        //Se agrega un bloque vacío para temas de diseño en pantalla
        val bloqueText = TextView(activity)
        bloqueText.height = 50
        bloqueText.width = 90
        grid.addView(bloqueText)

        val botonFlotante = view.findViewById(R.id.añadirAsignatura_btn) as com.google.android.material.floatingactionbutton.FloatingActionButton
        botonFlotante.setColorFilter(Color.WHITE)
        botonFlotante.setOnClickListener {
            if(cursoDao.obtenerCurso().isEmpty()){
                Toast.makeText(activity!!, "No existen cursos creados para asignar", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(context, asignarBloqueActivity::class.java) //Se llama a la activity para asignar bloque
                intent.putExtra("inicio", horarioInicioBloques) //Se le pasan los arreglos a la activity
                intent.putExtra("fin", horarioFinBloques)
                startActivity(intent)
            }
        }

        //Funcion recursiva para mostrar/ocultar el botón flotante
        //Al tocar la pantalla (scrollview) se muestra durante 3 segundos el botón flotante
        //El touch listener se desactiva y se vuelve a activar a los 3 segundos
        fun showHideBotonFlotante (){
            scrollView.setOnTouchListener { view, event ->
                botonFlotante.show()
                //botonFlotante.postDelayed({ botonFlotante.hide() }, 3000)
                scrollView.setOnTouchListener { _, _ ->  false }
                scrollView.postDelayed({
                    botonFlotante.hide()
                    showHideBotonFlotante()
                },3000)
                false
            }
        }

        //Se crea un listener en el TabLayout para saber si éste fragmento está seleccionado
        //Si está seleccionado, se muestra el boton flotante por 3 segundos y luego desaparece
        activity!!.tabLayoutHome!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position==1){
                    botonFlotante.show()
                    botonFlotante.postDelayed({ botonFlotante.hide() }, 3000)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
        showHideBotonFlotante()
        getSetBloques()
        return view
    }

    fun setBloque(bloque: Bloque){
        val gd = GradientDrawable()
        gd.setColor(bloque.color)
        //gd.cornerRadius = 5f
        gd.setStroke(2, Color.parseColor("#808080"))
        if(bloque.dia=="Lunes"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[0+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[0+(6*(bloqueINT-1))].background = gd
            //textViews[0+(6*(bloqueINT-1))].setBackgroundColor(bloque.color)
            textViews[0+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
        }
        else if(bloque.dia=="Martes"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[1+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[1+(6*(bloqueINT-1))].background = gd
            textViews[1+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
        }
        else if(bloque.dia=="Miercoles"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[2+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[2+(6*(bloqueINT-1))].background = gd
            textViews[2+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
        }
        else if(bloque.dia=="Jueves"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[3+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[3+(6*(bloqueINT-1))].background = gd
            textViews[3+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
        }
        else if(bloque.dia=="Viernes"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[4+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[4+(6*(bloqueINT-1))].background = gd
            textViews[4+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
        }
        else if(bloque.dia=="Sabado"){
            val bloqueINT = bloque.bloque.toInt()
            textViews[5+(6*(bloqueINT-1))].text = bloque.nombreCurso
            textViews[5+(6*(bloqueINT-1))].background = gd
            textViews[5+(6*(bloqueINT-1))].setOnClickListener({
                clickCurso(bloque)
            })
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
        else if(bloqueIndex>84 && bloqueIndex<91){
            bloque = 13
        }
        else if(bloqueIndex>91 && bloqueIndex<98){
            bloque = 14
        }
        else if(bloqueIndex>98 && bloqueIndex<105){
            bloque = 15
        }
        return bloque
    }

    fun getDia(bloqueIndex: Int): String {
        var dia = ""

        if(bloqueIndex==1||bloqueIndex==8||bloqueIndex==15||bloqueIndex==22||
            bloqueIndex==29||bloqueIndex==36||bloqueIndex==43||bloqueIndex==50
            ||bloqueIndex==57||bloqueIndex==64||bloqueIndex==71||bloqueIndex==78
             ||bloqueIndex==85||bloqueIndex==92||bloqueIndex==99||bloqueIndex==106){
            dia = "lu"
        }
        else if(bloqueIndex==2||bloqueIndex==9||bloqueIndex==16||bloqueIndex==23||
            bloqueIndex==30||bloqueIndex==37||bloqueIndex==44||bloqueIndex==51
            ||bloqueIndex==58||bloqueIndex==65||bloqueIndex==72||bloqueIndex==79
             ||bloqueIndex==86||bloqueIndex==93||bloqueIndex==100||bloqueIndex==107){
            dia = "ma"
        }
        else if(bloqueIndex==3||bloqueIndex==10||bloqueIndex==17||bloqueIndex==24||
            bloqueIndex==31||bloqueIndex==38||bloqueIndex==45||bloqueIndex==52
            ||bloqueIndex==59||bloqueIndex==66||bloqueIndex==73||bloqueIndex==80
            ||bloqueIndex==87||bloqueIndex==94||bloqueIndex==101||bloqueIndex==108){
            dia = "mi"
        }
        else if(bloqueIndex==4||bloqueIndex==11||bloqueIndex==18||bloqueIndex==25||
            bloqueIndex==32||bloqueIndex==39||bloqueIndex==46||bloqueIndex==53
            ||bloqueIndex==60||bloqueIndex==67||bloqueIndex==74||bloqueIndex==81
            ||bloqueIndex==88||bloqueIndex==95||bloqueIndex==102||bloqueIndex==109){
            dia = "ju"
        }
        else if(bloqueIndex==5||bloqueIndex==12||bloqueIndex==19||bloqueIndex==26||
            bloqueIndex==33||bloqueIndex==40||bloqueIndex==47||bloqueIndex==54
            ||bloqueIndex==61||bloqueIndex==68||bloqueIndex==75||bloqueIndex==82
            ||bloqueIndex==89||bloqueIndex==96||bloqueIndex==103||bloqueIndex==110){
            dia = "vi"
        }
        else if(bloqueIndex==6||bloqueIndex==13||bloqueIndex==20||bloqueIndex==27||
            bloqueIndex==34||bloqueIndex==41||bloqueIndex==48||bloqueIndex==55
            ||bloqueIndex==62||bloqueIndex==69||bloqueIndex==76||bloqueIndex==83
            ||bloqueIndex==90||bloqueIndex==97||bloqueIndex==104||bloqueIndex==111){
            dia = "sa"
        }

        return dia
    }


    override fun onStart() {
        generarHorario()
        cleanHorario()
        getSetBloques()
        super.onStart()
    }

    fun generarHorario(){
        //var grid = view.findViewById(R.id.gridlayout) as GridLayout
        horarioDao = AppDatabase.getInstance(activity!!).horarioDao()
        if(horarioDao.obtenerHorario() != null){ //Si ya existe, carga los datos
            horaInicio = horarioDao.obtenerHorario().horaInicio
            minutosInicio =  horarioDao.obtenerHorario().minutosInicio
            duracionClase = horarioDao.obtenerHorario().duracionBloque
            minutosReceso = horarioDao.obtenerHorario().minutosReceso
            numeroBloques = horarioDao.obtenerHorario().numeroBloques
        }
        else{
            val horario_aux = Horario(1,8,30,1,10,11)
            horarioDao.agregarHorario(horario_aux)
        }

    }

    fun getSetBloques(){
        val lista_bloques = checkValues(bloqueDao.obtenerBloque() as ArrayList<Bloque>) //Checkeo valores de los bloques
        var contador = 0
        while(contador < lista_bloques.size){
            setBloque(lista_bloques[contador])
            contador++
        }
    }

    //Se checkean los cambios en los bloques según la configuración del curso
    fun checkValues(lista_bloques: ArrayList<Bloque>): ArrayList<Bloque> {
        var aux = ArrayList<Bloque>()
        var contador = 0
        while(contador < lista_bloques.size){
            if(cursoDao.obtenerCursoEspecifico(lista_bloques[contador].idCurso)!=null){
                lista_bloques[contador].color = cursoDao.obtenerCursoEspecifico(lista_bloques[contador].idCurso).color
                lista_bloques[contador].nombreCurso = cursoDao.obtenerCursoEspecifico(lista_bloques[contador].idCurso).nombre
                bloqueDao.agregarBloque(lista_bloques[contador])
                aux.add(lista_bloques[contador])
            }
            else{
                bloqueDao.eliminarBloque(lista_bloques[contador])
            }
            contador++
        }
        return aux
    }

    @SuppressLint("ResourceAsColor")
    fun clickCurso(bloque: Bloque) {
        val cursoDialog = AlertDialog.Builder(activity).create()
        cursoDialog.setTitle(bloque.nombreCurso)
        cursoDialog.setMessage(bloque.dia+" bloque "+bloque.bloque+"\n" +
                "Hora de inicio: "+bloque.horaInicio+"\n" +
                "Hora de termino: "+bloque.horaFin
        )

        cursoDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Volver"
        ) { dialog, which -> dialog.dismiss() }

        cursoDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Eliminar"
        ) { dialog, which ->
            val confirmacion = AlertDialog.Builder(activity).create()
            confirmacion.setTitle(bloque.nombreCurso)
            confirmacion.setMessage("¿Deseas eliminarlo del horario?")
            confirmacion.setButton(AlertDialog.BUTTON_POSITIVE, "Si")
            {dialog, wich ->
                eliminarCursoBloque(bloque)
                dialog.dismiss()
                Toast.makeText(activity, "Operación realizada", Toast.LENGTH_SHORT).show()
            }
            confirmacion.setButton(AlertDialog.BUTTON_NEGATIVE,"No")
            {dialog, wich -> dialog.dismiss()}
            confirmacion.show()
            confirmacion.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
            confirmacion.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
            dialog.dismiss()
        }
        cursoDialog.show() //se muestra el dialog

        //Configuraciones de botones del cursoDialog
        val btnPositive = cursoDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val btnNegative = cursoDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        btnPositive.setTextColor(Color.parseColor("#1D7A9F"))
        btnNegative.setTextColor(Color.parseColor("#1D7A9F"))
        val layoutParams = btnPositive.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 10f
        btnPositive.layoutParams = layoutParams
        btnNegative.layoutParams = layoutParams
    }

    fun eliminarCursoBloque(bloque: Bloque){
        bloqueDao.eliminarBloque(bloque)
        cleanHorario()
        getSetBloques()
    }

    fun cleanHorario(){
        var contador = 0
        while(contador < textViews.size){
            textViews[contador].text=""
            textViews[contador].setBackgroundResource(R.drawable.back)
            textViews[contador].setOnClickListener(null)
            contador++
        }
    }

}