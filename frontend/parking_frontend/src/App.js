import React from "react";
import AddSlot from "./AddSlot";
import ParkVehicle from "./ParkVehicle";
import RemoveVehicle from "./RemoveVehicle";
import ViewSlots from "./ViewSlots";

function App() {
  return (
    <div>
      <h2>Parking App</h2>

      <AddSlot />
      <hr />

      <ParkVehicle />
      <hr />

      <RemoveVehicle />
      <hr />

      <ViewSlots />
    </div>
  );
}

export default App;
