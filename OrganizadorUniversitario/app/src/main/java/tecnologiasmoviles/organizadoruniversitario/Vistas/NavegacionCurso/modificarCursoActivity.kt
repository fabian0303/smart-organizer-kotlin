package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_curso_activity.*
import petrov.kristiyan.colorpicker.ColorPicker
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.CursoDao
import tecnologiasmoviles.organizadoruniversitario.R

class modificarCursoActivity : AppCompatActivity() {
    lateinit var curso_base: Curso
    lateinit var cursoDao: CursoDao
    lateinit var nombreCurso: EditText
    lateinit var nombreProfesorCurso: EditText
    lateinit var colorBtn: Button
    lateinit var cancelar: Button
    lateinit var modificarCurso: Button
    var colorCuro = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_curso)
        curso_base = intent.getSerializableExtra("Curso_Actual") as Curso
        cursoDao = AppDatabase.getInstance(this).cursoDao()
        cargarInfoCurso()
        modificarCurso()
    }

    private fun cargarInfoCurso() {
        nombreCurso = findViewById(R.id.nombreCurso_modificar)
        nombreProfesorCurso = findViewById(R.id.nombreProfesor_modificar)
        colorBtn = findViewById(R.id.colorCursoBtn_modificar)
        cancelar = findViewById(R.id.cancelarCursoBtn_modificar)
        modificarCurso = findViewById(R.id.modificarCursoBtn)
        //cargamos la info del curso-
        nombreCurso.setText(curso_base.nombre)
        colorBtn.setBackgroundColor(curso_base.color)


        colorBtn.setOnClickListener {
            val colorPicker = ColorPicker(this)
            colorPicker.setOnFastChooseColorListener(object : ColorPicker.OnFastChooseColorListener{
                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    colorBtn.setBackgroundColor(color)
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

    }


    private fun modificarCurso(){
        modificarCurso.setOnClickListener {
            if(nombreCurso.text.toString().isNotEmpty()){
                val nombre=nombreCurso.text.toString()
               // val curso= Curso(0,nombre,colorCuro)
                cursoDao.actualizarCurso(curso_base.id, nombre,colorCuro)

                // =cursoDao.obtenerCursoEspecifico(curso_base.id ) as Curso
                Toast.makeText(this, "Curso modificado exitosamente", Toast.LENGTH_SHORT).show()

                onBackPressed()
            }else{
                Toast.makeText(this, "Error al modificar  el curso.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}