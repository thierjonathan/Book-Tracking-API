import { useState } from 'react'
import { Route, Routes } from 'react-router-dom'
import Home from "./pages/Home"
import BookSearch from './pages/BookSearch'
import './App.css'
import bgImage from './assets/library.jpg'; // assuming it's inside /src/assets



function App() {
  const backgroundStyle = {
    backgroundImage: `url(${bgImage})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    position: "fixed",
    top: 0,
    left: 0,
    width: "100vw",
    height: "100vh",
    zIndex: -1
  };

  return (
    <div>
      {/* Background image layer */}
      <div style={backgroundStyle}></div>

      {/* Foreground content */}
      <main style={{ position: 'relative', zIndex: 1, minHeight: '100vh' }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/search" element={<BookSearch />} />
        </Routes>
      </main>
    </div>
  );
}

export default App
