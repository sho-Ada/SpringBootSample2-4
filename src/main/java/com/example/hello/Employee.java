package com.example.hello;

import lombok.Data;

@Data//Lombokが用意しているアノテーション（getter,setter,toString,hashCode,equals）
public class Employee {
private String employeeId;
private String employeeName;
private int employeeAge;
}
