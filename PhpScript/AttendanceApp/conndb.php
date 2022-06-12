<?php
//connect to the the database 
$conn = mysqli_connect('localhost', 'root', '', 'attendance');
    if(!$conn){
        die("Connection Error" . mysqli_error($conn));
    }