package guilherme26bc.com.github.alunos_rm550282_rm99748.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import guilherme26bc.com.github.alunos_rm550282_rm99748.model.EventoModel

@Dao
interface EventoDao {

    @Query("SELECT * FROM EventoModel")
    fun getAll(): LiveData<List<EventoModel>>

    @Insert
    fun insert(item:EventoModel)

    @Delete
    fun delete(item:EventoModel)
}