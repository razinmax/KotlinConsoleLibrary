class Library {
    val books = mutableListOf<Book>()
    val readers = mutableListOf<Reader>()
    var nextBookId = 1
    var nextReaderId = 1

    fun addBook(title: String, author: String, genre: String){
        val newBook = Book(
            nextBookId++,
            title,
            author,
            genre,
            isBorrowed = false
        )
        books.add(newBook)
        println("New book ${newBook.title} by ${newBook.author} with ID: ${newBook.id} added")
    }

    fun addReader(name: String){
        val newReader = Reader(nextReaderId++,name)
        readers.add(newReader)
        println("New reader ${newReader.name} with ID ${newReader.id} added")
    }

    fun findBook(query: String){
        val foundBooks = books.filter {it.title.contains(query, ignoreCase = true)
                || it.author.contains(query, ignoreCase = true)
                || it.genre.contains(query, ignoreCase = true)}
        if (foundBooks.isNotEmpty()) {
            println("Following books were found:")
            foundBooks.forEach{
                val status = if (it.isBorrowed) "Borrowed" else "Available"
                println("ID: ${it.id}, Title: ${it.title}, Author: ${it.author}, Genre: ${it.genre}, Status: ${status}")
            }
        }
        else {
            println("No books found for this query: ${query}")
        }
    }

    fun borrowBook(bookID: Int, readerID: Int){
        val book = books.find { it.id == bookID }
        val reader = readers.find { it.id ==readerID }

        when {
            book == null && reader == null ->
                println("Book with ID ${bookID} and reader with ID ${readerID} not found")

            book == null ->
                println("Book with ID ${bookID} not found")

            reader == null ->
                println("Reader with ID ${readerID} not found")

            book.isBorrowed ->
                println("Book '${book.title}' is already borrowed")

            else -> {
                book.isBorrowed = true
                reader.borrowedBooks.add(book)
                println("Book '${book.title}' successfully borrowed by ${reader.name}")
            }
        }
    }

    fun returnBook(bookID: Int, readerID: Int){
        val book = books.find { it.id == bookID }
        val reader = readers.find { it.id ==readerID }

        when {
            book == null && reader == null ->
                println("Book with ID ${bookID} and reader with ID ${readerID} not found")

            book == null ->
                println("Book with ID ${bookID} not found")

            reader == null ->
                println("Reader with ID ${readerID} not found")

            !book.isBorrowed ->
                println("Book '${book.title}' is not currently borrowed")

            !reader.borrowedBooks.contains(book) ->
                println("Reader ${reader.name} has not borrowed this book")

            else -> {
                book.isBorrowed = false
                reader.borrowedBooks.remove(book)
                println("Book '${book.title}' successfully returned by ${reader.name}")
            }
        }
    }

    fun showAllBooks(){
        println("All books in library (total: ${books.count()})")
        if (books.isEmpty()) {
            println("No books in the library")
        } else {
            books.forEach{
                val status = if (it.isBorrowed) "Borrowed" else "Available"
                println("Title: ${it.title}, Author: ${it.author}, Genre: ${it.genre}, Status: ${status}")
            }
        }
    }
}