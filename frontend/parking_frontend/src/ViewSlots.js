import React, { useState, useEffect } from "react";

function ViewSlots() {
  const [slots, setSlots] = useState([]);

  useEffect(function () {
    async function load() {
      try {
        const response = await fetch("http://localhost:8080/api/parking/all");
        const data = await response.json();
        setSlots(data);
      } catch (e) {
        console.log(e);
      }
    }

    load();
  }, []);

  return (
    <div>
      <h3>All Slots</h3>

      {slots.map(function (s) {
        return (
          <div key={s.slotNo}>
            Slot: {s.slotNo} | Covered: {String(s.covered)} | EV: {String(s.evCharging)} | Occupied: {String(s.occupied)}
          </div>
        );
      })}
    </div>
  );
}

export default ViewSlots;
