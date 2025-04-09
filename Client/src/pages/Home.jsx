import { useState } from "react";
import SearchBar from "../components/SearchBar";
import bgImage from '../assets/library.jpg';
import NavBar from "../components/NavBar";
import "../css/Home.css";

function Home() {
  /*
  const backgroundStyle = {
    backgroundImage: `url(${bgImage})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    backgroundRepeat: "no-repeat",
    position: "fixed",       // Make sure it sticks to the screen
    top: 0,
    left: 0,
    height: "100vh",
    width: "100vw",
    zIndex: -1
  };
  style={backgroundStyle}
*/
  const [query, setQuery] = useState("");

  const handleSearch = (e) => {
    e.preventDefault();
    console.log("Searching for:", query);
  };

  return (
    <>
    <NavBar/>
    <h1 className="title">BookNest</h1>
      <div/>
      <div style={{ position: "relative", zIndex: 1, paddingTop: "100px" }}>
        <SearchBar
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          onSubmit={handleSearch}
        />
      </div>
    </>
  );
}

export default Home;