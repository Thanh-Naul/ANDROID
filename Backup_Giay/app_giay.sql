-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 28, 2023 lúc 06:05 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `app_giay`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `hinhanh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`, `hinhanh`) VALUES
(1, 'Trang chủ', 'https://i.imgur.com/g2iNs6M.png'),
(2, 'ADIDAS', 'https://i.imgur.com/EYRWKf1.png'),
(3, 'NIKE', 'https://logohistory.net/wp-content/uploads/2023/02/Nike-Logo.png'),
(4, 'Liên hệ', 'https://cdn.pixabay.com/photo/2016/11/01/03/05/contact-1787332_1280.png'),
(5, 'Thông tin', 'https://icons.veryicon.com/png/o/object/material-design-icons-1/information-68.png'),
(8, 'Đơn hàng', 'https://cdn-icons-png.flaticon.com/512/6948/6948527.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) NOT NULL,
  `num` int(11) NOT NULL,
  `total_money` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `status` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `address`, `phone_number`, `email`, `num`, `total_money`, `status`) VALUES
(76, 45, 'bb', '0123456789', 'user2@gmail.com', 2, '854000', 4),
(77, 45, 'as', '0123456789', 'user2@gmail.com', 3, '357000', 3),
(78, 45, 'av', '0123456789', 'user2@gmail.com', 2, '336000', 3),
(79, 45, 'ab', '0123456789', 'user2@gmail.com', 2, '1750000', 0),
(80, 45, 'ab', '0123456789', 'user2@gmail.com', 2, '358000', 0),
(81, 45, 'az', '0123456789', 'user2@gmail.com', 3, '1281000', 0),
(82, 45, 'xz', '0123456789', 'user2@gmail.com', 6, '3240000', 3),
(83, 46, 'az', '0123456', 'user3@gmail.com', 3, '7500000', 0),
(84, 46, 'AS', '0123456', 'user3@gmail.com', 4, '2500000', 3),
(86, 46, 'abc', '0123456', 'user3@gmail.com', 1, '2500000', 3),
(87, 45, 'abc', '0123456789', 'user2@gmail.com', 2, '5000000', 0),
(88, 45, 'abc', '0123456789', 'user2@gmail.com', 2, '1250000', 2),
(89, 45, 'a', '0123456789', 'user2@gmail.com', 2, '336000', 2),
(90, 45, 'a', '0123456789', 'user2@gmail.com', 1, '2500000', 1),
(91, 45, 'a', '0123456789', 'user2@gmail.com', 1, '2500000', 3),
(92, 45, 'ab', '0123456789', 'user2@gmail.com', 1, '625000', 1),
(93, 45, 'a', '0123456789', 'user2@gmail.com', 1, '2500000', 2),
(94, 45, 'a', '0123456789', 'user2@gmail.com', 1, '2500000', 1),
(95, 45, 'e', '0123456789', 'user2@gmail.com', 1, '625000', 2),
(96, 40, 'ab', '0123456789', 'user1@gmail.com', 2, '1250000', 2),
(97, 47, 'a', '0123456', 'user4@gmail.com', 1, '625000', 2),
(102, 45, 'a', '0123456789', 'user2@gmail.com', 2, '595000', 1),
(103, 47, 'Ax', '0123456', 'user4@gmail.com', 2, '5000000', 2),
(104, 40, 'abc', '0123456789', 'user1@gmail.com', 1, '2500000', 2),
(105, 45, 'a', '0123456789', 'user2@gmail.com', 2, '3125000', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `order_details`
--

INSERT INTO `order_details` (`id`, `product_id`, `num`, `price`) VALUES
(76, 21, 2, '427000'),
(77, 17, 3, '119000'),
(78, 22, 2, '168000'),
(79, 19, 2, '875000'),
(80, 2, 2, '179000'),
(81, 21, 3, '427000'),
(82, 15, 6, '540000'),
(83, 24, 3, '2500000'),
(84, 23, 4, '625000'),
(86, 24, 1, '2500000'),
(87, 24, 2, '2500000'),
(88, 23, 2, '625000'),
(89, 22, 2, '168000'),
(90, 24, 1, '2500000'),
(91, 24, 1, '2500000'),
(92, 23, 1, '625000'),
(93, 24, 1, '2500000'),
(94, 24, 1, '2500000'),
(95, 23, 1, '625000'),
(96, 23, 2, '625000'),
(97, 23, 1, '625000'),
(98, 21, 1, '427000'),
(99, 24, 1, '2500000'),
(100, 23, 1, '625000'),
(101, 24, 1, '2500000'),
(101, 23, 1, '625000'),
(101, 22, 1, '168000'),
(102, 21, 1, '427000'),
(102, 22, 1, '168000'),
(103, 24, 2, '2500000'),
(104, 24, 1, '2500000'),
(105, 24, 1, '2500000'),
(105, 23, 1, '625000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `price` varchar(11) DEFAULT NULL,
  `thumbnail` text DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `category_id`, `title`, `price`, `thumbnail`, `description`) VALUES
(1, 2, 'Giày Jordan Cổ Cao, Giày Thể Thao Nam Nữ Sneaker Thời Trang', '269000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lkt3d3v4a40026', 'Thông tin sản phẩm Giày Jordan Cổ Cao: \nChất liệu da : Da sần, da nỉ, da trơn\nChất liệu đế : 85% cao su SR, 15% nhựa pp\nChất liệu lót : Premium\nForm EU : 36,37,38,39,40,41,42,43\nChiều cao đế : 2.5cm\nKiểu dáng : Cổ thấp, cổ cao'),
(2, 2, 'Giày Air Force 1 Low Cream Black', '179000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-llk75vf362tk08', 'Giày đẹp, nhẹ, bền. Giá phải chăng phù hợp với các bạn học sinh, sinh viên. Có thể làm giày đôi, giày nhóm. Thích hợp đi chơi, du lịch, chạy bộ, gym, đi học, đi làm. '),
(3, 1, 'Giày adidas samba Classic White', '139000', 'https://down-vn.img.susercontent.com/file/vn-11134201-7r98o-lljlzgslwgy0fa', 'Thông tin sản phẩm:\n- size 36-43\n- Chất lượng tốt nhất trong tầm giá\n- Form đẹp chuẩn :  Màu sắc giống đến 98%; \n-  Chất liệu da  + da lộn + vải mesh \n-  Logo Mông in dập chìm.\n-  Lưỡi gà cao dày dặn; swoosh sắc nét; Mông mũi làm đẹp'),
(4, 1, 'Giày Thể Thao Adidas Alpha Thoáng Khí Thời Trang', '299000', 'https://down-vn.img.susercontent.com/file/79b0d5633380b30526fc15fd373fbffa', 'Loại Khóa: Khóa dây\nLoại da: Da lộn\nDa ngoài: Bóng\nChất liệu: Canvas, Da, Cao su, Nylon, Sợi tổng hợp\nXuất xứ: Hàn Quốc\nDịp: Thể thao\nChiều cao cổ giày: Cổ thấp'),
(5, 2, 'Giày_Nike Air Force 1 Trắng, Giày AF1 Trắng', '249000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lnidb1028cca8d', 'Thông tin sản phẩm Giày Nike_Nam Nữ: \nChất liệu da : Da sần, da nỉ, da trơn\nChất liệu đế : 85% cao su SR, 15% nhựa pp\nChất liệu lót : Premium\nForm EU : 36,37,38,39,40,41,42,43\nChiều cao đế : 2.5cm\nKiểu dáng : Cổ thấp, cổ cao'),
(6, 1, 'Giày adidas alphabounce', '250000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lf5nnxpq15qved', 'Loại bảo hàng: Bảo hành nhà sản xuất\nGiới tính: Unisex\nLoại Khóa: Khóa dây\nXuất xứ: Quảng Châu\nKiểu giày: thể thao\n'),
(7, 2, 'Giày Jordan Màu Hồng Pink Quartz', '255000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lf84owyy87dj03', 'Thông tin sản phẩm Giày Jordan Nữ: \nChất liệu da : Da sần, da nỉ, da trơn\nChất liệu đế : 85% cao su SR, 15% nhựa pp\nChất liệu lót : Premium\nForm EU : 36,37,38,39,40,41,42,43\nChiều cao đế : 2.5cm\nKiểu dáng : Cổ thấp, cổ cao'),
(8, 2, 'Giày af1 Dây Thừng - Giày Mike Air Force 1 Cavans Big Bang', '229000', 'https://down-vn.img.susercontent.com/file/sg-11134201-23020-qgtpi7p9v9mv48', 'Thông tin giày:\nChất liệu : Da bò thật 100% nhập khẩu\nĐế giày: Cao su thiên nhiên 100% chống chơn trượt, độ bám mặt đường cao\nLót giày : Công nghệ cupsole memory foam độ êm tuyệt đối khi mang .\nPhù hợp sử dụng các hoạt động hằng ngày, đi làm đi chơi…'),
(9, 1, 'Giày Thể Thao Nam Adidas Aphamagma Bản Mới Nhất 2022', '680000', 'https://down-vn.img.susercontent.com/file/sg-11134201-23010-kk9z47j8nwlv3f', 'Màu : XÁM FULL\nSize : 40-43\nChất liệu : Vải dệt thoáng khí cao cấp. không hôi chân\nĐế cao su non êm ái, siêu nhẹ , đàn hồi tốt\nPhù hợp làm giày chạy bộ, giày leo núi, giày chơi thể thao'),
(10, 1, 'Giày thể thao nam nữ adidas Forum low 85', '550000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lkdsjk79jgyk5b', 'https://down-vn.img.susercontent.com/file/sg-11134201-22110-3o6yn446p3jv7b'),
(11, 2, 'Giày thể thao Jordan xanh ngọc cổ thấp', '147000', 'https://down-vn.img.susercontent.com/file/vn-11134201-23030-pmw9hgk10sov77', 'Giày đẹp, nhẹ, bền. Giá phải chăng phù hợp với các bạn học sinh, sinh viên. Có thể làm giày đôi, giày nhóm. Thích hợp đi chơi, du lịch, chạy bộ, gym, đi học, đi làm... \n'),
(12, 1, 'Giày Thể Thao Adidas Màu Trắng', '199000', 'https://down-vn.img.susercontent.com/file/7bda8f90ce9f4f6479a28347cfa7dddc', 'Tác dụng: chống trượt\nChất liệu đế: EVA\nNhững người có thể áp dụng: Chung\nCông nghệ giày thể thao: chức năng uốn cong dễ dàng\nChất liệu trên giày: Lưới\nKịch bản áp dụng: đi dạo thư giãn'),
(13, 2, 'Giày_Nike AF1 Full Trắng', '199000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lmibqdfr4d0fb2', 'Thông tin sản phẩm Giày_Nike: \nChất liệu da : Da sần, da nỉ, da trơn\nChất liệu đế : 85% cao su SR, 15% nhựa pp\nChất liệu lót : Premium\nForm EU : 36,37,38,39,40,41,42,43\nChiều cao đế : 2.5cm\nKiểu dáng : Cổ thấp, cổ cao'),
(14, 2, 'Giày Jordan PSG cổ cao', '259000', 'https://down-vn.img.susercontent.com/file/vn-11134207-23020-5id6ciejn9mv99', 'Giày đẹp, nhẹ, bền. Giá phải chăng phù hợp với các bạn học sinh, sinh viên. Có thể làm giày đôi, giày nhóm. Thích hợp đi chơi, du lịch, chạy bộ, gym, đi học, đi làm... '),
(15, 1, 'Giày thể thao Adidas đế mềm thời trang mùa hè', '540000', 'https://down-vn.img.susercontent.com/file/cf361b280a6f724a9593e4916bbcc5d2', 'Chiều rộng phù hợp: Có\nLoại Khóa: Khóa dây\nLoại da: Da Trâu\nDa ngoài: Bóng\nXuất xứ: Trung Quốc\nChất liệu: Da\nDịp: Thể thao\nChiều cao cổ giày: Cổ thấp'),
(16, 1, 'Giày Thể Thao Adidas Alphabounce Beyond', '1170000', 'https://down-vn.img.susercontent.com/file/6da5f4e9c774c7a8bf37465a9dd19815', 'Thương hiệu: Adidas\nChiều rộng phù hợp: Không\nLoại Khóa: Khóa dây\nDa ngoài: Mờ\nXuất xứ: Việt Nam\nChất liệu: Da PU, Cao su, Sợi tổng hợp\nDịp: Hằng Ngày\nChiều cao cổ giày: Cổ thấp'),
(17, 2, 'Giày Sneaker Nike_Blazer Mid 77 Vintage White Black', '119000', 'https://down-vn.img.susercontent.com/file/vn-11134201-23020-4p82whfo01nved', ' Kiểu dáng hot trend của năm nay và còn kéo dài trong những năm tiếp theo. Đế giày được thiết kế chịu ma sát tốt, tăng chiều cao, nhẹ, êm, cân bằng và thoáng khí '),
(18, 1, 'Giày Thể Thao Adidas_Forum 84 Nam Nữ Quai Dán', '197000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lm7s3u030p2790', 'Loại Khóa: Khóa dây\nLoại da: Da bò\nDa ngoài: Bóng\nXuất xứ: Trung Quốc\nChất liệu: Da, Cao su\nDịp: Hằng Ngày\nChiều cao cổ giày: Cổ cao'),
(19, 2, 'Giày_Nike air force 1 bản cao cấp', '875000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lk00ui0kxfw4a0', ' Kiểu dáng hot trend của năm nay và còn kéo dài trong những năm tiếp theo. Đế giày được thiết kế chịu ma sát tốt, tăng chiều cao, nhẹ, êm, cân bằng và thoáng khí'),
(20, 2, 'Giày NIKE_AF1 Âm Dương Phản Quang', '119000', 'https://down-vn.img.susercontent.com/file/sg-11134201-23020-hnu1nivc19mvf4', 'Giày cao gót: Trung Bình\nLoại Khóa: Khóa Zip\nChất liệu: Canvas, Da\nChiều rộng phù hợp: Có\nXuất xứ: Việt Nam\nDịp: Hằng Ngày\nChiều cao cổ giày: Cổ thấp'),
(21, 1, 'Giày Adidas Samba Classic White nam nữ', '427000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7r98o-lm6xuofbixun78', 'Loại Khóa: Khóa dây\nLoại da: Da bò\nDa ngoài: Dập nổi\nXuất xứ: Trung Quốc\nChất liệu: Cối, Canvas, Da, Cao su\nDịp: Hằng Ngày\nChiều cao cổ giày: Cổ thấp'),
(22, 1, 'Giày_thể thao Adidas clover SUPERSTAR Kentaro Okawara', '168000', 'https://down-vn.img.susercontent.com/file/vn-11134201-23020-t88siy15dynvd0', 'Loại da: Da dê\nXuất xứ: Việt Nam\nChất liệu: Da, Cao su\nDịp: Thể thao\nChiều cao cổ giày: Cổ thấp'),
(23, 2, 'Giày Nike_ Air Force 1 Panda Đen Trắng', '625000', 'https://down-vn.img.susercontent.com/file/vn-11134207-7qukw-lk2mt4xzbqno57', 'Giày cao gót: Trung Bình\nLoại Khóa: Khóa dây\nChất liệu: Canvas, Da, Cao su\nChiều rộng phù hợp: Có\nXuất xứ: Việt Nam\nDịp: Hằng Ngày\nChiều cao cổ giày: Cổ thấp'),
(24, 1, 'Giày Alphaboost V1 Nam Đen HQ4517', '2500000', 'https://down-vn.img.susercontent.com/file/vn-11134201-7r98o-lnvdf64hc4fu8a', '- Dáng regular fit\n- Có dây giày\n- Thân giày bằng vải lưới\n- Lớp đệm Adiprene+\n- Đế giữa kết hợp đệm BOOST và Bounce\n- Đế ngoài Adiwear');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `uid` text NOT NULL,
  `token` text NOT NULL,
  `role` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `phone_number`, `password`, `uid`, `token`, `role`) VALUES
