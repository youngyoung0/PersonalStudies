package com.example.demo.Entity;



import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("userInfoV2")
public class UserV2 extends User {

	private String grade;
}