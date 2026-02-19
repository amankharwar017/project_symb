import React, { useState } from "react";

function ParkVehicle() {

  const [needsEV, setNeedsEV] = useState(false);
  const [needsCover, setNeedsCover] = useState(false);

  async function handlePark() {

    const response = await fetch(
      "http://localhost:8080/api/parking/park?needsEV=" 
      + needsEV + 
      "&needsCover=" + needsCover,
      {
        method: "POST"
      }
    );

    const result = await response.json();

    alert("Vehicle parked in Slot: " + result.slotNo);
  }

  return (
    <div>
      <h3>Park Vehicle</h3>

      <div>
        <label>
          <input
            type="checkbox"
            checked={needsEV}
            onChange={function(e){ setNeedsEV(e.target.checked); }}
          />
          Needs EV Charging
        </label>
      </div>

      <div>
        <label>
          <input
            type="checkbox"
            checked={needsCover}
            onChange={function(e){ setNeedsCover(e.target.checked); }}
          />
          Needs Covered
        </label>
      </div>

      <button onClick={handlePark}>Park</button>

    </div>
  );
}

export default ParkVehicle;
