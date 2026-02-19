import React, { useState } from "react";

function AddSlot() {

  const [slotNo, setSlotNo] = useState("");
  const [covered, setCovered] = useState(false);
  const [evCharging, setEvCharging] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();

    const data = {
      slotNo: Number(slotNo),
      covered: covered,
      evCharging: evCharging,
      occupied: false
    };

    const response = await fetch(
      "http://localhost:8080/api/parking/add",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
      }
    );

    const result = await response.json();

    alert("Slot Added: " + result.slotNo);

    setSlotNo("");
    setCovered(false);
    setEvCharging(false);
  }

  return (
    <div>
      <h3>Add Slot</h3>

      <form onSubmit={handleSubmit}>

        <div>
          Slot No:
          <input
            type="number"
            value={slotNo}
            onChange={function(e){ setSlotNo(e.target.value); }}
          />
        </div>

        <div>
          <label>
            <input
              type="checkbox"
              checked={covered}
              onChange={function(e){ setCovered(e.target.checked); }}
            />
            Covered
          </label>
        </div>

        <div>
          <label>
            <input
              type="checkbox"
              checked={evCharging}
              onChange={function(e){ setEvCharging(e.target.checked); }}
            />
            EV Charging
          </label>
        </div>

        <button type="submit">Add</button>

      </form>
    </div>
  );
}

export default AddSlot;
