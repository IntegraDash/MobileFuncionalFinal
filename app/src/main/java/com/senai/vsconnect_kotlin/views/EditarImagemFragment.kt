package com.senai.vsconnect_kotlin.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.senai.vsconnect_kotlin.R
import com.senai.vsconnect_kotlin.adapters.ListaAlertaAdapter
import com.senai.vsconnect_kotlin.apis.EndpointInterface
import com.senai.vsconnect_kotlin.apis.RetrofitConfig
import com.senai.vsconnect_kotlin.databinding.FragmentEditarImagemBinding
import com.senai.vsconnect_kotlin.models.Alerta
import com.senai.vsconnect_kotlin.models.Erro
import com.senai.vsconnect_kotlin.models.Estrategia
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class EditarImagemFragment : Fragment() {

    private var _binding: FragmentEditarImagemBinding? = null

    private val bindings get() = _binding!!

    private val clientRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val endpoints = clientRetrofit.create(EndpointInterface::class.java)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditarImagemBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val sharedPreferences = requireContext().getSharedPreferences("idUsuario", Context.MODE_PRIVATE)

        val idUsuario = sharedPreferences.getString("idUsuario", "")

//        buscarUsuarioPorID(idUsuario.toString())

        endpoints.listarAlertas().enqueue(object : Callback<List<Alerta>> {
            override fun onResponse(call: Call<List<Alerta>>, response:
            Response<List<Alerta>>
            ) {
                val response = response.body()

                val alertas = root.findViewById<TextView>(R.id.txtAlertaValor)
                alertas.text = if (response?.size == null) "0" else response.size.toString()
            }

            override fun onFailure(call: Call<List<Alerta>>, t: Throwable) {
                println("Falha na requisicao: ${t.message}")
            }

        })

        endpoints.listarErros().enqueue(object : Callback<List<Erro>> {
            override fun onResponse(call: Call<List<Erro>>, response: Response<List<Erro>>) {
                val response = response.body()

                var errosCount = 0
                var errosCorrigidosCount = 0

                if (response != null) {
                    for ((index, model) in response.withIndex()) {

                        if (model.status_erro == "Ativo"){
                            errosCount += 1
                        }else{
                            errosCorrigidosCount += 1
                        }
                    }
                }

                val erros = root.findViewById<TextView>(R.id.txtErroValor)
                erros.text = errosCount.toString()

                val errosCorrigidos = root.findViewById<TextView>(R.id.txtErroCorrigidoValor)
                errosCorrigidos.text = errosCorrigidosCount.toString()
            }

            override fun onFailure(call: Call<List<Erro>>, t: Throwable) {
                println("Falha na requisicao: ${t.message}")
            }

        })

        endpoints.listarEstrategias().enqueue(object : Callback<List<Estrategia>> {
            override fun onResponse(call: Call<List<Estrategia>>, response:
            Response<List<Estrategia>>
            ) {
                val response = response.body()

                val estrategia = root.findViewById<TextView>(R.id.txtEstrategiaValor)
                estrategia.text = if (response?.size == null) "0" else response.size.toString()
            }

            override fun onFailure(call: Call<List<Estrategia>>, t: Throwable) {
                println("Falha na requisicao: ${t.message}")
            }

        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}