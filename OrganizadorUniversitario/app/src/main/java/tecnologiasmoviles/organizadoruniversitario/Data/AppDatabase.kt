package tecnologiasmoviles.organizadoruniversitario.Data

import androidx.fragment.app.FragmentActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tecnologiasmoviles.organizadoruniversitario.Clases.*


@Database(entities = [Curso::class, Nota::class, Archivo::class, Horario::class, Bloque::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cursoDao(): CursoDao
    abstract fun notaDao(): NotaDao
    abstract fun archivoDao(): ArchivoDao
    abstract fun bloqueDao(): BloqueDao
    abstract fun horarioDao(): HorarioDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: FragmentActivity): AppDatabase {
            if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "smart-organizer-db"
                ).allowMainThreadQueries().build()
            }
            return  INSTANCE!!
        }
    }

}
