package com.simplify.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private String image;
    @Column(nullable = false)
    private String salonId;

}
