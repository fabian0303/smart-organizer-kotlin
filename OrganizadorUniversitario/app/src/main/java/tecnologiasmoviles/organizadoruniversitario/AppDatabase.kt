package tecnologiasmoviles.organizadoruniversitario

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [Curso::class,Nota::class,Archivo::class,Horario::class,Bloque::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cursoDao(): CursoDao
    abstract fun notaDao(): NotaDao
    abstract fun archivoDao(): ArchivoDao
    abstract fun bloqueDao(): BloqueDao
    abstract fun horarioDao(): HorarioDao

    companion object{

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
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
