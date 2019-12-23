package bookstore.model

data class Book(
    var isbn: Long = 0,
    var title: String = "",
    var description: String = "",
    var author: String = "",
    var genre: String = "",
    var pages: String = "",
    var ageRange: String = "",
    var price: String = "",
    var qty: Long = 0
)
