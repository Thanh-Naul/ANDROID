<?php
	include "connect.php";
	$title= $_POST['title'];
	$price = $_POST['price'];
	$thumbnail = $_POST['thumbnail'];	
	$description = $_POST['description'];
	$category_id = $_POST['category_id'];
	$id = $_POST['id'];

	
	$query = 'UPDATE `product` SET `category_id`='.$category_id.',`title`="'.$title.'",`price`="'.$price.'",`thumbnail`="'.$thumbnail.'",`description`="'.$description.'" WHERE `id` = '.$id;
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