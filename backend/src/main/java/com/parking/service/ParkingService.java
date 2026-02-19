package com.parking.service;

import com.parking.entity.ParkingSlot;
import java.util.List;

public interface ParkingService {

    ParkingSlot addSlot(ParkingSlot slot);

    List<ParkingSlot> getAllSlots();

    ParkingSlot parkVehicle(boolean needsEV, boolean needsCover);

    ParkingSlot removeVehicle(Integer slotNo);
}
