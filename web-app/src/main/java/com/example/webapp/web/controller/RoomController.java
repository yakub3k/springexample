package com.example.webapp.web.controller;

import com.example.webapp.data.entiry.RoomEntity;
import com.example.webapp.data.repository.RoomRepository;
import com.example.webapp.web.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public String getRoomsPage(Model model) {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        List<Room> rooms = roomEntities.stream()
                .map(r -> new Room(r.getRoomId(), r.getName(), r.getNumber(), r.getBedInfo()))
                .toList();
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
}
