package com.senai.vsconnect_kotlin.models

import java.util.UUID

class Alerta (
    val id_erro : String,
    val nivel_criticidade : String,
    val data_alerta: String,
    val status_alerta: String,
    val descricao_alerta: String,
    val erro : Erro
)