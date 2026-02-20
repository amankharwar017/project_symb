
package com.parking.repository;

import com.parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepo extends JpaRepository<ParkingSlot, Integer> {
}
