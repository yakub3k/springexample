package com.example.webapp.web.api;

import com.example.webapp.services.RoomService;
import com.example.webapp.web.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomService roomService;

    public RoomApiController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable UUID id) {
        return roomService.getRoomById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable(name = "id") UUID id, @RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @DeleteMapping
    public void deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
    }


}
