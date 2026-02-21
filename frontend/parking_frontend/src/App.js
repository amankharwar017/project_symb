import { BrowserRouter, Routes, Route } from "react-router-dom";
import AssignmentUI from "./pages/AssignmentUI";
import Home from "./pages/Home";

export default function App() {
  return (

  <BrowserRouter>
      <Routes>
       
        <Route path="/" element={<Home />} />
       
        <Route path="/assignment" element={<AssignmentUI />} />
      </Routes>
  </BrowserRouter>
  );
}