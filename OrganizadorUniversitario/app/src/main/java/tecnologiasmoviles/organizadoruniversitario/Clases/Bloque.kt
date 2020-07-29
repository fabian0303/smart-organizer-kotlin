package tecnologiasmoviles.organizadoruniversitario.Clases

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bloque")
class Bloque(

    @ColumnInfo val nombreCurso: String,
    @ColumnInfo val dia: String,
    @ColumnInfo val bloque: String,
    @ColumnInfo val color: Int,
    @ColumnInfo val horaInicio: String,
    @ColumnInfo val horaFin: String,

    @PrimaryKey(autoGenerate = false)
    val id:String = dia+bloque
)
