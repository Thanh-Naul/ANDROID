<?php
	include "connect.php";
	$phone_number = $_POST['phone_number'];
	$email = $_POST['email'];
	$total_money = $_POST['total_money'];
	$user_id  = $_POST['user_id'];
	$address = $_POST['address'];		
	$num = $_POST['num'];
	$chitiet = $_POST['chitiet'];


	$query = 'INSERT INTO `orders`(`user_id`, `address`, `phone_number`, `email`, `num`, `total_money`) VALUES ('.$user_id.',"'.$address.'","'.$phone_number.'","'.$email.'",'.$num.',"'.$total_money.'")';
	$data = mysqli_query($conn, $query);

	if($data == true){
		$query = 'SELECT id AS iddonhang FROM `orders` WHERE `user_id` = '.$user_id.' ORDER BY id DESC LIMIT 1';
		$data = mysqli_query($conn, $query);

		while ($row = mysqli_fetch_assoc($data)) {
				$iddonhang = ($row);
		}

		if(!empty($iddonhang)){
			$chitiet = json_decode($chitiet, true);
			foreach ($chitiet as $key => $value) {
				$truyvan = 'INSERT INTO `order_details`(`id`, `product_id`, `num`, `price`) VALUES ('.$iddonhang["iddonhang"].','.$value["idsp"].','.$value["soluong"].',"'.$value["giasp"].'")';
				
				$data = mysqli_query($conn, $truyvan);
			}

			if($data == true){
					$arr = [
						'success' => true,
						'message' => "thanh cong"
					];
				}else{
					$arr = [
						'success' => false,
						'message' => "khong thanh cong"
					];
				}
				print_r(json_encode($arr));
			}		
		}else{
			$arr = [
				'success' => false,
				'message' => "khong thanh cong"
			];
			print_r(json_encode($arr));
		}
?>