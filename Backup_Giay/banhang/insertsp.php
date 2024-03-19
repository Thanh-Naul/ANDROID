<?php
	include "connect.php";
	$title= $_POST['title'];
	$price = $_POST['price'];
	$thumbnail = $_POST['thumbnail'];
	$description = $_POST['description'];
	$category_id = $_POST['category_id'];

	
	$query = 'INSERT INTO `product`(`category_id`, `title`, `price`, `thumbnail`, `description`) VALUES ('.$category_id.',"'.$title.'","'.$price.'","'.$thumbnail.'","'.$description.'")';
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