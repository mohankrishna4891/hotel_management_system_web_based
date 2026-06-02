package com.hotelmanagement.service;

import java.util.concurrent.Future;

public interface ReportService {

    Future<String> generateRevenueReport();

    Future<String> generateOccupancyReport();
}
