CREATE TABLE IF NOT EXISTS employee(
   id VARCHAR(50) PRIMARY KEY,
   name VARCHAR(50),
   age INT
);
/*従業員の基本管理Id、名前、年齢を指定。またVARCHAR（５０）は５０文字までの指定。*/
/*ユーザーマスタ*/
 CREATE TABLE IF NOT EXISTS m_user
(
   user_id VARCHAR(50) PRIMARY KEY,
   password VARCHAR(100),
   user_name VARCHAR(50),
   birthday DATE,
   age INT,
   gender INT,
   department_id INT,
   role VARCHAR(50)
);
/*従業員の基本管理Id、名前、パスワード、誕生日、年齢、性別、を指定。またVARCHAR（５０）は５０文字までの指定。*/

/*部署マスタ*/ 
CREATE TABLE IF NOT EXISTS m_department(
department_id INT PRIMARY KEY,
department_name VARCHAR(50)
);

/*給与テーブル*/
 CREATE TABLE IF NOT EXISTS t_salary(
user_id VARCHAR(50),
year_month VARCHAR(50),
salary INT,
PRIMARY KEY(user_id,year_month)
);