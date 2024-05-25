package com.capgemini.wsb.fitnesstracker.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@AllArgsConstructor
@Slf4j
public class ReportGenerator {

    // www.mailtrap.io
    // z tej strony wygenerować konfiguracje i wklepać ją do application.yml
    // JavaMailSender mailSender;

    // spring cron generator
    //@Scheduled(cron = "")
    @Scheduled(fixedDelay = 1000*60*60*24*7, initialDelay = 1000)
    public void LogToStdOut() {
        // mailSender.send();
        log.info("Report generated");
    }


}
