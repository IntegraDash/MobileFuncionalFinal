package com.senai.vsconnect_kotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senai.vsconnect_kotlin.R
import com.senai.vsconnect_kotlin.models.Alerta
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ListaAlertaAdapter(
    private val context: Context,
    private val listaAlerta: List<Alerta>
) : RecyclerView.Adapter<ListaAlertaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Essa funcao e responsavel por chamar e atribuir valores para as views do item da Recyclerview
        fun VincularDadosNoItem(alerta: Alerta) {

            val txtErro = itemView.findViewById<TextView>(R.id.codigoErro)
            txtErro.text = alerta.erro.nomeerro

            val txtDescricao = itemView.findViewById<TextView>(R.id.descricaoAlerta)
            txtDescricao.text = alerta.descricao_alerta

            val txtData = itemView.findViewById<TextView>(R.id.dataAlerta)
            val localDateTime: LocalDateTime = LocalDateTime.parse( "${alerta.data_alerta}T00:00:00")
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val output: String = formatter.format(localDateTime)
            txtData.text = output

            val txtStatus = itemView.findViewById<TextView>( R.id.statusAlerta)
            txtStatus.text = alerta.status_alerta

            val txtCriticidade = itemView.findViewById<TextView>(R.id.criticidadeAlerta)
            txtCriticidade.text = alerta.nivel_criticidade

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaAlertaAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context);

        val view = inflater.inflate(R.layout.fragment_alerta, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListaAlertaAdapter.ViewHolder, position: Int) {
        val itemServico = listaAlerta[position]

        holder.VincularDadosNoItem(itemServico)
    }

    override fun getItemCount(): Int {
        return listaAlerta.size
    }
}