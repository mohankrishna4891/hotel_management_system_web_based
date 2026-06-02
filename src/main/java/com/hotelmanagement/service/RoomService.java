package com.hotelmanagement.service;

import com.hotelmanagement.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void addRoom(Room room);

    Optional<Room> getRoomById(Long roomId);

    List<Room> getAllRooms();

    void updateRoom(Room room);

    void removeRoom(Long roomId);

    void deleteRoom(Long roomId);
}
