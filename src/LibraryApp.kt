class LibraryApp {
    private val library = Library()

    fun start() {
        println("WELCOME TO LIBRARY MANAGEMENT SYSTEM")

        initializeTestData()

        while (true) {
            showMenu()
            print("Select an action (1-7): ")

            when (readLine()?.toIntOrNull()) {
                1 -> addBookMenu()
                2 -> addReaderMenu()
                3 -> findBookMenu()
                4 -> borrowBookMenu()
                5 -> returnBookMenu()
                6 -> library.showAllBooks()
                7 -> {
                    println("Thank you for using the Library Management System!")
                    break
                }
                else -> println("Invalid choice. Please select from 1 to 7.")
            }

            println("\nPress Enter to continue...")
            readLine()
        }
    }

    private fun showMenu() {
        println("MAIN MENU")
        println("1. Add book")
        println("2. Add reader")
        println("3. Find book")
        println("4. Borrow book")
        println("5. Return book")
        println("6. Show all books")
        println("7. Exit")
    }

    private fun addBookMenu() {
        println("--- ADDING BOOK ---")
        print("Enter book title: ")
        val title = readLine()?.trim() ?: return

        if (title.isEmpty()) {
            println("Title cannot be empty!")
            return
        }

        print("Enter author: ")
        val author = readLine()?.trim() ?: return

        if (author.isEmpty()) {
            println("Author cannot be empty!")
            return
        }

        print("Enter genre: ")
        val genre = readLine()?.trim() ?: return

        if (genre.isEmpty()) {
            println("Genre cannot be empty!")
            return
        }

        library.addBook(title, author, genre)
    }

    private fun addReaderMenu() {
        println("--- ADDING READER ---")
        print("Enter reader name: ")
        val name = readLine()?.trim() ?: return

        if (name.isEmpty()) {
            println("Name cannot be empty!")
            return
        }

        library.addReader(name)
    }

    private fun findBookMenu() {
        println("\n--- BOOK SEARCH ---")
        print("Enter search query (title, author or genre): ")
        val query = readLine()?.trim() ?: return

        if (query.isEmpty()) {
            println("Search query cannot be empty!")
            return
        }

        library.findBook(query)
    }

    private fun borrowBookMenu() {
        println("--- BORROWING BOOK ---")

        print("Enter book ID: ")
        val bookId = readLine()?.toIntOrNull()

        if (bookId == null) {
            println("Invalid book ID!")
            return
        }

        print("Enter reader ID: ")
        val readerId = readLine()?.toIntOrNull()

        if (readerId == null) {
            println("Invalid reader ID!")
            return
        }

        library.borrowBook(bookId, readerId)
    }

    private fun returnBookMenu() {
        println("\n--- RETURNING BOOK ---")

        print("Enter book ID: ")
        val bookId = readLine()?.toIntOrNull()

        if (bookId == null) {
            println("Invalid book ID!")
            return
        }

        print("Enter reader ID: ")
        val readerId = readLine()?.toIntOrNull()

        if (readerId == null) {
            println("Invalid reader ID!")
            return
        }

        library.returnBook(bookId, readerId)
    }

    private fun initializeTestData() {
        // Add some books for demonstration
        library.addBook("War and Peace", "Leo Tolstoy", "Classic")
        library.addBook("1984", "George Orwell", "Dystopian Fiction")
        library.addBook("The Master and Margarita", "Mikhail Bulgakov", "Fantasy")

        // Add readers
        library.addReader("John Smith")
        library.addReader("Anna Johnson")

        println("Test data loaded!")
    }
}