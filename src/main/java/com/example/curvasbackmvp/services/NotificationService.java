package com.example.curvasbackmvp.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Scheduled(fixedRate = 12)
    public void notification() {
        System.out.println("teste");
    }
}
