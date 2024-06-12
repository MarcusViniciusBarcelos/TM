package com.example.recyclerview_menucontexto

fun String.convertToMoneyWithSymbol() = "R$".plus(this.replace(".", ","))