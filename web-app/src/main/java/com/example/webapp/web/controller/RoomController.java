package com.example.webapp.web.controller;

import com.example.webapp.services.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rooms")
public class RoomController {



    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getRoomsPage(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        return "rooms";
    }

    /*
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
     */
}
