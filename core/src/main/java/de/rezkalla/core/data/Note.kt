package de.rezkalla.core.data

import de.rezkalla.core.getWordsCount

data class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0,
    var wordCount: Int = title.getWordsCount() + content.getWordsCount()
)