class Reader (
    val id: Int,
    val name: String,
    val borrowedBooks: MutableList<Book> = mutableListOf()
)