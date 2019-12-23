package bookstore.fileprocessor.impl

import bookstore.fileprocessor.FileProcessor
import bookstore.model.Book
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.HashMap

class CSVFileProcessor : FileProcessor {

    override fun processFile(path: String): Map<Long, Book> {
        val books = HashMap<Long, Book>()

        try {
            Files.lines(Paths.get(path)).use { lines ->

                lines.skip(1)
                    .filter { l -> !l.isEmpty() }.sorted()
                    .forEach { l ->
                        val book =
                            mapToBookObj(l.split(SPECIAL_CHARS.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())

                        books.put(book.isbn, book)
                    }
            }
        } catch (e: IOException) {
            System.err.println(e.message)
        }

        return books
    }

    private fun mapToBookObj(str: Array<String>): Book {
        val book = Book(str[0].toLong(),
                        str[1],
                        str[2],
                        str[3],
                        str[4],
                        str[5],
                        str[6],
                        str[7],
                        str[8].toLong()
        )

        return book
    }

    companion object {

        private val SPECIAL_CHARS = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"
    }
}

