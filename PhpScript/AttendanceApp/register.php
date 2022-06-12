<?php
require_once "conndb.php";
Header('Content-Type: application/json; charset: UTF-8');



//if (isset($_POST['submit'])){
    if(isset($_POST['first_name']) && isset($_POST['last_name']) && isset($_POST['email_address']) && isset($_POST['student_password']) ){
    
    $firstName = $_POST['first_name'];
    $lastName = $_POST['last_name'];
    $email = $_POST['email_address'];
    $pass = $_POST['student_password'];

    //if all fields are not empty save user details to the database
    if(!empty($firstName) && !empty($lastName)  
    && !empty($email)&& !empty($pass) ){

    //clean the form data before sending to database
    $fname = mysqli_real_escape_string($conn, $firstName);
    $lname = mysqli_real_escape_string($conn, $lastName);
    $Studentemail = mysqli_real_escape_string($conn, $email);
    $password = mysqli_real_escape_string($conn, $pass);

    //check if email already exist in the database.
    $sql_check = mysqli_query($conn, "SELECT email_address FROM student WHERE email_address = '$email' ");
   if( mysqli_num_rows($sql_check)>0){
        $result = array();
       $result["exist"] = true;
       echo json_encode($result);
    }else{
        $sql = "INSERT INTO student (first_name, last_name, email_address, student_Password) 
        VALUES (?,?,?,?)";
        if($stmt = mysqli_prepare($conn,$sql)){
            $hashedpass = password_hash($pass, PASSWORD_DEFAULT);
            mysqli_stmt_bind_param($stmt, "ssss", $firstName, $lastName, $email, $hashedpass);
            if(mysqli_stmt_execute($stmt)){

                $response = array();
                $response["success"] = true;
                echo json_encode($response);
            }else{
                echo json_encode($response).mysqli_error($conn);
            }
            mysqli_stmt_close($stmt);
        }
        mysqli_close($conn);
     }
  }
}