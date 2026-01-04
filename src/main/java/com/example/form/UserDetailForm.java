package com.example.form;

import java.util.Date;
import java.util.List;

import com.example.domain.user.model.Department;
import com.example.domain.user.model.Salary;

import lombok.Data;

@Data
public class UserDetailForm {//画面表示入力に合わせて使う箱
private String userId;
private String password;
private String userName;
private Date birthday;
private Integer age;
private Integer gender;
private Department department;
private List<Salary> salaryList;
}
