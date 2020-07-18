package tecnologiasmoviles.organizadoruniversitario

import androidx.room.*

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
}