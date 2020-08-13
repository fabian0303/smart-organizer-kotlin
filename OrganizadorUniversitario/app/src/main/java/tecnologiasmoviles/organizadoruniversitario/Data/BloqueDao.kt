package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Bloque
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso


@Dao
interface BloqueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarBloque(bloque: Bloque)

    @Update
    fun actualizarBloque(bloque: Bloque)

    @Delete
    fun eliminarBloque(bloque: Bloque)

    @Query(value = "SELECT * FROM bloque")
    fun obtenerBloque(): List<Bloque>

    @Query("DELETE FROM bloque")
    fun limpiar()

    @Query("DELETE FROM bloque WHERE nombreCurso = :curso")
    fun eliminarByNombreCurso(curso: String)

}