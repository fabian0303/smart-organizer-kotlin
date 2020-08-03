package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.ActionMenuView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_opciones_imagen.*
import tecnologiasmoviles.organizadoruniversitario.R

class OpcionesImagenActivity : AppCompatActivity() {
    private val REQUEST_GALERIA = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones_imagen)

        agregarDesdeGaleria.setOnClickListener {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                //verificamos los permisos
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)  == PackageManager.PERMISSION_DENIED){
                    // si no tiene permiso, hay que pedirlo
                    val permisosArchivos = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisosArchivos, REQUEST_GALERIA )
                }else{
                    mostrarGaleria()
                }
            }else{
                //los permisos estan agregados por defecto
                mostrarGaleria()
            }
        }

    }
//verificamos si el usuario le dio permiso a la app, para acceder a la galeria.
    override fun onRequestPermissionsResult( requestCode: Int, permissions: Array<out String>, grantResults: IntArray ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_GALERIA ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mostrarGaleria()
                }else{
                    Toast.makeText(applicationContext,"No hay permisos para acceder Galeria de imagenes", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    private fun mostrarGaleria(){
        //Toast.makeText(applicationContext,"Galeria de imagenes", Toast.LENGTH_LONG).show()
        val intentGaleria = Intent(Intent.ACTION_PICK)
        intentGaleria.type ="image/*"
        startActivityForResult(intentGaleria,REQUEST_GALERIA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode  == REQUEST_GALERIA){
            viewImagen.setImageURI(data?.data)
        }
    }
}