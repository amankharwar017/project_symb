package com.parking.service;

import com.parking.entity.ParkingSlot;
import com.parking.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepository repository;

    public ParkingServiceImpl(ParkingSlotRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParkingSlot addSlot(ParkingSlot slot) {
        slot.setOccupied(false);
        return repository.save(slot);
    }

    @Override
    public List<ParkingSlot> getAllSlots() {
        return repository.findAll();   // No sorting here
    }

    @Override
    public ParkingSlot parkVehicle(boolean needsEV, boolean needsCover) {

        List<ParkingSlot> slots = repository.findAll();

        ParkingSlot bestSlot = null;

        for (ParkingSlot slot : slots) {

            if (slot.isOccupied()) {
                continue;
            }

            if (needsEV && !slot.isEvCharging()) {
                continue;
            }

            if (needsCover && !slot.isCovered()) {
                continue;
            }

            // If first valid slot
            if (bestSlot == null) {
                bestSlot = slot;
            }
            // If smaller slot number found
            else if (slot.getSlotNo() < bestSlot.getSlotNo()) {
                bestSlot = slot;
            }
        }

        if (bestSlot == null) {
            return null;   // No slot available
        }

        bestSlot.setOccupied(true);
        return repository.save(bestSlot);
    }

    @Override
    public ParkingSlot removeVehicle(Integer slotNo) {

        ParkingSlot slot = repository.findById(slotNo)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (!slot.isOccupied()) {
            throw new RuntimeException("Slot already free");
        }

        slot.setOccupied(false);
        return repository.save(slot);
    }
}
