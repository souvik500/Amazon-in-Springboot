package com.amazon.AmazonDataBase.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestSellerDto
{
    private String name;

    private int age;

    private Long mobileNo;

    private String email;

    private String  pan;
}
