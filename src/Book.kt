data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    var isBorrowed: Boolean = false
)