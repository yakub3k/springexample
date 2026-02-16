package com.example.webapp.services;

import com.example.webapp.data.entiry.RoomEntity;
import com.example.webapp.data.repository.RoomRepository;
import com.example.webapp.web.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll().stream()
                .map(this::getRoomFromEntity).toList();
    }

    public Room getRoomById(UUID id) {
        return roomRepository.findById(id).map(this::getRoomFromEntity).orElse(null);
    }

    public Room addRoom(Room room) {
        RoomEntity roomEntity = roomRepository.save(getRoomEntityFromRoom(room));
        return getRoomFromEntity(roomEntity);
    }

    public void deleteRoom(UUID id) {
        roomRepository.deleteById(id);
    }

    public Room updateRoom(Room room) {
        RoomEntity roomEntity = getRoomEntityFromRoom(room);
        roomEntity = roomRepository.save(roomEntity);
        return getRoomFromEntity(roomEntity);
    }

    private Room getRoomFromEntity(RoomEntity room) {
        return new Room(room.getRoomId(), room.getName(), room.getNumber(), room.getBedInfo());
    }

    private RoomEntity getRoomEntityFromRoom(Room room) {
        return new RoomEntity(room.getId(), room.getName(), room.getNumber(), room.getInfo());
    }
}
