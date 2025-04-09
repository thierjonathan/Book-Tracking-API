import BookCard from "../components/BookCard"
function BookSearch(){
    const book = {
        title: "The Hobbit",
        authors: ["J.R.R. Tolkien"],
        thumbnail: "https://covers.openlibrary.org/b/id/8231856-L.jpg"
      };
      const booksecond = {
        title: "Kafka on The Shore",
        authors: ["Haruki Murakami"],
        thumbnail: "http://books.google.com/books/content?id=L6AtuutQHpwC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
      };
    return <div>
        <BookCard book={book}/>
        <BookCard book={booksecond}/>
    </div>
}
export default BookSearch