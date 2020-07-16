package tecnologiasmoviles.organizadoruniversitario

import androidx.room.*

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