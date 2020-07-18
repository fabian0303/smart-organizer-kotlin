package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso

@Dao
interface CursoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarCurso(curso: Curso)

    @Update
    fun actualizarCurso(curso: Curso)

    @Delete
    fun eliminarCurso(curso: Curso)

    @Query(value = "SELECT * FROM curso")
    fun obtenerCurso(): List<Curso>
}