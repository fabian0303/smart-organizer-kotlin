package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nota")
data class Nota(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo val refCurso: String,
    @ColumnInfo val nota: Float,
    @ColumnInfo val porcentaje: Float,
    @ColumnInfo val esAprobatorio: Boolean


)
