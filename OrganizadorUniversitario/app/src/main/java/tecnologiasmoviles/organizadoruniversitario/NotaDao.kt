package tecnologiasmoviles.organizadoruniversitario

import androidx.room.*

@Dao
interface NotaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarNota(nota: Nota)

    @Update
    fun actualizarNota(nota: Nota)

    @Delete
    fun eliminarNota(nota: Nota)

    @Query(value = "SELECT * FROM nota")
    fun obtenerNota(): List<Nota>
}