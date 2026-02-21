import { useState } from "react";

const API = "http://localhost:8080/api/slots";

export default function AssignmentUI() {

  const [slots, setSlots] = useState([]);
  const [slotNo, setSlotNo] = useState("");

  const [covered, setCovered] = useState(false);
  const [evcharging, setEvcharging] = useState(false);
  const [needsEV, setNeedsEV] = useState(false);
  const [needsCover, setNeedsCover] = useState(false);
  const [removeSlotNo, setRemoveSlotNo] = useState("");
  const [output, setOutput] = useState("No output yet");
  const [showTable, setShowTable] = useState(false); 

  async function loadSlots() {
    try {
      const res = await fetch(API);
      const data = await res.json();
      setSlots(data);
      setShowTable(true); 
    } catch (e) {
      setOutput("Error: Unable to fetch slots");
    }
  }

  async function addSlot(e) {
    e.preventDefault();
    setOutput("");

    const body = {
      slotNo: Number(slotNo),
      covered: covered,
      evcharging: evcharging,
      occupied: false,
    };

    try {
      const res = await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });

      if (!res.ok) {
        const errMsg = await res.text();
        setOutput("Add Slot Failed: " + errMsg);
        return;
      }

      const data = await res.json();
      setOutput(`Slot Added Successfully! Slot No: ${data.slotNo}`);

      setSlotNo("");
      setCovered(false);
      setEvcharging(false);

      await loadSlots();
    } catch (e) {
      setOutput("Error: Add Slot request failed");
    }
  }

  async function parkVehicle() {
    setOutput("");
    const url = `${API}/park?needsEV=${needsEV}&needsCover=${needsCover}`;

    try {
      const res = await fetch(url, { method: "POST" });

      if (!res.ok) {
        const errMsg = await res.text();
        setOutput("Park Failed: " + errMsg);
        return;
      }

      const data = await res.json();
      setOutput(`Vehicle Parked Successfully in Slot ${data.slotNo}`);
      await loadSlots();
    } catch (e) {
      setOutput("Error: Park request failed");
    }
  }

  async function removeVehicle() {
    setOutput("");

    if (!removeSlotNo) {
      setOutput("Enter slot number to remove vehicle");
      return;
    }

    try {
      const res = await fetch(`${API}/remove/${removeSlotNo}`, {
        method: "POST",
      });

      if (!res.ok) {
        const errMsg = await res.text();
        setOutput("Remove Failed: " + errMsg);
        return;
      }

      const data = await res.json();
      setOutput(`Vehicle Removed Successfully from Slot ${data.slotNo}`);
      setRemoveSlotNo("");
      await loadSlots();
    } catch (e) {
      setOutput("Error: Remove request failed");
    }
  }

  return (
    <div className="container">
      {/* 1) Add Slot Form */}
      <div className="box">
        <h3>1) Add Parking Slot</h3>
        <form onSubmit={addSlot}>
          <div className="row">
            <input
              className="input"
              type="number"
              placeholder="Slot Number"
              value={slotNo}
              onChange={(e) => setSlotNo(e.target.value)}
              required
            />
            <label>
              <input
                type="checkbox"
                checked={covered}
                onChange={(e) => setCovered(e.target.checked)}
              /> Covered
            </label>
            <label>
              <input
                type="checkbox"
                checked={evcharging}
                onChange={(e) => setEvcharging(e.target.checked)}
              /> EV Charging
            </label>
            <button className="btn addBtn" type="submit">Add Slot</button>
          </div>
        </form>
      </div>

      {/* 2) Slot Listing */}
      <div className="box">
        <h3>2) Parking Slot List</h3>
        <button className="btn refreshBtn" onClick={loadSlots}>Refresh List</button>

        {showTable && (   
          <div className="tableWrap" style={{ marginTop: 12 }}>
            <table>
              <thead>
                <tr>
                  <th>Slot No</th>
                  <th>Covered</th>
                  <th>EV Charging</th>
                  <th>Occupied</th>
                </tr>
              </thead>
              <tbody>
                {slots.length === 0 ? (
                  <tr>
                    <td colSpan="4">No slots found</td>
                  </tr>
                ) : (
                  slots.map((s) => (
                    <tr key={s.slotNo}>
                      <td>{s.slotNo}</td>
                      <td>{String(s.covered)}</td>
                      <td>{String(s.evcharging)}</td>
                      <td>{String(s.occupied)}</td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* 3) Park / Remove */}
      <div className="box">
        <h3>3) Park / Remove Vehicle</h3>
        <div className="row" style={{ marginBottom: 10 }}>
          <label>
            <input
              type="checkbox"
              checked={needsEV}
              onChange={(e) => setNeedsEV(e.target.checked)}
            /> Needs EV Charging
          </label>
          <label>
            <input
              type="checkbox"
              checked={needsCover}
              onChange={(e) => setNeedsCover(e.target.checked)}
            /> Needs Covered Slot
          </label>
          <button className="btn parkBtn" onClick={parkVehicle}>Park Vehicle</button>
        </div>
        <div className="row">
          <input
            className="input"
            type="number"
            placeholder="Slot No (Remove Vehicle)"
            value={removeSlotNo}
            onChange={(e) => setRemoveSlotNo(e.target.value)}
          />
          <button className="btn removeBtn" onClick={removeVehicle}>Remove Vehicle</button>
        </div>
      </div>

      {/* 4) Output Panel */}
      <div className="outputBox">
        <p className="outputTitle">4) Output Display Panel</p>
        <div>{output}</div>
      </div>
    </div>
  );
}