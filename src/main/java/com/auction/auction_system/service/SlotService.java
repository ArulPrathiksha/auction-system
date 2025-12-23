package com.auction.auction_system.service;

import com.auction.auction_system.entity.TimeSlot;
import com.auction.auction_system.repository.TimeSlotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SlotService {
    private final TimeSlotRepository repo;

    public SlotService(TimeSlotRepository repo) {
        this.repo = repo;
    }

    // Get available slots that are not booked and have a start time in the future
    public List<TimeSlot> getAvailableSlots() {
        return repo.findByBookedFalseAndStartTimeAfter(LocalDateTime.now());
    }

    @Transactional
    public TimeSlot createSlot(TimeSlot slot) {
        // Check for overlapping slots before saving the new one
        if (isOverlapping(slot)) {
            throw new IllegalArgumentException("Time slot overlaps with an existing slot");
        }

        return repo.save(slot);
    }

    // Method to check if the new slot overlaps with any existing ones
    private boolean isOverlapping(TimeSlot newSlot) {
        LocalDateTime newStartTime = newSlot.getStartTime();
        LocalDateTime newEndTime = newSlot.getEndTime();

        // Fetch all existing slots that are not booked
        List<TimeSlot> existingSlots = repo.findByBookedFalseAndStartTimeAfter(LocalDateTime.now());

        for (TimeSlot existingSlot : existingSlots) {
            // Check for overlap: if the new slot's time range intersects with any existing slot's time range
            if (newStartTime.isBefore(existingSlot.getEndTime()) && newEndTime.isAfter(existingSlot.getStartTime())) {
                return true;  // Overlap found
            }
        }
        return false;  // No overlap
    }

    public TimeSlot findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
