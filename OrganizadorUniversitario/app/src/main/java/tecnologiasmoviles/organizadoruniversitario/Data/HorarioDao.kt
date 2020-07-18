package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.room.*
import tecnologiasmoviles.organizadoruniversitario.Clases.Horario

@Dao
interface HorarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarHorario(horario: Horario)

    @Update
    fun actualizarHorario(horario: Horario)

    @Delete
    fun eliminarHorario(horario: Horario)

    @Query(value = "SELECT * FROM horario")
    fun obtenerHorario(): List<Horario>
}