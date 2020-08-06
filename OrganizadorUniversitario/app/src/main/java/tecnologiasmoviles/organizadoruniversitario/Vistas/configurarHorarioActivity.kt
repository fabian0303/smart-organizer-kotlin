package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_configuracion_horario.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Horario
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.BloqueDao
import tecnologiasmoviles.organizadoruniversitario.Data.HorarioDao
import tecnologiasmoviles.organizadoruniversitario.R


class configurarHorarioActivity : AppCompatActivity() {

    lateinit var horarioDao: HorarioDao
    lateinit var bloqueDao: BloqueDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion_horario)
        setup()
    }

    private fun setup(){

        horarioDao = AppDatabase.getInstance(this).horarioDao()
        var horario = horarioDao.obtenerHorario()
        if(horario.minutosInicio.toString().length == 1){
            selectHoraInicio.setText(horario.horaInicio.toString() + ":0" + horario.minutosInicio.toString())
        }
        else{
            selectHoraInicio.setText(horario.horaInicio.toString() + ":" + horario.minutosInicio.toString())
        }
        selectDuracionBloque.setText(horario.duracionBloque.toString())
        selectMinutosReceso.setText(horario.minutosReceso.toString())
        selectNumeroBloques.setText(horario.numeroBloques.toString())
        confirmar()
        cancelarOperacion()
        Toast.makeText(this, "Se recomienda primero limpiar el horario para evitar posibles errores", Toast.LENGTH_LONG).show()
    }

    private fun confirmar() {
        aceptarHorarioBtn.setOnClickListener {
            if (selectHoraInicio.text.toString() != "" && selectDuracionBloque.text.toString()!=""
                && selectMinutosReceso.text.toString()!="" && selectNumeroBloques.text.toString()!=""
                && selectNumeroBloques.text.toString().toInt()<16 && selectNumeroBloques.text.toString().toInt()>9) {

                val horaMinutosInicio = selectHoraInicio.text.toString().split(":").toTypedArray()
                val horario_aux = Horario(1, horaMinutosInicio[0].toInt(), horaMinutosInicio[1].toInt(), selectDuracionBloque.text.toString().toInt(), selectMinutosReceso.text.toString().toInt(), selectNumeroBloques.text.toString().toInt())
                //Toast.makeText(this, horarioDao.obtenerHorario().horaInicio.toString(), Toast.LENGTH_SHORT).show()
                val confirmacion = android.app.AlertDialog.Builder(this).create()
                confirmacion.setTitle("Atención, se requiere confirmar los cambios")
                confirmacion.setMessage("Para aplicar los cambios en el horario, presione aceptar")
                confirmacion.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Aceptar")
                {dialog, wich ->
                    if(checkNullPointerBloques()){
                        val confirmacion2 = android.app.AlertDialog.Builder(this).create()
                        confirmacion2.setTitle("Atención, se borrarán algunas asignaciones")
                        confirmacion2.setMessage("Tienes cursos asignados a bloques que se borrarán debido a la nueva configuración del horario")
                        confirmacion2.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "Confirmar")
                        {dialog, wich ->
                            horarioDao.actualizarHorario(horario_aux)
                            deleteNullPointerBloques()
                            val intent = Intent(baseContext, AuthActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Horario configurado exitosamente", Toast.LENGTH_SHORT).show()
                        }
                        confirmacion2.setButton(android.app.AlertDialog.BUTTON_NEGATIVE,"Cancelar")
                        {dialog, wich -> dialog.dismiss()}
                        confirmacion2.show()
                        confirmacion2.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
                        confirmacion2.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
                    }
                    else{
                        horarioDao.actualizarHorario(horario_aux)
                        val intent = Intent(baseContext, AuthActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Horario configurado exitosamente", Toast.LENGTH_SHORT).show()
                    }
                }
                confirmacion.setButton(android.app.AlertDialog.BUTTON_NEGATIVE,"Cancelar")
                {dialog, wich -> dialog.dismiss()}
                confirmacion.show()
                confirmacion.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
                confirmacion.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
            }
            else {
                if(selectNumeroBloques.text.toString().toInt()>15 || selectNumeroBloques.text.toString().toInt()<10){
                    Toast.makeText(this, "Se permiten entre 10 y 15 bloques", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Por favor ingresa los datos solicitados", Toast.LENGTH_SHORT).show()
                }
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

    private fun checkNullPointerBloques(): Boolean {
        bloqueDao = AppDatabase.getInstance(this).bloqueDao()
        var bloques = bloqueDao.obtenerBloque()
        for (i in 0 until bloques.size) {
            if(bloques[i].bloque.toInt()>selectNumeroBloques.text.toString().toInt()){
                return true
            }
        }
        return false
    }

    private fun deleteNullPointerBloques(){
        bloqueDao = AppDatabase.getInstance(this).bloqueDao()
        var bloques = bloqueDao.obtenerBloque()
        for (i in 0 until bloques.size) {
            if(bloques[i].bloque.toInt()>selectNumeroBloques.text.toString().toInt()){
                bloqueDao.eliminarBloque(bloques[i])
            }
        }
    }
}