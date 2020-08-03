package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_asignar_bloque.*
import kotlinx.android.synthetic.main.item_spinner_row.view.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.SpinnerRowAdapter
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.BloqueDao
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R


class asignarBloqueActivity() : AppCompatActivity() {

    var horaInicio = ArrayList<String>()
    var horaFin = ArrayList<String>()
    lateinit var cursoDao: CursoDao
    lateinit var bloqueDao: BloqueDao
    lateinit var asignarCursoBtn: Button
    lateinit var cencelarRegistro: Button
    val lista_bloques_seleccionados = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_bloque)
        setup()
    }

    @SuppressLint("RestrictedApi")
    private fun setup() {

        var bundle = intent.extras
        horaInicio = bundle!!["inicio"] as ArrayList<String>
        horaFin= bundle!!["fin"] as ArrayList<String>

        var diasArray = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado")
        var diasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, diasArray)
        val dia_spinner = findViewById(R.id.dia_spinner) as Spinner
        dia_spinner.prompt = "Selecciona el día"
        dia_spinner.adapter = diasAdapter

        var bloquesAux = ArrayList<String>()
        var checkedAux = ArrayList<Boolean>()
        var aux = 1
        while(aux<=horaInicio.size){
            bloquesAux.add(aux.toString()+" ("+horaInicio.get(aux-1)+" - "+horaFin.get(aux-1)+")")
            checkedAux.add(false)
            aux++
        }
        val bloques: Array<String> = bloquesAux.toTypedArray()
        val checkedItems: BooleanArray = checkedAux.toBooleanArray()

        val builder = AlertDialog.Builder(this, R.style.CheckBoxItemColor)
        builder.setTitle("Selecciona los bloques que deseas asignar")
        val bloqueButton = findViewById(R.id.bloque_button) as Button
        bloqueButton.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
            dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
        }
        builder.setMultiChoiceItems(bloques, checkedItems) { dialog, which, isChecked ->
            checkedItems[which] = isChecked
        }
        bloqueButton.text = "[]"
        builder.setPositiveButton("OK") { _, _ ->
            lista_bloques_seleccionados.clear()
            for (i in 0 until bloques.size) {
                val checked = checkedItems[i]
                if (checked) {
                    //lista_bloques_seleccionados += bloques[i]
                    lista_bloques_seleccionados += (i+1).toString()
                }
            }
            bloqueButton.text = lista_bloques_seleccionados.toString()
        }
        asignarCursoBtn = findViewById<Button>(R.id.asignarCursoBtn)
        cencelarRegistro = findViewById<Button>(R.id.cancelarCursoBtn)
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        bloqueDao = AppDatabase.getInstance(this).bloqueDao()

        val lista_cursos = cursoDao.obtenerCurso()
        val curso_spinner = findViewById(R.id.curso_spinner) as Spinner
        val adapter = SpinnerRowAdapter(this, lista_cursos)
        curso_spinner.prompt = "Selecciona un curso"
        curso_spinner.adapter = adapter

        insertarBloque()
        cancelarInsercion()
    }

    /**
     * Registra un curso en la base de datos
     */
    private fun insertarBloque() {
        asignarCursoBtn.setOnClickListener {
            if (lista_bloques_seleccionados.size != 0) {
                val cursoSeleccionado = curso_spinner.selectedView.curso_row.text.toString()
                val diaSeleccionado = dia_spinner.selectedItem.toString()
                val color = curso_spinner.selectedView.color_button.backgroundTintList?.defaultColor
                for (i in 0 until lista_bloques_seleccionados.size) {

                    val bloque = color?.let { it1 ->
                        Bloque(
                            cursoSeleccionado,
                            diaSeleccionado,
                            lista_bloques_seleccionados[i],
                            it1,
                            getHoraInicio(lista_bloques_seleccionados[i].toInt()),
                            getHoraFin(lista_bloques_seleccionados[i].toInt())
                        )
                    }
                    if (bloque != null) {
                        bloqueDao.agregarBloque(bloque)
                    }
                }
                onBackPressed()
                Toast.makeText(this, "Curso asignado exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Nos regresa al home de nuestra app.
     */
    private fun cancelarInsercion() {
        cencelarRegistro.setOnClickListener {
            val confirmacion = android.app.AlertDialog.Builder(this).create()
            confirmacion.setTitle("¿Cancelar operación?")
            confirmacion.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Si")
            {dialog, wich ->
                dialog.dismiss()
                onBackPressed()
            }
            confirmacion.setButton(android.app.AlertDialog.BUTTON_NEGATIVE,"No")
            {dialog, wich -> dialog.dismiss()}
            confirmacion.show()
            confirmacion.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
            confirmacion.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
        }
    }

    fun getHoraInicio(bloque: Int): String {
        return horaInicio[bloque - 1]
    }

    fun getHoraFin(bloque: Int): String{
        return horaFin[bloque - 1]
    }
}