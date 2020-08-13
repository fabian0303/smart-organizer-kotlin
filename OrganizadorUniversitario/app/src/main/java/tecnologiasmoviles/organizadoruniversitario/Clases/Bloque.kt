package tecnologiasmoviles.organizadoruniversitario.Clases

import android.graphics.drawable.Drawable
import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(tableName = "bloque")
class Bloque(

    @ColumnInfo var nombreCurso: String,
    @ColumnInfo val dia: String,
    @ColumnInfo val bloque: String,
    @ColumnInfo var color: Int,
    @ColumnInfo val horaInicio: String,
    @ColumnInfo val horaFin: String,
    @ColumnInfo val idCurso: Int,

    @PrimaryKey(autoGenerate = false)
    val id:String = dia+bloque
)