<?php
	include "connect.php";
	$user_id = $_POST['user_id'];


	if($user_id == 0){
		$query = 'SELECT orders.id, orders.user_id, orders.address, orders.phone_number, orders.email, orders.num, orders.total_money, orders.status, user.username FROM `orders` INNER JOIN user ON orders.user_id = user.id ORDER BY orders.id DESC';
	}else{
		$query = 'SELECT * FROM `orders` WHERE `user_id` = '.$user_id.' ORDER BY id DESC';
	}

	$data = mysqli_query($conn, $query);
	$result = array();
	while ($row = mysqli_fetch_assoc($data)) {
			$truyvan = 'SELECT * FROM `order_details` INNER JOIN product ON order_details.product_id = product.id WHERE order_details.id = '.$row['id'];
			$data1 = mysqli_query($conn, $truyvan);
			$item = array();
			while ($row1 = mysqli_fetch_assoc($data1)) {
				$item[] = $row1;
			}

		$row['item'] = $item;
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