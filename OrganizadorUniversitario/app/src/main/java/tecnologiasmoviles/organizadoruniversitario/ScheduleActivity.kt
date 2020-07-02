package tecnologiasmoviles.organizadoruniversitario

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlin.random.Random

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

         //obtener dimensiones de pantalla
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels
        ///////////////////

        var dias = 6
        var bloques = 11

        gridlayout.rowCount = bloques + 1 //cantidad de bloques +1

        lunesText.width = width/dias
        martesText.width = width/dias
        miercolesText.width = width/dias
        juevesText.width = width/dias
        viernesText.width = width/dias
        sabadoText.width = width/dias

        var contador = 0
        var contadorBloque = 0
        while(contador < bloques*6){
            var bloqueText = TextView(this)
            bloqueText.textSize = 11F
            bloqueText.setOnClickListener {
                Toast.makeText(this@ScheduleActivity, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
                val color: Int = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
                bloqueText.setBackgroundColor(color)
                bloqueText.text = "Tecnologías Móviles"
            }
            if(contador%6 == 0){
                contadorBloque++
            }
            bloqueText.text = "Bloque "+contadorBloque
            gridlayout.addView(bloqueText,width/dias,170)
            contador++
        }

    }
}
