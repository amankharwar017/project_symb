import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="homePage" style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Welcome to Smart Parking Lot System</h2>
      <Link 
        to="/assignment" 
        style={{
          display: "inline-block",
          padding: "12px 24px",
          backgroundColor: "#007bff",
          color: "white",
          textDecoration: "none",
          borderRadius: "6px",
          fontWeight: "bold",
          marginTop: "20px"
        }}
      >
        Open
      </Link>
    </div>
  );
}