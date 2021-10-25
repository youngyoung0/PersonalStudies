package com.example.demo.Entity;



import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class User {

	private Integer user_id;
	@Size(min =2)
	private String name;
	@Past // 미래 데이터 사용 불가 과거시간만 사용가능
	private Date joinDate;
}