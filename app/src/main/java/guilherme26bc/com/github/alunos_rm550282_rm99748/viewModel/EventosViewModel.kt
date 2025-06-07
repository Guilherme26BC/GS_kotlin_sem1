package guilherme26bc.com.github.alunos_rm550282_rm99748.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import androidx.room.Room
import guilherme26bc.com.github.alunos_rm550282_rm99748.data.EventoDao
import guilherme26bc.com.github.alunos_rm550282_rm99748.data.EventoDataBase
import guilherme26bc.com.github.alunos_rm550282_rm99748.model.EventoModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EventosViewModel(application: Application): AndroidViewModel(application) {

    private val eventoDao: EventoDao
    val eventoLiveData : LiveData<List<EventoModel>>

    init{
        val database = Room.databaseBuilder(
            getApplication(),
            EventoDataBase::class.java,
            "eventos_database"
        ).build()
        eventoDao = database.itemDao()
        eventoLiveData = eventoDao.getAll()
    }
    fun addEvento(local: String, evento:String, grau:String, data:String, quantidade:Int){

        viewModelScope.launch(Dispatchers.IO) {
            val newItem = EventoModel(
                local = local,
                evento = evento,
                grau = grau,
                data = data,
                quantidade = quantidade)
            eventoDao.insert(newItem)

        }
    }

    fun removeEvento(item: EventoModel){
        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(item)
        }
    }
}