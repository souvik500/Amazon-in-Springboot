package com.amazon.AmazonDataBase.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class CustomerRequestDto {

    private String name;

    private int age;

    private String mobileNo;

    private String email;

}
