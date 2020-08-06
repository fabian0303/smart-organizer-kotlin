package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario")
class Horario(

    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo val horaInicio: Int,
    @ColumnInfo val minutosInicio: Int,
    @ColumnInfo val duracionBloque: Int,
    @ColumnInfo val minutosReceso: Int,
    @ColumnInfo val numeroBloques: Int

)
