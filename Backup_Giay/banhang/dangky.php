<?php
	include "connect.php";
	$username = $_POST['username'];
	$email = $_POST['email'];
	$phone = $_POST['phone_number'];
	$pass = $_POST['password'];
	$uid = $_POST['uid'];

	

	$query = 'SELECT * FROM `user` WHERE `email` = "'.$email.'"';
	$data = mysqli_query($conn, $query);
	$numrow = mysqli_num_rows($data);
	
	if($numrow > 0){
		$arr = [
			'success' => false,
			'message' => "Email đã tồn tại",
			
		];
	}else{

		$query = 'INSERT INTO `user`(`username`, `email`, `phone_number`, `password`, `uid`) VALUES ("'.$username.'","'.$email.'","'.$phone.'","'.$pass.'", "'.$uid.'")';
		$data = mysqli_query($conn, $query);
		
		
		if($data == true){

			$arr = [
				'success' => true,
				'message' => "Thành công",
				];
		}else{
			$arr = [
				'success' => false,
				'message' => "Thất bại",
				
			];
		}
	}

	print_r(json_encode($arr));

?>