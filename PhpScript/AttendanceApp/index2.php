<?php
require_once "login.php";
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <div class="signup-form">
        <form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post">
            
            <h2>Create an account</h2><br>    
            <p><lable for="email">Email Address</lable></p>
            <p><input type="text" name="email_address" value="<?php echo isset($_POST['email']) ? $_POST['email'] : '' ?>">
           

            <p><lable for="pass1">Password</lable></p>
            <p><input type="password" name="student_password">
            

            <p><input type="submit" name="submit" id="submit" value="Create an account"></p><br>
            <p>Do you already have an account?<a href="login.php"> Log In</a></p>

        </form>
    </div>
</div>
</div><br>
</body>
</html>
</body>
</html>