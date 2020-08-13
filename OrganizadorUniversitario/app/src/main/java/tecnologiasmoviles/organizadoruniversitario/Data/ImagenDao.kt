package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Imagen

@Dao
interface ImagenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserImagen(imagen :Imagen)

    @Update
    fun updateImagen(imagen :Imagen)

    @Delete
    fun deleteImagen(imagen :Imagen)

    @Query(value = "SELECT * FROM imagen")
    fun gelAllImg(): List<Imagen>

    @Query(value = "SELECT * FROM imagen WHERE imagen.refCurso =:id_curso")
    fun gelAllImgOfCurso(id_curso:Int): List<Imagen>


}
