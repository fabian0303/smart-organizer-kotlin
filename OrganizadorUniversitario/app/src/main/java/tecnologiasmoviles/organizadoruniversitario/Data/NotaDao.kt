package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota

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