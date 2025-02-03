package com.simplify.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private String categoryId;
    private String name;
    private String description;
    private String image;
    private String salonId;

}
