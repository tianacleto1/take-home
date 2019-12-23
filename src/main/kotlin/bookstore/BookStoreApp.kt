package bookstore

import bookstore.fileprocessor.FileProcessor
import bookstore.fileprocessor.factory.FileProcessorFactory
import bookstore.model.Book
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer

val PATH_SAVE_FILE = "/Users/tiagosilva/Downloads/take-home-master/Examples/processed.csv"

fun main(args: Array<String>) {

    if (args.size < 3) {
        System.err.println("Error: Please provide the fully qualified path to the \"3\" files separated by space as command line arguments!")
        System.exit(1)
    }

    val bookMap: MutableMap<Long, Book?> = HashMap<Long, Book?>()
    for (filePath in args) {
        if (!filePath.isEmpty()) {
            val fileProcessor: FileProcessor = FileProcessorFactory.getFileProcessorImpl(filePath)
            val lines: Map<Long, Book> = fileProcessor.processFile(filePath)

            lines.values
                .forEach(Consumer<Book> { b: Book ->
                    bookMap.computeIfPresent(b.isbn) { k: Long?, v: Book? ->
                        v!!.qty = (v.qty + b.qty)
                        v
                    }
                    bookMap.putIfAbsent(b.isbn, b)
                })
        }
    }
    writeToCSVFile(bookMap)
}

private fun writeToCSVFile(booksMap: Map<Long, Book?>) {
    try {
        Files.newBufferedWriter(Paths.get(PATH_SAVE_FILE)).use { bw ->
            bw.write("Title,Description,ISBN,Author,Genre,Pages,Age Range,Price,Qty.")
            bw.newLine()
            val booksList: ArrayList<Book?> = ArrayList<Book?>(booksMap.values)

            booksList.sortWith(
                Comparator.comparing(Book::genre)
                    .thenComparing(Book::author)
            )

            for (b in booksList) {
                bw.write(b.toString())
                bw.newLine()
            }
        }
    } catch (ex: IOException) {
        println(ex.message)
    }
}