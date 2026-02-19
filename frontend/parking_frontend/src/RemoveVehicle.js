import React, { useState } from "react";

function RemoveVehicle() {

  const [slotNo, setSlotNo] = useState("");

  async function handleRemove() {

    const response = await fetch(
      "http://localhost:8080/api/parking/remove/" + slotNo,
      {
        method: "POST"
      }
    );

    const result = await response.json();

    alert("Vehicle removed from Slot: " + result.slotNo);

    setSlotNo("");
  }

  return (
    <div>
      <h3>Remove Vehicle</h3>

      <div>
        Slot No:
        <input
          type="number"
          value={slotNo}
          onChange={function(e){ setSlotNo(e.target.value); }}
        />
      </div>

      <button onClick={handleRemove}>Remove</button>

    </div>
  );
}

export default RemoveVehicle;
