package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "archivo")
class Archivo(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo val refCurso: Int,
    @ColumnInfo val tipo: String,
    @ColumnInfo val path: String
)
