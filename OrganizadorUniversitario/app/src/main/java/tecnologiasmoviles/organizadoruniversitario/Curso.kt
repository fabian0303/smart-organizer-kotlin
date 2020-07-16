package tecnologiasmoviles.organizadoruniversitario

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "curso")
class Curso(
    @PrimaryKey val uid: Int,
    val nombre:String
)
