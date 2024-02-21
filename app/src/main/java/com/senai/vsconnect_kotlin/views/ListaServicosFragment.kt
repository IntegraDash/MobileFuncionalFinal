package com.senai.vsconnect_kotlin.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.senai.vsconnect_kotlin.adapters.ListaAlertaAdapter
import com.senai.vsconnect_kotlin.apis.EndpointInterface
import com.senai.vsconnect_kotlin.apis.RetrofitConfig
import com.senai.vsconnect_kotlin.databinding.FragmentListaServicosBinding
import com.senai.vsconnect_kotlin.models.Alerta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaServicosFragment : Fragment() {

    private val clientRetrofit = RetrofitConfig.obterInstanciaRetrofit()

    private val endpoints = clientRetrofit.create(EndpointInterface::class.java)

    private var _binding: FragmentListaServicosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListaServicosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerServicos.layoutManager = LinearLayoutManager(requireContext())

        endpoints.listarAlertas().enqueue(object : Callback<List<Alerta>> {
            override fun onResponse(call: Call<List<Alerta>>, response:
            Response<List<Alerta>>
            ) {
                val servicos = response.body()

                binding.recyclerServicos.adapter = servicos?.let { ListaAlertaAdapter(requireContext(), it) }
            }

            override fun onFailure(call: Call<List<Alerta>>, t: Throwable) {
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