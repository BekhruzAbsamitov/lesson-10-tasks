package uz.pdp.lesson10tasks.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10tasks.entity.Hotel;
import uz.pdp.lesson10tasks.entity.Room;
import uz.pdp.lesson10tasks.payload.RoomDto;
import uz.pdp.lesson10tasks.repository.HotelRepository;
import uz.pdp.lesson10tasks.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    RoomRepository roomRepository;
    HotelRepository hotelRepository;

    public RoomController(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/byHotelId/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId,
                                        @RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return roomRepository.findAllByHotel_Id(hotelId, pageable);
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        final Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            final Room room = optionalRoom.get();
            room.setFloor(roomDto.getFloor());
            final Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (optionalHotel.isEmpty()) {
                return "Hotel is not found";
            }
            room.setHotel(optionalHotel.get());
            room.setNumber(roomDto.getNumber());
            room.setSize(room.getSize());
            roomRepository.save(room);
            return "Edited";
        }
        return "Error";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        try {
            roomRepository.deleteById(id);
        } catch (Exception e) {
            return "Error";
        }
        return "Deleted";
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        final Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());

        room.setHotel(optionalHotel.get());
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "Added";
    }
}
