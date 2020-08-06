package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ActionMenuView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_opciones_imagen.*
import tecnologiasmoviles.organizadoruniversitario.R

class OpcionesImagenActivity : AppCompatActivity() {
    private val REQUEST_GALERIA = 1001
    private val REQUEST_FOTO = 1002
    var foto: Uri? = null
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

        tomarFotografia()
        guardarImagen()
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
            REQUEST_FOTO -> {
                if(grantResults[0] ==PackageManager.PERMISSION_GRANTED ){
                    abrirCamara()
                }else{
                    Toast.makeText(applicationContext,"No hay permisos para abrir la camara", Toast.LENGTH_LONG).show()

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
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_FOTO){
            viewImagen.setImageURI(foto)
        }
    }

    private fun tomarFotografia(){
      tomarFoto.setOnClickListener {
          if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
              if ( checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                  || checkSelfPermission( Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                  //pedimos permiso
                  val permisosCamara = arrayOf( Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                  requestPermissions(permisosCamara,REQUEST_FOTO)
              }else{
                  abrirCamara()
              }
          }else{
              abrirCamara()
          }
      }
    }
    // abrir la camara del telefono
    private  fun abrirCamara(){
        val value = ContentValues()
        value.put(MediaStore.Images.Media.TITLE, "Nueva imagen")
        foto = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
        val camaraIntent  = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
        startActivityForResult(camaraIntent,REQUEST_FOTO)
    }

    private fun guardarImagen(){

    }
}