package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_asignar_bloque.*
import kotlinx.android.synthetic.main.item_spinner_row.view.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.SpinnerRowAdapter
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.BloqueDao
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R


class asignarBloqueActivity : AppCompatActivity() {
    lateinit var cursoDao: CursoDao
    lateinit var bloqueDao: BloqueDao
    lateinit var asignarBloque :Button
    lateinit var cencelarRegistro: Button
    val lista_bloques_seleccionados = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_bloque)
        setup()
    }

    @SuppressLint("RestrictedApi")
    private fun setup(){

        var diasArray = arrayOf("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado")
        var diasAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, diasArray)
        val dia_spinner = findViewById(R.id.dia_spinner) as Spinner
        dia_spinner.prompt = "Selecciona el día"
        dia_spinner.adapter = diasAdapter

        // Set up the alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona los bloques que deseas asignar")
        val bloques = arrayOf("1","2","3","4","5","6","7","8","9","10","11")
        val checkedItems = booleanArrayOf(false,false,false,false,false,false,false,false,false,false,false)
        val bloqueButton = findViewById(R.id.bloque_button) as Button
        bloqueButton.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
        }
        builder.setMultiChoiceItems(bloques, checkedItems) { dialog, which, isChecked->
            checkedItems[which] = isChecked
        }
        bloqueButton.text="[]"
        builder.setPositiveButton("OK") { _, _ ->
            bloqueButton.text = ""
            for (i in 0 until bloques.size) {
                val checked = checkedItems[i]
                if (checked) {
                    lista_bloques_seleccionados+=bloques[i]
                }
            }
            bloqueButton.text = lista_bloques_seleccionados.toString()
        }

        asignarBloque= findViewById<Button>(R.id.asignarCursoBtn)
        cencelarRegistro= findViewById<Button>(R.id.cancelarCursoBtn)
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        bloqueDao = AppDatabase.getInstance(this).bloqueDao()

        val lista_cursos = cursoDao.obtenerCurso()
        val curso_spinner = findViewById(R.id.curso_spinner) as Spinner
        val adapter = SpinnerRowAdapter(this, lista_cursos )
        curso_spinner.prompt = "Selecciona un curso"
        curso_spinner.adapter = adapter

        insertarBloque()
        cancelarInsercion()
    }

    /**
     * Registra un curso en la base de datos
     */
    private fun insertarBloque(){
        asignarBloque.setOnClickListener {
            if(lista_bloques_seleccionados.size!=0){
                val cursoSeleccionado = curso_spinner.selectedView.curso_row.text.toString()
                val diaSeleccionado = dia_spinner.selectedItem.toString()
                val background = curso_spinner.selectedView.color_button.background
                val color = (background as ColorDrawable).color
                for (i in 0 until lista_bloques_seleccionados.size) {
                    val bloque = Bloque(cursoSeleccionado,diaSeleccionado,lista_bloques_seleccionados[i], color)
                    bloqueDao.agregarBloque(bloque)
                }
                onBackPressed()
                //asignarBloque.text = "faltan datos"
            }
            else{
                Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Nos regresa al home de nuestra app.
     */
    private fun cancelarInsercion(){
        cencelarRegistro.setOnClickListener {
            onBackPressed()
        }
    }
}