
package com.parking.controller;

import com.parking.entity.ParkingSlot;
import com.parking.exception.SimpleParkingException;
import com.parking.service.ParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
@CrossOrigin(origins = "http://localhost:3000")
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> addSlot(@RequestBody ParkingSlot slot) {
        try {
            return ResponseEntity.ok(service.addSlot(slot));
        } catch (SimpleParkingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ParkingSlot>> getAll() {
        return ResponseEntity.ok(service.getAllSlots());
    }

    @PostMapping("/park")
    public ResponseEntity<?> park(@RequestParam boolean needsEV, @RequestParam boolean needsCover) {
        try {
            return ResponseEntity.ok(service.parkVehicle(needsEV, needsCover));
        } catch (SimpleParkingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/remove/{slotNo}")
    public ResponseEntity<?> remove(@PathVariable int slotNo) {
        try {
            return ResponseEntity.ok(service.removeVehicle(slotNo));
        } catch (SimpleParkingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
