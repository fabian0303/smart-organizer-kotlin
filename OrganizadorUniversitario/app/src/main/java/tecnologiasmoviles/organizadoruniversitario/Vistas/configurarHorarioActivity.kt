package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_configuracion_horario.*
import kotlinx.android.synthetic.main.activity_home.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Horario
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.HorarioDao
import tecnologiasmoviles.organizadoruniversitario.R


class configurarHorarioActivity : AppCompatActivity() {

    lateinit var horarioDao: HorarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_horario)
        setup()
    }

    private fun setup(){

        horarioDao = AppDatabase.getInstance(this).horarioDao()
        var horario = horarioDao.obtenerHorario()
        if(horario!=null) {
            selectHoraInicio.setText(horario.horaInicio.toString() + ":" + horario.minutosInicio.toString())
            selectDuracionBloque.setText(horario.duracionBloque.toString())
            selectMinutosReceso.setText(horario.minutosReceso.toString())
            selectNumeroBloques.setText(horario.numeroBloques.toString())
        }
        else{
            selectHoraInicio.setText("8:30")
            selectDuracionBloque.setText("1")
            selectMinutosReceso.setText("10")
            selectNumeroBloques.setText("11")
        }
        confirmar()
        cancelarOperacion()
    }

    private fun confirmar() {
        aceptarHorarioBtn.setOnClickListener {
            if (selectHoraInicio.text.toString() != "" && selectDuracionBloque.text.toString()!=""
                && selectMinutosReceso.text.toString()!="" && selectNumeroBloques.text.toString()!="") {

                val horaMinutosInicio = selectHoraInicio.text.toString().split(":").toTypedArray()
                val horario_aux = Horario(1, horaMinutosInicio[0].toInt(), horaMinutosInicio[1].toInt(), selectDuracionBloque.text.toString().toInt(), selectMinutosReceso.text.toString().toInt(), selectNumeroBloques.text.toString().toInt())
                //Toast.makeText(this, horarioDao.obtenerHorario().horaInicio.toString(), Toast.LENGTH_SHORT).show()
                val confirmacion = android.app.AlertDialog.Builder(this).create()
                confirmacion.setTitle("Atención, se requiere confirmar los cambios")
                confirmacion.setMessage("Para aplicar los cambios en el horario, presione aceptar")
                confirmacion.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Aceptar")
                {dialog, wich ->
                    horarioDao.actualizarHorario(horario_aux)
                    val intent = Intent(baseContext, HomeActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Horario configurado exitosamente", Toast.LENGTH_SHORT).show()
                }
                confirmacion.setButton(android.app.AlertDialog.BUTTON_NEGATIVE,"Cancelar")
                {dialog, wich -> dialog.dismiss()}
                confirmacion.show()
                confirmacion.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
                confirmacion.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
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