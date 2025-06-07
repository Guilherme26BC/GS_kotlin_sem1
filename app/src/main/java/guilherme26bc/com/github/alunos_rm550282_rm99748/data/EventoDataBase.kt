package guilherme26bc.com.github.alunos_rm550282_rm99748.data
import androidx.room.Database
import androidx.room.RoomDatabase
import guilherme26bc.com.github.alunos_rm550282_rm99748.model.EventoModel


@Database(entities = [EventoModel::class], version = 1)
abstract class EventoDataBase :RoomDatabase() {

    abstract fun itemDao(): EventoDao
}