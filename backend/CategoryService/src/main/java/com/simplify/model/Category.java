package com.simplify.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private String image;
    @Column(nullable = false)
    private String salonId;

}
