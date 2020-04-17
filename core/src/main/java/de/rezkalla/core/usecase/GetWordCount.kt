package de.rezkalla.core.usecase

import de.rezkalla.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note) = wordCount(note.content) + wordCount(note.title)

    private fun wordCount(word: String) =
        word.split(" ", "\n").filter {
            it.contains(Regex(".*[a-zA-Z].*"))
        }.count()
}