package com.Payment.MobiPayLite.controller;

import com.Payment.MobiPayLite.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashboardService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserDashboard(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getUserDashBoard(userId));
    }

    @GetMapping("/admin")
    public ResponseEntity<?> getAdminDashboard() {
        return ResponseEntity.ok(dashboardService.getAdminDashboard());
    }
}
