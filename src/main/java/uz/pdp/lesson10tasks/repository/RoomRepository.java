package uz.pdp.lesson10tasks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson10tasks.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Page<Room> findAllByHotel_Id(Integer hotel_id, Pageable pageable);
}
