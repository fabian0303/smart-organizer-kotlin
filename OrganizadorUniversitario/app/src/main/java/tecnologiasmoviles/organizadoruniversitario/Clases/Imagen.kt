package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "imagen")
class Imagen (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo val refCurso: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val imagen: ByteArray
)
