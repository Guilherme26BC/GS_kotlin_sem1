package guilherme26bc.com.github.alunos_rm550282_rm99748.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
public class EventoModel (
    @PrimaryKey(autoGenerate = true)

    val id: Int = 0,
    val local: String,
    val evento:String,
    val grau:String,
    val data:String,
    val quantidade:Int,

    )