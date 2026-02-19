
package com.parking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ParkingSlot {

    @Id
    private Integer slotNo;

    private boolean covered;
    private boolean evcharging;
    private boolean occupied;

    public ParkingSlot() {}

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public boolean isEvcharging() {
        return evcharging;
    }

    public void setEvcharging(boolean evcharging) {
        this.evcharging = evcharging;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
