-- spring boot 测试 user
create table if not exists gb_boot_user(
  id int auto_increment primary key comment'主键',
  user_id int comment 'user id',
  name VARCHAR(30) comment'课程id',
  head_url varchar(200) comment'图片链接', -- 后面还是用自己的存储比较安全
  dd timestamp default current_timestamp COMMENT'时间'
)engine=innodb default charset=utf8;
CREATE index idx_gb_boot_user_user_id on gb_boot_user(user_id);