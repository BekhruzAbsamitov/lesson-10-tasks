package uz.pdp.lesson10tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10tasks.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
