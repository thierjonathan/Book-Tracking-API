import React from 'react';
import '../css/SearchBar.css';

function SearchBar({ value, onChange, onSubmit }) {
  return (
    <form onSubmit={onSubmit} className="search-container">
      <input
        className="search-input"
        type="text"
        placeholder="Search Books..."
        value={value}
        onChange={onChange}
      />
    </form>
  );
}

export default SearchBar;