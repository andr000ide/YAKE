package com.example.yake.Models


class sobreTopico(titulo: String, texto: String, url: String) {
    var titulo: String = ""
    var texto : String = ""
    var url: String = ""
    private var expanded: Boolean = false

    init{
        this.texto = texto
        this.titulo = titulo
        this.url = url
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }

    fun isExpanded() : Boolean{
        return expanded
    }
}