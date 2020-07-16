package tecnologiasmoviles.organizadoruniversitario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archivo")
class Archivo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val refCurso: Integer,
    @ColumnInfo val tipo: String,
    @ColumnInfo val path: String
)
