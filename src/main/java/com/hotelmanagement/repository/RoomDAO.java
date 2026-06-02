package com.hotelmanagement.repository;

import com.hotelmanagement.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomDAO {

    void saveRoom(Room room);

    Optional<Room> findRoomById(Long roomId);

    List<Room> findAllRooms();

    void updateRoom(Room room);

    void deleteRoom(Long roomId);
}
