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

    @PrimaryKey(autoGenerate = false)
    val id:String = dia+bloque
)
