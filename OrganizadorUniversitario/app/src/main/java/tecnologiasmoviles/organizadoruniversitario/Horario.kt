package tecnologiasmoviles.organizadoruniversitario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horario")
class Horario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val refCurso: Integer,
    @ColumnInfo val refBloque: Integer,
    @ColumnInfo val dia: String,
    @ColumnInfo val sala: String,
    @ColumnInfo val color: String
)
