package tecnologiasmoviles.organizadoruniversitario

import android.app.ActionBar
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import tecnologiasmoviles.organizadoruniversitario.my_fragment.MyPageAdapter


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


        //======Brayan=================
        toolBar.setTitle("tab Layout")
        setSupportActionBar(toolBar)

        val fragmentAdapter = MyPageAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)


    }
    /*
    private  fun setUp(email: String, provider: String){
        title = "Inicio"
        emailTextView.text = email
        providerTextView2.text = provider
        logOutButton.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file),Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
*/
    public fun notas(notas: View){
        //val notasIntent = Intent(this,GradeActivity::class.java)
        //startActivity(notasIntent)

    }
}
