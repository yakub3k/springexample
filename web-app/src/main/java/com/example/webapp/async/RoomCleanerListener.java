package com.example.webapp.async;

import com.example.webapp.services.RoomService;
import com.example.webapp.web.model.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class RoomCleanerListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper;
    private final RoomService roomService;

    public RoomCleanerListener(ObjectMapper objectMapper, RoomService roomService) {
        this.objectMapper = objectMapper;
        this.roomService = roomService;
    }

    public void receiveMessage(String message) {
        try {
            AsyncPayload asyncPayload = objectMapper.readValue(message, AsyncPayload.class);

            switch (asyncPayload.getModel()) {
                case "room":
                    Room roomById = roomService.getRoomById(asyncPayload.getId());
                    log.info("Room cleaned: {}", roomById);
                    break;
                default: {
                    log.warn("Unknown mode: {}", asyncPayload.getModel());
                }
            }
        } catch (Exception e) {
            log.error("Error while cleaning room", e);
        }
    }
}
