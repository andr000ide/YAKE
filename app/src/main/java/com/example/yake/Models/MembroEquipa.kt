package com.example.yake.Models


import android.graphics.Bitmap

class MembroEquipa(nome: String, linkedin: String, mail: String, github: String,web: String, imagemUrl: Int, cargo: String ) {
    var nome: String = ""
    var linkedin: String = ""
    var mail: String = ""
    var github: String = ""
    var imagemUrl: Int
    var cargo: String = ""
    var web: String = ""

    init{
        this.imagemUrl = imagemUrl
        this.linkedin = linkedin
        this.mail = mail
        this.github = github
        this.nome = nome
        this.cargo = cargo
        this.web = web
    }
}