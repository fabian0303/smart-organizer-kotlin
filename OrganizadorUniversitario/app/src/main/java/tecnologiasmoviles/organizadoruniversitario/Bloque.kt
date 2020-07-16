package tecnologiasmoviles.organizadoruniversitario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "bloque")
class Bloque(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val horaInicio: Time,
    @ColumnInfo val horaTermino: Time

)
