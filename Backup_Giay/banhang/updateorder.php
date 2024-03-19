<?php
	include "connect.php";
	

	$id = $_POST['id'];
	$status = $_POST['status'];

	
	$query = 'UPDATE `orders` SET `status`='.$status.' WHERE `id` = '.$id;
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

	print_r(json_encode($arr));

?>