(39, 'Trung', 'tr90642@gmail.com', '0123456789', '123456', 'rL1WSzeTQFSOeXlLfTF4FaHAM6K2', 'cKtkYyMER_mAnrnh3FLPBI:APA91bHDkNbdpLWf0aL742GIiaElF3628paqv5bqhzdfLHMN1uWl4ZAhNqGR45HM0QRMkZ6N6g0Ldld_jtStpPNTBDELh7fWS5fZ6l64qC250gSP2WFxgRpTUddv27I4vs41ezpUYXYL', 1),
(40, 'user1', 'user1@gmail.com', '0123456789', '123456', 'd0Q6iitYoff0m4gnsJaHGCNA1DG3', 'fvqZQfGOS1CUNt6PH4pai9:APA91bHHsnXQn4AnLGBxlcOW-QuuLr0sFriOMA5FhldW9pqmbLY3JxaMMzFoU9eWPVlTRAbJ-e3MKxQPmngVIn4WfdrK1HeO05hrBxb7UPKA86gD0UW6RPVWEP2-B8XdTe55i3QU8P8R', 0),
(45, 'user2', 'user2@gmail.com', '0123456789', 'onfirebase', 'TQmhgGAdz0glDMrmftaYmXoEtnu1', 'doUh2AahRWidvwktVPI5jq:APA91bF_QHc3ZpoCQ65-xhf1L0vra_42suNcRSY-IgoY0UFQ5ErvCsZg62jFNZchktosf3UT-z97gZZGXNK2IMMHE99JViyrIXLNm3PFflslxxJQ6OXZv8EeJCgVMDmVfkkQOcd74qo3', 0),
(46, 'User', 'user3@gmail.com', '0123456', 'onfirebase', '9QydyiDHH7ObflhC9rX5edrIaXI3', 'fvqZQfGOS1CUNt6PH4pai9:APA91bHHsnXQn4AnLGBxlcOW-QuuLr0sFriOMA5FhldW9pqmbLY3JxaMMzFoU9eWPVlTRAbJ-e3MKxQPmngVIn4WfdrK1HeO05hrBxb7UPKA86gD0UW6RPVWEP2-B8XdTe55i3QU8P8R', 0),
(47, 'user4', 'user4@gmail.com', '0123456', 'onfirebase', 'q6E6k3hnxZh0QNyhzpTSl3731fW2', 'fvqZQfGOS1CUNt6PH4pai9:APA91bHHsnXQn4AnLGBxlcOW-QuuLr0sFriOMA5FhldW9pqmbLY3JxaMMzFoU9eWPVlTRAbJ-e3MKxQPmngVIn4WfdrK1HeO05hrBxb7UPKA86gD0UW6RPVWEP2-B8XdTe55i3QU8P8R', 0),
(48, 'User5', 'user5@gmail.com', '0123456789', 'onfirebase', 'S4L39o4BsnOKnMCmJ6FDWjzOilj1', 'fvqZQfGOS1CUNt6PH4pai9:APA91bHHsnXQn4AnLGBxlcOW-QuuLr0sFriOMA5FhldW9pqmbLY3JxaMMzFoU9eWPVlTRAbJ-e3MKxQPmngVIn4WfdrK1HeO05hrBxb7UPKA86gD0UW6RPVWEP2-B8XdTe55i3QU8P8R', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
