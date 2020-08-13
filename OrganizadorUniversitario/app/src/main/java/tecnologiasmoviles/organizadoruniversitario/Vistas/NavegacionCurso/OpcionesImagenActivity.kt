package tecnologiasmoviles.organizadoruniversitario.Vistas.NavegacionCurso

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_opciones_imagen.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Imagen
import tecnologiasmoviles.organizadoruniversitario.Data.AppDatabase
import tecnologiasmoviles.organizadoruniversitario.Data.ImagenDao
import tecnologiasmoviles.organizadoruniversitario.R
import java.io.ByteArrayOutputStream
import java.io.InputStream

class OpcionesImagenActivity : AppCompatActivity() {
    //Variables
    private val REQUEST_GALERIA = 1001
    private val REQUEST_FOTO = 1002
    lateinit var curso_actual: Curso
    lateinit var IMG_Dao: ImagenDao
    var foto: Uri? = null
    lateinit var myImagen :ImageView
     var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones_imagen)
        curso_actual = intent.getSerializableExtra("Curso") as Curso
        IMG_Dao = AppDatabase.getInstance(this).imagenDao()
        myImagen = findViewById(R.id.viewImagen)

        agregarFotoDesdeGaleria()
        tomarFotografia()
        guardarImagen()
        cancelar()
    }
    //Añadimos la imagen desde la galeria.
    private fun mostrarGaleria(){
        val intentGaleria = Intent(Intent.ACTION_PICK)
        intentGaleria.type ="image/*"
        startActivityForResult(intentGaleria,REQUEST_GALERIA)
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

    private fun agregarFotoDesdeGaleria(){
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode  == REQUEST_GALERIA){
            //foto =data?.data
            //myImagen.setImageURI(foto)
            foto = data?.data
            val input : InputStream = foto?.let { contentResolver.openInputStream(it) } as InputStream
            bitmap = BitmapFactory.decodeStream(input)
            myImagen.setImageBitmap(bitmap)
        }
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_FOTO){
            //myImagen.setImageURI(foto)
            val imagenUri : Uri? = foto
            val input :InputStream= imagenUri?.let { contentResolver.openInputStream(it) } as InputStream
            bitmap = BitmapFactory.decodeStream(input)
            myImagen.setImageBitmap(bitmap)
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
        guardarImg.setOnClickListener {
            if(bitmap != null){

                val imagen:ByteArray=convertirImagenTOByArray(bitmap!!)
                val Imagen_1 =Imagen(0,curso_actual.id,imagen)
                IMG_Dao.inserImagen(Imagen_1)
                onBackPressed()
            }


        }
    }
    private fun convertirImagenTOByArray(bitmap: Bitmap):ByteArray{
        val stream= ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,0, stream)
        return stream.toByteArray()
    }

    private fun cancelar(){
        cancelar.setOnClickListener {
            onBackPressed()
        }
    }
}