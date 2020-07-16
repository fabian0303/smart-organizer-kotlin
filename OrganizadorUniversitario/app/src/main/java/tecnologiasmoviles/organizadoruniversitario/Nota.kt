package tecnologiasmoviles.organizadoruniversitario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nota")
class Nota(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo val refCurso: Int,
    @ColumnInfo val nota: Float,
    @ColumnInfo val porcentaje: Float,
    @ColumnInfo  val esAprobatorio: Boolean
)
