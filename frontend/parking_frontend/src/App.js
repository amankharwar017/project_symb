import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import AssignmentUI from "./pages/AssignmentUI";

export default function App() {
  return (
    <BrowserRouter>
      <div className="topBar">
        <h2 className="title">Smart Parking Lot System</h2>

        {/* Router DOM used  */}
        <div className="nav">
          <Link to="/">Assignment UI</Link>
        </div>
      </div>

      <Routes>
        <Route path="/" element={<AssignmentUI />} />
      </Routes>
    </BrowserRouter>
  );
}
