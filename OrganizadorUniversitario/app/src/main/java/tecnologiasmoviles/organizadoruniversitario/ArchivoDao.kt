package tecnologiasmoviles.organizadoruniversitario

import androidx.room.*

@Dao
interface ArchivoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarArchivo(archivo: Archivo)

    @Update
    fun actualizarArchivo(archivo: Archivo)

    @Delete
    fun eliminarArchivo(archivo: Archivo)

    @Query(value = "SELECT * FROM archivo")
    fun obtenerArchivo(): List<Archivo>
}