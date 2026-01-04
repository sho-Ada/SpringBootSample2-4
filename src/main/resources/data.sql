INSERT INTO employee(id,name,age)/*INSERT INTO employee(id,name,age)employee テーブルに、id, name, age の3列へデータを入れる宣言。*/
VALUES('1','Tom',30);/*中身*/

/*ユーザーマスタ*/
INSERT INTO m_user(
user_id
,password
,user_name
,birthday
,age
,gender
,department_id
,role/*muserに入れるための箱*/
) VALUES
('system@co.jp','$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy','システム管理者','2000-01-01',21,1,1,'ROLE_ADMIN')
,('user@co.jp','$2a$10$rJyapIrvsHARwCNgporWLO6QIKXXezOpRrdb..7X0ea0VwZ5IldSy','ユーザー1','2000-01-01',21,2,2,'ROLE_GENERAL')
;/*初期値を設定している。passwordの部分はbcryptハッシュという。ROLE_ADMINでアドミン権限を付与している。）*/

/*部署マスタ*/
INSERT INTO m_department(
department_id
,department_name
) VALUES
(1,'システム管理部')
,(2,'営業部')
;/*上に同じで初期値を入力している1でシステム管理部、２で営業部。その人はどこの部首か検索したい時などに便利*/

/*給料テーブル*/
INSERT INTO t_salary(
user_id
,year_month
,salary
) VALUES
('user@co.jp','2020/11',280000)
,('user@co.jp','2020/12',290000)
,('user@co.jp','2021/01',300000)
;/*IDが一緒でいいのかと思ったけどこれはuser@co.jpの2020/~の給与という形で登録されているため問題ない。*/
