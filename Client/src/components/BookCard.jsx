import "../css/BookCard.css"

function BookCard({ book }) {
    function onFavoriteClick() {
      alert('Favorited!');
    }
  
    return (
      <div className="book-card">
        <div className="book-cover">
          <img src={book.thumbnail || 'https://via.placeholder.com/128x192?text=No+Image'} alt={book.title} />
          <div className="book-overlay">
            <button className="favorite-btn" onClick={onFavoriteClick}>♥</button>
          </div>
        </div>
        <div className="book-info">
          <h3>{book.title}</h3>
          <p>{book.authors?.join(', ') || 'Unknown Author'}</p>
        </div>
      </div>
    );
  }
  
  export default BookCard;