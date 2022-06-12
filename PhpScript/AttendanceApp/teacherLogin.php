<?php
require_once "conndb.php";
//require_once "validate.php";


//if(isset($_POST['submit'])){
    if(isset($_POST['email_address']) && isset($_POST['teacher_password'])){

    $email = $_POST['email_address'];
    $pass = $_POST['teacher_password'];

    //clean the form data before sending to database
    $email_address = mysqli_real_escape_string($conn, $email);
    $password = mysqli_real_escape_string($conn, $pass);

    $sql ="SELECT teacher_id, email_address, teacher_password FROM teacher WHERE email_address = ?";

    if($stmt = mysqli_prepare($conn, $sql)){
        // Bind variables to the prepared statement as parameters
        mysqli_stmt_bind_param($stmt, "s", $email);
        
        // Attempt to execute the prepared statement
        if(mysqli_stmt_execute($stmt)){
            
        }else{
            echo "Error could not execute" . mysqli_error($conn);
        }
            //Bind result to variables when fetching.
            mysqli_stmt_bind_result($stmt, $id, $email, $hashedpwd);

             // Store result
            mysqli_stmt_store_result($stmt);

            //Check if there are results in the statement
            if(mysqli_stmt_num_rows($stmt) != 0){
               // fetch result from the database
                if(mysqli_stmt_fetch($stmt)){
                    //verify if password matches the one in the database
                    if($verify = password_verify($pass, $hashedpwd)){
                        // if username and Password is correct, start a new session
                        if($email == true && $verify == true){
                           // header('Refresh:2; url=welcome.php');
                            // echo "Login successful";
                            $response = array();
                             $response["success"] = true;
                             $response["incorrect"] = true;
                             $response["notFound"] = true;
                            echo json_encode($response);
                        }else{
                            echo json_encode($response);
                        }
                    }else{
                        echo json_encode($response);
                    }
                }
            }else{
                echo json_encode($response);
            }
        }else{
            echo "Oops! Something went wrong. Please try again later";
        }
        mysqli_stmt_free_result($stmt);
        mysqli_stmt_close($stmt);
        mysqli_close($conn);
}