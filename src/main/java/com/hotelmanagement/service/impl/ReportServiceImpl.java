package com.hotelmanagement.service.impl;

import com.hotelmanagement.config.ThreadPoolConfig;

import com.hotelmanagement.repository.BillDAO;
import com.hotelmanagement.repository.RoomDAO;

import com.hotelmanagement.repository.impl.BillDAOImpl;
import com.hotelmanagement.repository.impl.RoomDAOImpl;

import com.hotelmanagement.model.Bill;
import com.hotelmanagement.model.Room;

import com.hotelmanagement.model.enums.RoomStatus;

import com.hotelmanagement.service.ReportService;

import java.math.BigDecimal;

import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ReportServiceImpl implements ReportService {

    private final BillDAO billDAO =
            new BillDAOImpl();

    private final RoomDAO roomDAO =
            new RoomDAOImpl();

    private final ExecutorService executorService =
            ThreadPoolConfig.getExecutorService();

    @Override
    public Future<String> generateRevenueReport() {

        return executorService.submit(() -> {

            List<Bill> bills = billDAO.findAllBills();

            BigDecimal totalRevenue = bills.stream()
                    .map(Bill::getTotalAmount)
                    .reduce(
                            BigDecimal.ZERO,
                            BigDecimal::add
                    );

            return """
                
                ===================================
                     DAILY REVENUE REPORT
                ===================================
                
                Total Bills Generated : %d
                
                Total Revenue         : %s
                
                ===================================
                """
                    .formatted(
                            bills.size(),
                            totalRevenue
                    );
        });
    }

    @Override
    public Future<String> generateOccupancyReport() {

        return executorService.submit(() -> {

            List<Room> rooms = roomDAO.findAllRooms();

            long occupiedRooms = rooms.stream()
                    .filter(room ->
                            room.getStatus()
                                    == RoomStatus.OCCUPIED)
                    .count();

            long availableRooms = rooms.stream()
                    .filter(room ->
                            room.getStatus()
                                    == RoomStatus.AVAILABLE)
                    .count();

            return """
                
                ===================================
                      OCCUPANCY REPORT
                ===================================
                
                Total Rooms       : %d
                
                Occupied Rooms    : %d
                
                Available Rooms   : %d
                
                ===================================
                """
                    .formatted(
                            rooms.size(),
                            occupiedRooms,
                            availableRooms
                    );
        });
    }
}
