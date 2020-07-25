package tecnologiasmoviles.organizadoruniversitario.Clases

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "curso")
class Curso(
     @PrimaryKey(autoGenerate = false)
     var nombre:String,
     @NotNull
     var color: Int = 0
):Serializable
