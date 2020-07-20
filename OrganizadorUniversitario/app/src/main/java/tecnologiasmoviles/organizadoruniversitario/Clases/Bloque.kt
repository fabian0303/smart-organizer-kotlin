package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bloque")
class Bloque(
    @PrimaryKey(autoGenerate = true)
    val id:Int,

    @ColumnInfo val horaInicio: String,
    @ColumnInfo val horaTermino: String

)
