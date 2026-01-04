package com.example.domain.user.model;

import lombok.Data;

@Data
public class Salary {//給与テーブルを表すモデルクラス、給与情報1件分の入れ物。
	private String userId;
	private String yearMonth;
	private Integer salary;
}
