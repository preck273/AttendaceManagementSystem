<?php

//connect to the database created
$conn = mysqli_connect('localhost', 'root', '', 'attendance');
    if(!$conn){
        die("Connection Error" . mysqli_error($conn));
    }//else{
        //echo "success";
    //}

    //create tables in the database name attendance
    // $db = "CREATE TABLE student
    //     (
    //        student_id int not null auto_increment,
    //        first_name varchar(30) NOT NULL,
    //        last_name varchar(30) NOT NULL,
    //        email_address varchar(50) NOT NULL, 
    //        student_password varchar(255) NOT NULL,
    //        CONSTRAINT pk_student PRIMARY KEY(student_id)

    //     )";

    // $db = "CREATE TABLE teacher
    //     (
    //     teacher_id int not null auto_increment,
    //     first_name varchar(30) NOT NULL,
    //     last_name varchar(30) NOT NULL,
    //     email_address varchar(50) NOT NULL, 
    //     teacher_password varchar(255) NOT NULL,
    //     CONSTRAINT pk_teacher PRIMARY KEY(teacher_id)

    //     )";

    // $db = "CREATE TABLE course
    //     (
    //     course_id int not null auto_increment,
    //     course_name varchar(30) NOT NULL,
    //     CONSTRAINT pk_course PRIMARY KEY(course_id)

    //     )";

    //  $db = "CREATE TABLE enroll
    //     (

    //     course_id int not null ,
    //     student_id int not null ,
    //     CONSTRAINT enroll_course_fk FOREIGN KEY (course_id)
    //     REFERENCES course (course_id)
    //                 ON UPDATE CASCADE
    //                 ON DELETE NO ACTION,

    //     CONSTRAINT enroll_student_fk FOREIGN KEY (student_id)
    //     REFERENCES student (student_id)
    //                 ON UPDATE CASCADE
    //                 ON DELETE NO ACTION

    //     )";    
        
    //  $db = "CREATE TABLE schedule
    //     (
    //     schedule_id int not null auto_increment,
    //     course_name varchar(30) NOT NULL,
    //     room_number int null,
    //     course_date DATE NULL,
    //     start_time TIME NULL,
    //     end_time TIME NULL,
    //     CONSTRAINT pk_schedule PRIMARY KEY(schedule_id)
            
    //     )";

    // $db = "CREATE TABLE presentLog
    // (
    // presentLog_id int not null auto_increment,
    // student_id int not null,
    // course_id int not null,
    // CONSTRAINT pk_presentLog PRIMARY KEY(presentLog_id),
    // CONSTRAINT presentLog_student_fk FOREIGN KEY(student_id)
    // REFERENCES student(student_id)
    //         ON UPDATE CASCADE
    //         ON DELETE NO ACTION,

    // CONSTRAINT presentLog_course_fk FOREIGN KEY(course_id)
    // REFERENCES course (course_id)
    //         ON UPDATE CASCADE
    //         ON DELETE NO ACTION
    // )";

    $db = "CREATE TABLE absentLog
    (
     absentLog_id int not null auto_increment,
    student_id int not null,
    course_id int not null,
    CONSTRAINT pk_absentLog PRIMARY KEY(absentLog_id),
    CONSTRAINT absentLog_student_fk FOREIGN KEY(student_id)
    REFERENCES student(student_id)
            ON UPDATE CASCADE
            ON DELETE NO ACTION,

    CONSTRAINT absentLog_course_fk FOREIGN KEY(course_id)
    REFERENCES course (course_id)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
    )";

    

$statement = mysqli_prepare($conn, $db)or
die(mysqli_error($conn));
mysqli_stmt_execute($statement)or
die("There is an Error creating the Table");
echo ("Table created");
mysqli_stmt_close($statement);
mysqli_close($conn);

