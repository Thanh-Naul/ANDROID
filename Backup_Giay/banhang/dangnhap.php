<?php
	include "connect.php";
	$email = $_POST['email'];
	$pass = $_POST['password'];

	$query = 'SELECT * FROM `user` WHERE `email` = "'.$email.'"';
	$data = mysqli_query($conn, $query);
	$result = array();
	while ($row = mysqli_fetch_assoc($data)) {
		$result[] = ($row);
	}
	
	if(!empty($result)){

		$arr = [
			'success' => true,
			'message' => "Thành công",
			'result' => $result
		];
	}else{
		$arr = [
			'success' => false,
			'message' => "Thất bại",
			'result' => $result
		];
	}
	print_r(json_encode($arr));

?>