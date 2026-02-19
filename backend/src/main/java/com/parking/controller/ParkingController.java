package com.parking.controller;

import com.parking.entity.ParkingSlot;
import com.parking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    // 1️⃣ Add Slot
    @PostMapping("/add")
    public ResponseEntity<ParkingSlot> addSlot(@RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(service.addSlot(slot));
    }

    // 2️⃣ View All Slots
    @GetMapping("/all")
    public ResponseEntity<List<ParkingSlot>> getAllSlots() {
        return ResponseEntity.ok(service.getAllSlots());
    }

    // 3️⃣ Park Vehicle
    @PostMapping("/park")
    public ResponseEntity<?> parkVehicle(
            @RequestParam boolean needsEV,
            @RequestParam boolean needsCover) {

        ParkingSlot slot = service.parkVehicle(needsEV, needsCover);

        if (slot == null) {
            return ResponseEntity.badRequest().body("No slot available");
        }

        return ResponseEntity.ok(slot);
    }

    // 4️⃣ Remove Vehicle
    @PostMapping("/remove/{slotNo}")
    public ResponseEntity<?> removeVehicle(@PathVariable Integer slotNo) {

        return ResponseEntity.ok(service.removeVehicle(slotNo));
    }
}
