<?php
	$host = "localhost";
	$user = "root";
	$pass = "";
	$database = "app_giay";

	$conn = mysqli_connect($host, $user, $pass, $database);
	mysqli_set_charset($conn,"utf8");
?>