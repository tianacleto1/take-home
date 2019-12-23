package bookstore.fileprocessor.factory

import bookstore.fileprocessor.FileProcessor
import bookstore.fileprocessor.impl.CSVFileProcessor
import bookstore.fileprocessor.impl.JsonFileProcessor
import bookstore.fileprocessor.impl.TxtTabSeparatorFileProcessor
import java.util.*

object FileProcessorFactory {

    fun getFileProcessorImpl(path: String): FileProcessor {
        val fileExtension = Optional.ofNullable(path)
            .filter { f -> f.contains(".") }
            .map { f -> f.substring(path.lastIndexOf('.') + 1) }
            .orElse("")
            .toLowerCase()

        when (fileExtension) {
            "csv" -> return CSVFileProcessor()
            "txt" -> return TxtTabSeparatorFileProcessor()
            "json" -> return JsonFileProcessor()
            else -> throw RuntimeException("File Extension empty or not recognised!")
        }
    }
}
