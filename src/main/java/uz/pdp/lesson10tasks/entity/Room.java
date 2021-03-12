package uz.pdp.lesson10tasks.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer number;
    private Integer floor;
    private Double size;
    @ManyToOne
    private Hotel hotel;
}
