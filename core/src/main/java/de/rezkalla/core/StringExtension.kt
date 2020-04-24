package de.rezkalla.core

fun String.getWordsCount() =
    this.split(" ", "\n").filter {
        it.contains(Regex(".*[a-zA-Z].*"))
    }.count()