package guilherme26bc.com.github.alunos_rm550282_rm99748.viewModel

import guilherme26bc.com.github.alunos_rm550282_rm99748.R
import guilherme26bc.com.github.alunos_rm550282_rm99748.model.EventoModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EventosAdapter(private val onItemRemoved: (EventoModel)->Unit)
    : RecyclerView.Adapter<EventosAdapter.ItemViewHolder>() {
    private var eventos= listOf<EventoModel>();

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewNomeLocal = view.findViewById<TextView>(R.id.textViewNomeLocal)
        val textViewTipoEvento = view.findViewById<TextView>(R.id.textViewTipoEvento)
        val textViewGrau = view.findViewById<TextView>(R.id.textViewGrau)
        val textViewData = view.findViewById<TextView>(R.id.textViewData)
        val textViewQuantidade = view.findViewById<TextView>(R.id.textViewQuantidade)

        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(evento: EventoModel){
            textViewNomeLocal.text = evento.local
            textViewTipoEvento.text = evento.evento
            textViewGrau.text = evento.grau
            textViewData.text = evento.data
            textViewQuantidade.text = evento.quantidade.toString()

            button.setOnClickListener({
                onItemRemoved(evento)
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.evento_layout, parent, false);
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    fun updateItems(newEventos :List<EventoModel>){
        eventos = newEventos
        notifyDataSetChanged()
    }
}