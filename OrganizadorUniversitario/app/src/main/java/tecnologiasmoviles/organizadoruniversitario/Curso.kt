package tecnologiasmoviles.organizadoruniversitario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curso")
class Curso(
     @PrimaryKey(autoGenerate = true)
     val id:Int,

     @ColumnInfo val nombre:String
)
