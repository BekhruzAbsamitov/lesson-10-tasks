package uz.pdp.lesson10tasks.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson10tasks.entity.Hotel;
import uz.pdp.lesson10tasks.payload.HotelDto;
import uz.pdp.lesson10tasks.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        try {
            hotelRepository.deleteById(id);
        } catch (Exception e) {
           return "Error in deleting";
        }
        return "Deleted";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        final Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()) {
            final Hotel hotel = optionalHotel.get();
            hotel.setName(hotelDto.getName());
            hotelRepository.save(hotel);
        }
        return "Edited";
    }

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        try {
            hotelRepository.save(hotel);
        } catch (Exception e) {
            return "Error in saving";
        }
        return "Saved";
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        final Optional<Hotel> optionalHotel =
                hotelRepository.findById(id);
        return optionalHotel.orElse(null);
    }
}
