package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_asignar_bloque.*
import kotlinx.android.synthetic.main.activity_configuracion_horario.*
import kotlinx.android.synthetic.main.item_spinner_row.view.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.R

class configurarHorarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_horario)
        setup()
    }

    private fun setup(){
        confirmar()
        cancelarOperacion()
    }

    private fun confirmar() {
        aceptarHorarioBtn.setOnClickListener {
            if (selectHoraInicio.text.toString() != "" && selectDuracionBloque.text.toString()!=""
                && selectMinutosReceso.text.toString()!="" && selectNumeroBloques.text.toString()!="") {
                onBackPressed()
                Toast.makeText(this, "Horario configurado exitosamente", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelarOperacion() {
        cancelarHorarioBtn.setOnClickListener {
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