package tecnologiasmoviles.organizadoruniversitario.Vistas

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import tecnologiasmoviles.organizadoruniversitario.Adaptadores.MyPageAdapter
import tecnologiasmoviles.organizadoruniversitario.R


enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        //setUp(email ?: "",provider ?: "")

        // Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()


        //se le añade el nombre al toolbar
        toolBar.setTitle(R.string.app_name)
        setSupportActionBar(toolBar)
        //Muestro los tabLayout
        val fragmentAdapter =
            MyPageAdapter(
                supportFragmentManager
            )
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_home,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId;
        if(id == R.id.cerrarSesion){
            val salirDialog = AlertDialog.Builder(this).create() //se crea un dialog para confirmar el cierre de sesión
            salirDialog.setTitle("¿Desea cerrar sesión?")
            salirDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Si"
            ) { dialog, which ->
                title = "Inicio"
                val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, AuthActivity::class.java)
                this.finish() //se finaliza la activity actual para llamar a la activity de autenticacinon
                startActivity(intent)
            }
            salirDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO"
            ) { dialog, which -> dialog.dismiss()}
            salirDialog.show()
            salirDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
            salirDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
            //onBackPressed()

        }
        else if(id == R.id.configuracion_horario){
            val intent = Intent(this, configurarHorarioActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val salirDialog = AlertDialog.Builder(this).create()
        salirDialog.setTitle("¿Desea salir de la aplicación?")
        salirDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Si"
        ) { dialog, which -> finishAffinity() }

        salirDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO"
        ) { dialog, which -> dialog.dismiss()}
        salirDialog.show()
        salirDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#1D7A9F"))
        salirDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#1D7A9F"))
    }
}
