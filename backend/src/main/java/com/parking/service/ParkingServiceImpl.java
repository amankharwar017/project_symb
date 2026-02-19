package com.parking.service;

import com.parking.entity.ParkingSlot;
import com.parking.exception.SlotAlreadyExistsException;
import com.parking.exception.SlotAlreadyFreeException;
import com.parking.exception.SlotNotFoundException;
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

        if (slot == null) {
            throw new IllegalArgumentException("Slot body is required");
        }

        if (slot.getSlotNo() == null || slot.getSlotNo() <= 0) {
            throw new IllegalArgumentException("slotNo must be a positive number");
        }

        // prevent duplicates
        if (repository.existsById(slot.getSlotNo())) {
            throw new SlotAlreadyExistsException("Slot already exists: " + slot.getSlotNo());
        }

        // always create as free
        slot.setOccupied(false);
        return repository.save(slot);
    }

    @Override
    public List<ParkingSlot> getAllSlots() {
        return repository.findAll();
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

            // pick nearest = smallest slotNo
            if (bestSlot == null || slot.getSlotNo() < bestSlot.getSlotNo()) {
                bestSlot = slot;
            }
        }

        if (bestSlot == null) {
            return null; // controller will return "No slot available"
        }

        bestSlot.setOccupied(true);
        return repository.save(bestSlot);
    }

    @Override
    public ParkingSlot removeVehicle(Integer slotNo) {

        if (slotNo == null || slotNo <= 0) {
            throw new IllegalArgumentException("slotNo must be a positive number");
        }

        ParkingSlot slot = repository.findById(slotNo)
                .orElseThrow(() -> new SlotNotFoundException("Slot not found: " + slotNo));

        if (!slot.isOccupied()) {
            throw new SlotAlreadyFreeException("Slot already free: " + slotNo);
        }

        slot.setOccupied(false);
        return repository.save(slot);
    }
}
