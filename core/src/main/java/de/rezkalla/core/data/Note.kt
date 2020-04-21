package de.rezkalla.core.data

data class Note(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0,
    var wordCount: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other)
            return false
        other as Note

        if (title != other.title) return false

        if (content != other.content) return false

        if (creationTime != other.creationTime) return false

        if (id != other.id) return false

        if (wordCount != other.wordCount) return false

        return true
    }
}
