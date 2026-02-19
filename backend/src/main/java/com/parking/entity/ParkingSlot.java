package com.parking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot {

    @Id
    @Column(name = "slot_no")
    private Integer slotNo;

    @Column(name = "is_covered", nullable = false)
    private boolean isCovered;

    @Column(name = "is_ev_charging", nullable = false)
    private boolean isEvCharging;

    @Column(name = "is_occupied", nullable = false)
    private boolean isOccupied;

    public ParkingSlot() {}

    public ParkingSlot(Integer slotNo, boolean isCovered, boolean isEvCharging, boolean isOccupied) {
        this.slotNo = slotNo;
        this.isCovered = isCovered;
        this.isEvCharging = isEvCharging;
        this.isOccupied = isOccupied;
    }

    public Integer getSlotNo() { 
    	return slotNo; 
    	}
    public void setSlotNo(Integer slotNo) {
    	this.slotNo = slotNo;
    	}

    public boolean isCovered(){
    	return isCovered;
    	}
    public void setCovered(boolean covered) {
    	isCovered = covered; 
    	}

    public boolean isEvCharging() { 
    	return isEvCharging; 
    	}
    public void setEvCharging(boolean evCharging) {
    	isEvCharging = evCharging; 
    	}

    public boolean isOccupied() {
    	return isOccupied;
    	}
    public void setOccupied(boolean occupied) {
    	isOccupied = occupied; 
    	}
}
