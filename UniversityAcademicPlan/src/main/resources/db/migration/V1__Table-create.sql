create table if not exists course (id int8 generated by default as identity, name varchar(255) not null, location_id int8 not null, primary key (id));
create table if not exists course_teacher (course_id int8 not null, teacher_id int8 not null);
create table if not exists groups (id int8 generated by default as identity, name varchar(255) not null, primary key (id));
create table if not exists groups_course (groups_id int8 not null, course_id int8 not null);
create table if not exists lecture (id int8  generated by default as identity, name varchar(255) not null, course_id int8 not null, primary key (id));
create table if not exists location (id int8 generated by default as identity, name varchar(255) not null, primary key (id));
create table if not exists schedule (id int8 generated by default as identity, day_of_week varchar(255) not null, start_time time not null, end_time time not null, course_id int8, primary key (id));
create table if not exists student (first_name varchar(255) not null, last_name varchar(255) not null, id int8 not null, groups_id int8, primary key (id));
create table if not exists student_course (student_id int8 not null, course_id int8 not null);
create table if not exists teacher (first_name varchar(255) not null, last_name varchar(255) not null, id int8 not null, primary key (id));
create table if not exists admin (first_name varchar(255) not null, last_name varchar(255) not null, id int8 not null, primary key (id));
create table if not exists users (id int8 generated by default as identity, nick_name varchar(255) not null, password varchar(255) not null, user_type varchar(255), primary key (id));
alter table if exists course add constraint FK_location foreign key (location_id) references location;
alter table if exists course_teacher add constraint FK_teacher foreign key (teacher_id) references teacher;
alter table if exists course_teacher add constraint FK_course foreign key (course_id) references course;
alter table if exists groups_course add constraint FK_course foreign key (course_id) references course;
alter table if exists groups_course add constraint FK_groups foreign key (groups_id) references groups;
alter table if exists lecture add constraint FK_course foreign key (course_id) references course;
alter table if exists schedule add constraint FK_course foreign key (course_id) references course;
--alter table if exists schedule add constraint FK_lecture foreign key (lecture_id) references lecture;
alter table if exists student add constraint FK_grops foreign key (groups_id) references groups;
alter table if exists student add constraint FK_users foreign key (id) references users;
alter table if exists student_course add constraint FK_course foreign key (course_id) references course;
alter table if exists student_course add constraint FK_student foreign key (student_id) references student;
alter table if exists teacher add constraint FK_users foreign key (id) references users;
alter table if exists admin add constraint FK_users foreign key (id) references users;