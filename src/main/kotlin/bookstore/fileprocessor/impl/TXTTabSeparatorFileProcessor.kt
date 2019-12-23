package bookstore.fileprocessor.impl

import bookstore.fileprocessor.FileProcessor
import bookstore.model.Book
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

class TxtTabSeparatorFileProcessor : FileProcessor {

    override fun processFile(path: String): Map<Long, Book> {
        val books: MutableMap<Long, Book> = HashMap<Long, Book>()
        val SPECIAL_CHARS = "\t"

        try {
            Files.lines(Paths.get(path)).use { lines ->
                lines.skip(1)
                    .filter { l: String -> !l.isEmpty() }.sorted()
                    .forEach { l: String ->
                        val book: Book =
                            mapToBookObj(l.split(SPECIAL_CHARS).toTypedArray())
                        books[book.isbn] = book
                    }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }

        return books
    }

    private fun mapToBookObj(str: Array<String>): Book {
        val book = Book(str[2].toLong())
        book.title = str[0]
        book.description = str[1]
        book.author = str[3]
        book.genre = str[4]
        book.pages = str[5]
        book.ageRange = str[6]
        book.price = str[7]
        book.qty = str[8].toLong()
        return book
    }
}