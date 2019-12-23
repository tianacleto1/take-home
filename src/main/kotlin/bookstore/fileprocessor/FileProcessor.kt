package bookstore.fileprocessor

import bookstore.model.Book

interface FileProcessor {

    fun processFile(path: String): Map<Long, Book>
}