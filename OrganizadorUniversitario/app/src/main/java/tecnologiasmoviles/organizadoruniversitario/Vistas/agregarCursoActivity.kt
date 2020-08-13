package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_curso_activity.*
import petrov.kristiyan.colorpicker.ColorPicker
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R

class agregarCursoActivity : AppCompatActivity() {
    lateinit var cursoDao: CursoDao
    var colorCuro = 0;
    lateinit var colorCursoSeleccionado :Button
    lateinit var registrarCurso :Button
    lateinit var cencelarRegistro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_curso_activity)

        setup()
    }

    private fun setup(){
        //Inicializamos variables.
        colorCursoSeleccionado = findViewById<Button>(R.id.colorCursoBtn)
        registrarCurso= findViewById<Button>(R.id.registrarCursobtn)
        cencelarRegistro= findViewById<Button>(R.id.cancelarCursoBtn)
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        colorCursoSeleccionado.setOnClickListener {

            val colorPicker = ColorPicker(this)
            colorPicker.setOnFastChooseColorListener(object : ColorPicker.OnFastChooseColorListener{
                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    colorCursoSeleccionado.setBackgroundColor(color)
                    colorCuro=color
                    //colorCursoSeleccionado.setText(color)
                }

                override fun onCancel() {
                    colorPicker.dismissDialog()
                }

            })
                .disableDefaultButtons(true)
                .setColumns(5)
                .setRoundColorButton(true)
                .show()
        }

        insertCurso()
        cancelarInsertCurso()
    }

    /**
     * Registra un curso en la base de datos
     */
    private fun insertCurso(){
        registrarCurso.setOnClickListener {
            if(colorCuro !=0 && nombreCurso.text.toString().isNotEmpty()){
            var nombre=nombreCurso.text.toString()
            val curso= Curso(0,nombre,colorCuro)
            cursoDao.agregarCurso(curso)

            onBackPressed()
            Toast.makeText(this, "Curso creado exitosamente", Toast.LENGTH_SHORT).show()

        }
            else{
                Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
                //colorCursoBtn.setText("No registrado")
            }

        }
    }

    /**
     * Nos regresa al home de nuestra app.
     */
    private fun cancelarInsertCurso(){
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
}