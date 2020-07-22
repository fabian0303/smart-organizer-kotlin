package tecnologiasmoviles.organizadoruniversitario.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_agregar_curso_activity.*
import petrov.kristiyan.colorpicker.ColorPicker
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.R

class agregarCursoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_curso_activity)
        setup()
    }

    private fun setup(){
        var colorCuro = 0;
        var colorCursoSeleccionado = findViewById(R.id.colorCursoBtn) as Button
        var registrarCurso =findViewById(R.id.registrarCursobtn) as Button
        var cencelarRegistro =findViewById(R.id.cancelarCursoBtn) as Button
        var curso = null as Curso

        colorCursoSeleccionado.setOnClickListener {

            val colorPicker = ColorPicker(this)
            colorPicker.setOnFastChooseColorListener(object : ColorPicker.OnFastChooseColorListener{
                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    colorCursoSeleccionado.setBackgroundColor(color)
                    colorCuro=color
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


        registrarCurso.setOnClickListener {
            if(colorCuro !=0 && nombreCurso.text.toString().isNotEmpty()){
                //colorCursoBtn.setText("registrado")
                var nombre=nombreCurso.text.toString()
                //curso= Curso(nombre,colorCuro)

            }
            else{
                //colorCursoBtn.setText("No registrado")
            }

        }
        cencelarRegistro.setOnClickListener {
            onBackPressed()
        }
    }
}