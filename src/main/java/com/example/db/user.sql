-- spring boot ���� user
create table if not exists gb_boot_user(
	id int auto_increment primary key comment'����',
	user_id int comment 'user id',
	name VARCHAR(30) comment'�γ�id',
	head_url varchar(200) comment'ͼƬ����', -- ���滹�����Լ��Ĵ洢�Ƚϰ�ȫ
	dd timestamp default current_timestamp COMMENT'ʱ��'
)engine=innodb default charset=utf8;
CREATE index idx_gb_boot_user_user_id on gb_boot_user(user_id);