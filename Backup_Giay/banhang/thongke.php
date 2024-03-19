<?php
	include "connect.php";


	$query = "SELECT `product_id`, product.title , COUNT(`num`) AS total FROM `order_details` INNER JOIN product ON product.id = order_details.product_id GROUP BY `product_id`";
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
	print_r(json_encode($arr));

?>