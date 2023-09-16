INSERT INTO users (nick_name, password, user_tipe) VALUES ('user1', 'password', 'TEACHER');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user2', 'password', 'TEACHER');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user3', 'password', 'TEACHER');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user4', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user5', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user6', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user7', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user8', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user9', 'password', 'STUDENT');
INSERT INTO users (nick_name, password, user_tipe) VALUES ('user10', 'password', 'STUDENT');
----------------------------------------------------------------------------------
INSERT INTO groups (name) VALUES ('gr1');
INSERT INTO groups (name) VALUES ('gr2');
INSERT INTO groups (name) VALUES ('gr3');
INSERT INTO groups (name) VALUES ('gr4');
--------------------------------------------------------
INSERT INTO location (name) VALUES ('auditory1');
INSERT INTO location (name) VALUES ('auditory2');
INSERT INTO location (name) VALUES ('auditory3');
INSERT INTO location (name) VALUES ('auditory4');
--------------------------------------------------------
INSERT INTO course (name, location_id) VALUES ('c1', 1);
INSERT INTO course (name, location_id) VALUES ('c2', 2);
INSERT INTO course (name, location_id) VALUES ('c3', 3);
INSERT INTO course (name, location_id) VALUES ('c4', 4);
INSERT INTO course (name, location_id) VALUES ('c5', 1);
INSERT INTO course (name, location_id) VALUES ('c6', 2);
INSERT INTO course (name, location_id) VALUES ('c7', 3);
-----------------------------------------------------------------
INSERT INTO lecture (name, course_id) VALUES ('c1-l1', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l2', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l3', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l4', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l5', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l6', 1);
INSERT INTO lecture (name, course_id) VALUES ('c1-l7', 1);

INSERT INTO lecture (name, course_id) VALUES ('c2-l1', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l2', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l3', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l4', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l5', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l6', 2);
INSERT INTO lecture (name, course_id) VALUES ('c2-l7', 2);

INSERT INTO lecture (name, course_id) VALUES ('c3-l1', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l2', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l3', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l4', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l5', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l6', 3);
INSERT INTO lecture (name, course_id) VALUES ('c3-l7', 3);

INSERT INTO lecture (name, course_id) VALUES ('c4-l1', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l2', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l3', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l4', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l5', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l6', 4);
INSERT INTO lecture (name, course_id) VALUES ('c4-l7', 4);

INSERT INTO lecture (name, course_id) VALUES ('c5-l1', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l2', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l3', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l4', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l5', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l6', 5);
INSERT INTO lecture (name, course_id) VALUES ('c5-l7', 5);

INSERT INTO lecture (name, course_id) VALUES ('c6-l1', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l2', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l3', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l4', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l5', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l6', 6);
INSERT INTO lecture (name, course_id) VALUES ('c6-l7', 6);

INSERT INTO lecture (name, course_id) VALUES ('c7-l1', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l2', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l3', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l4', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l5', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l6', 7);
INSERT INTO lecture (name, course_id) VALUES ('c7-l7', 7);
--------------------------------------------------------------------

INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn1', 'ln1', 4, 1);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn2', 'ln2', 5, 2);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn3', 'ln3', 6, 3);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn4', 'ln4', 7, 4);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn5', 'ln5', 8, 1);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn6', 'ln6', 9, 2);
INSERT INTO student (first_name, last_name, id, groups_id) VALUES ('fn7', 'ln7', 10, 3);

INSERT INTO teacher (first_name, last_name, id) VALUES ('T-fn1', 'T-ln1', 1);
INSERT INTO teacher (first_name, last_name, id) VALUES ('T-fn2', 'T-ln2', 2);
INSERT INTO teacher (first_name, last_name, id) VALUES ('T-fn3', 'T-ln3', 3);