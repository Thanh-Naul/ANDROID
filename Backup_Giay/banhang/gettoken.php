<?php
	include "connect.php";
	$role = $_POST['role'];

	if($role == 1){
		$query = "SELECT * FROM `user` WHERE `role` = ".$role;
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
	}else if($role == 0){
		$user_id = $_POST['user_id'];

		$query = "SELECT * FROM `user` WHERE `id` = ".$user_id." AND `role` = ".$role;
		$data = mysqli_query($conn, $query);
		$result = array();
		while ($row = mysqli_fetch_assoc($data)) {
			$result[] = ($row);
		}
		
		if(!empty($result)){

			$arr = [
				'success' => true,
				'message' => "thanh cong",
				'result' => $result
			];
		}else{
			$arr = [
				'success' => false,
				'message' => "khong thanh cong",
				'result' => $result
			];
		}

	}

	
	print_r(json_encode($arr));

?>