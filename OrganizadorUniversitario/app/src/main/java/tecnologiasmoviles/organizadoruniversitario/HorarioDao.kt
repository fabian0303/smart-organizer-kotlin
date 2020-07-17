package tecnologiasmoviles.organizadoruniversitario

import androidx.room.*

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