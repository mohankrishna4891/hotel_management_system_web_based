package com.hotelmanagement.service.impl;

import com.hotelmanagement.repository.RoomDAO;
import com.hotelmanagement.repository.impl.RoomDAOImpl;
import com.hotelmanagement.model.Room;
import com.hotelmanagement.service.RoomService;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public void addRoom(Room room) {

        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        if (room.getPrice() == null) {
            throw new IllegalArgumentException("Room price cannot be null");
        }

        roomDAO.saveRoom(room);
    }

    @Override
    public Optional<Room> getRoomById(Long roomId) {

        return roomDAO.findRoomById(roomId);
    }

    @Override
    public List<Room> getAllRooms() {

        return roomDAO.findAllRooms();
    }

    @Override
    public void updateRoom(Room room) {
        roomDAO.updateRoom(room);
    }

    @Override
    public void removeRoom(Long roomId) {

    }

    @Override
    public void deleteRoom(Long roomId) {
        roomDAO.deleteRoom(roomId);
    }
}
