package bookstore.fileprocessor.impl

import bookstore.fileprocessor.FileProcessor
import bookstore.model.Book
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.json.simple.parser.ParseException
import java.io.FileReader
import java.io.IOException
import java.util.stream.Collectors

/**
 * This class implements the FileProcessor interface
 * with json file type processing behavior
 */
class JsonFileProcessor : FileProcessor {

    override fun processFile(path: String): Map<Long, Book> {
        val jsonParser = JSONParser()
        var books: Map<Long, Book> = HashMap()

        try {
            FileReader(path).use { fileReader ->
                val lines = jsonParser.parse(fileReader) as JSONArray
                books = lines.stream()
                            .collect(Collectors.toMap({
                                    c: JSONObject -> c.get("ISBN")
                                }, mapToBookObj)
                            ) as Map<Long, Book>
                }
        } catch (e: IOException) {
            println(e.message);
        } catch (e: ParseException) {
            println(e.message);
        }
        return books
    }

    private val mapToBookObj = {
            json: JSONObject? ->
                val book = Book(json!!["ISBN"] as Long)
                book.title = json["Title"] as String
                book.description = json["Description"] as String
                book.author = json["Author"] as String
                book.genre = json["Genre"] as String
                book.pages = (json["Pages"] as Long?).toString()
                book.ageRange = json["Age Range"] as String
                book.price = json["Price"] as String
                book.qty = json["Qty."] as Long
                book
            }
}