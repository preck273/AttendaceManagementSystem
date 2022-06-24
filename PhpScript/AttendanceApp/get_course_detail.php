<?php

	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_NAME', 'attendance');
	
	$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
	
	IF(mysqli_connect_errno()){
		die('Unable to connect to database' . mysqli_connect_error());
	}
	
	$query = $conn->prepare("SELECT course_name, course_start_date, course_end_date FROM `course`;");

	$query->execute();
	
	$query->bind_result($name, $startDate, $endDate );
	
	$course = array();
	
	while($query->fetch()){
		
		$temporaryArray = array();
		$temporaryArray['course_name'] = $name;
		$temporaryArray['course_start_date'] = $startDate;
		$temporaryArray['course_end_date'] = $endDate;
		
		array_push($course, $temporaryArray);

	}
	
	echo json_encode($course);