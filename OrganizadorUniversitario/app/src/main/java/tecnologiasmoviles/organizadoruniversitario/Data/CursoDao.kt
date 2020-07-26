package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota

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

    @Query(value = "SELECT * FROM nota WHERE refCurso = :nombre_curso")
    fun obtenerNotasDeUnCurso(nombre_curso: String): List<Nota>
}