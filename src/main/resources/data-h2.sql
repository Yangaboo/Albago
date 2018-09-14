insert into posts (title, content, author, pwd, cat_id, favor, hate, created_date) values ('a', 'b', 'c', 'd', 1, 1, 1, now())
insert into posts (title, content, author, pwd, cat_id, favor, hate, created_date) values ('d', 'c', 'b', 'a', 1, 1, 1, now())
insert into comments (content, author, pwd, favor, hate, post_id, created_date) values ('a', 'b', 'c', 10, 1, 1, now())
insert into comments (content, author, pwd, favor, hate, post_id, created_date) values ('c', 'b', 'a', 1, 10, 1, now())