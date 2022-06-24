<?php
	
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'attendance');
	
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	IF(mysqli_connect_errno()){
		die('Unable to connect to database' . mysqli_connect_error());
	}
	
	$query = $conn->prepare("SELECT first_name, last_name, email_address
	FROM `student` 
	WHERE last_name = 'Oziwo';");

	$query->execute();
	
	$query->bind_result($firstName, $lastName, $email );
	
	$course = array();
	
	while($query->fetch()){
		
		$temporaryArray = array();
		$temporaryArray['first_name'] = $firstName;
		$temporaryArray['last_name'] = $lastName;
		$temporaryArray['email_address'] = $email;
		
		array_push($course, $temporaryArray);

	}
	
	echo json_encode($course);
?>