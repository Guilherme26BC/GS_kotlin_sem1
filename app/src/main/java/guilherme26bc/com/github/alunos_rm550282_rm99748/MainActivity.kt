package guilherme26bc.com.github.alunos_rm550282_rm99748

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import guilherme26bc.com.github.alunos_rm550282_rm99748.viewModel.EventosAdapter
import guilherme26bc.com.github.alunos_rm550282_rm99748.viewModel.EventosViewModel
import guilherme26bc.com.github.alunos_rm550282_rm99748.viewModel.EventosViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: EventosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Histórico de eventos e catastrofes climáticas"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val eventosAdapter = EventosAdapter{
                evento -> viewModel.removeEvento(evento)
        }
        recyclerView.adapter = eventosAdapter

        val button = findViewById<Button>(R.id.button)
        val editTextNomeLocal = findViewById<EditText>(R.id.editTextNomeLocal)
        val editTextTipoEvento = findViewById<EditText>(R.id.editTextTipoEvento)
        val editTextImpacto = findViewById<EditText>(R.id.editTextImpacto)
        val editTextDataEvento = findViewById<EditText>(R.id.editTextDataEvento)
        val editTextPessoasAfetadas = findViewById<EditText>(R.id.editTextPessoasAfetadas)

        button.setOnClickListener({
            if(editTextNomeLocal.text.isEmpty()){
                editTextNomeLocal.error = "Preencha um valor"
                return@setOnClickListener
            }
            if(editTextTipoEvento.text.isEmpty()){
                editTextTipoEvento.error = "Preencha um valor"
                return@setOnClickListener
            }
            if(editTextImpacto.text.isEmpty()){
                editTextImpacto.error = "Preencha um valor"
                return@setOnClickListener
            }
            if(editTextDataEvento.text.isEmpty()){
                editTextDataEvento.error = "Preencha um valor"
                return@setOnClickListener
            }
            if(editTextPessoasAfetadas.text.isEmpty()){
                editTextPessoasAfetadas.error = "Preencha um valor"
                return@setOnClickListener
            }
            if(Integer.parseInt(editTextPessoasAfetadas.text.toString())<0){
                editTextPessoasAfetadas.error = "Preencha um valor valido (>0)"
                return@setOnClickListener
            }

            viewModel.addEvento(
                editTextNomeLocal.text.toString(),
                editTextTipoEvento.text.toString(),
                editTextImpacto.text.toString(),
                editTextDataEvento.text.toString(),
                Integer.parseInt(editTextPessoasAfetadas.text.toString()))

            editTextNomeLocal.text.clear()
            editTextImpacto.text.clear()
            editTextDataEvento.text.clear()
            editTextTipoEvento.text.clear()
            editTextPessoasAfetadas.text.clear()
        })

        val viewModelFactory = EventosViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventosViewModel::class.java)

        viewModel.eventoLiveData.observe(this){
                eventos -> eventosAdapter.updateItems(eventos)
        }
    }
}