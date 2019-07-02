package com.example.yake.Models

class Media(organizacao: String, titulo: String, texto: String, link: String, img: Int) {
    var organizacao: String = ""
    var titulo: String = ""
    var texto: String = ""
    var link: String = ""
    var img: Int
    private var expanded: Boolean = false

    init {
        this.organizacao = organizacao
        this.titulo = titulo
        this.texto = texto
        this.link = link
        this.img = img
    }

    fun setExpanded(expanded: Boolean){
        this.expanded = expanded
    }

    fun isExpanded() : Boolean{
        return expanded
    }
}