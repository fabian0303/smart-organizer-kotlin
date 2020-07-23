package tecnologiasmoviles.organizadoruniversitario.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
                val curso= Curso(nombre,colorCuro)
                cursoDao.agregarCurso(curso)

                onBackPressed()

            }
            else{
                //colorCursoBtn.setText("No registrado")
            }

        }
    }

    /**
     * Nos regresa al home de nuestra app.
     */
    private fun cancelarInsertCurso(){
        cencelarRegistro.setOnClickListener {
            onBackPressed()
        }
    }
}