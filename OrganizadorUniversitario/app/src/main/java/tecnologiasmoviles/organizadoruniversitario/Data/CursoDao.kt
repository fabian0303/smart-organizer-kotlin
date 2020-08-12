package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Curso
import tecnologiasmoviles.organizadoruniversitario.Clases.Nota

@Dao
interface CursoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarCurso(curso: Curso)

    @Query( value ="Update curso SET nombre=:nuevoNombre, color =:nuevoColor where id=:id_curso")
    fun actualizarCurso(id_curso: Int,nuevoNombre:String,nuevoColor:Int)

    @Delete
    fun eliminarCurso(curso: Curso)

    @Query(value = "SELECT * FROM curso")
    fun obtenerCurso(): List<Curso>

    @Query(value = "SELECT * FROM nota WHERE refCurso = :nombre_curso")
    fun obtenerNotasDeUnCurso(nombre_curso: String): List<Nota>

    @Query(value = "SELECT * FROM curso WHERE id = :id_curso")
    fun obtenerCursoEspecifico(id_curso: Int):Curso
}