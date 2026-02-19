// src/main/java/com/parking/service/ParkingServiceImpl.java
package com.parking.service;

import com.parking.entity.ParkingSlot;
import com.parking.exception.SimpleParkingException;
import com.parking.repository.ParkingSlotRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepo repo;

    public ParkingServiceImpl(ParkingSlotRepo repo) {
        this.repo = repo;
    }

    @Override
    public ParkingSlot addSlot(ParkingSlot slot) {
        if (slot.getSlotNo() == null) {
            throw new SimpleParkingException("slotNo is required");
        }

        if (repo.existsById(slot.getSlotNo())) {
            throw new SimpleParkingException("Slot already exists: " + slot.getSlotNo());
        }

        // occupied default false
        return repo.save(slot);
    }

    @Override
    public List<ParkingSlot> getAllSlots() {
        List<ParkingSlot> list = repo.findAll();

        // sort by slotNo (nearest = small slotNo)
        Collections.sort(list, new Comparator<ParkingSlot>() {
            @Override
            public int compare(ParkingSlot a, ParkingSlot b) {
                return a.getSlotNo().compareTo(b.getSlotNo());
            }
        });

        return list;
    }

    @Override
    public ParkingSlot parkVehicle(boolean needsEV, boolean needsCover) {
        List<ParkingSlot> list = repo.findAll();

        ParkingSlot best = null;

        for (int i = 0; i < list.size(); i++) {
            ParkingSlot s = list.get(i);

            if (s.isOccupied()) continue;
            if (needsEV && !s.isEvcharging()) continue;
            if (needsCover && !s.isCovered()) continue;

            if (best == null || s.getSlotNo() < best.getSlotNo()) {
                best = s;
            }
        }

        if (best == null) {
            throw new SimpleParkingException("No slot available");
        }

        best.setOccupied(true);
        return repo.save(best);
    }

    @Override
    public ParkingSlot removeVehicle(int slotNo) {
        Optional<ParkingSlot> opt = repo.findById(slotNo);

        if (opt.isEmpty()) {
            throw new SimpleParkingException("Slot not found: " + slotNo);
        }

        ParkingSlot slot = opt.get();

        if (!slot.isOccupied()) {
            throw new SimpleParkingException("Slot is already free: " + slotNo);
        }

        slot.setOccupied(false);
        return repo.save(slot);
    }
}
