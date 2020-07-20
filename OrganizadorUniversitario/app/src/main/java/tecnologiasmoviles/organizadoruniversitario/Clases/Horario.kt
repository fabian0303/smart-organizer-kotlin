package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario")
class Horario(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo val refCurso: Int,
    @ColumnInfo val refBloque: Int,
    @ColumnInfo val dia: String,
    @ColumnInfo val sala: String,
    @ColumnInfo val color: String
)
