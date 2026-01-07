package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data//Lombokのアノテーション
public class MUser {
	private String userId;//sqlに対応している。これはmuser.user_id
	private String password;//
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private Integer departmentId;
	private String role;
	private Department department;
	private List<Salary> salaryList;//これは給与の入れるデータが一ユーザーにつき複数データが入るため。
}
