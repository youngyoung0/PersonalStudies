package com.example.demo.Entity;



import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"password","ssn"})
@NoArgsConstructor
//@JsonFilter("userInfo")
@ApiModel(description = "사용자 상세 정보를 위한 객체 도메인 객체 ")
public class User {

	private Integer user_id;
	@Size(min =2)
	@ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
	private String name;
	@Past // 미래 데이터 사용 불가 과거시간만 사용가능
	@ApiModelProperty(notes="사용자 등록일을 입력해 주세요.")
	private Date joinDate;
	
	@ApiModelProperty(notes="사용자 비밀번호를 입력해 주세요.")
	private String password;
	@ApiModelProperty(notes="사용자 주민번호를 입력해 주세요.")
	private String ssn;
}