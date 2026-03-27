package com.voting.voting_system.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin("*")
public class OTPController {

    private Map<Long, String> otpStore = new HashMap<>();

    @PostMapping("/send-otp")
    public String sendOtp(@RequestParam Long userId) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(userId, otp);

        System.out.println("OTP: " + otp); // simulate sending
        return "OTP Sent!";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam Long userId,
                            @RequestParam String otp) {

        if (otp.equals(otpStore.get(userId))) {
            return "OTP Verified!";
        }

        throw new RuntimeException("Invalid OTP");
    }
}