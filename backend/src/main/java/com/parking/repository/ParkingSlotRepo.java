
package com.parking.repository;

import com.parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Integer> {
}
