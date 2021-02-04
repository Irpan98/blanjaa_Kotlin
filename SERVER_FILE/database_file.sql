-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.13-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for db_blanjaa
CREATE DATABASE IF NOT EXISTS `db_blanjaa` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `db_blanjaa`;

-- Dumping structure for table db_blanjaa.bestproducts
CREATE TABLE IF NOT EXISTS `bestproducts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.bestproducts: ~3 rows (approximately)
/*!40000 ALTER TABLE `bestproducts` DISABLE KEYS */;
REPLACE INTO `bestproducts` (`id`, `product_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3);
/*!40000 ALTER TABLE `bestproducts` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `backgroundPath` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.categories: ~5 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
REPLACE INTO `categories` (`id`, `name`, `backgroundPath`) VALUES
	(1, 'Komputer dan Laptop', 'no'),
	(2, 'Handphone', 'no'),
	(3, 'Pakaian', 'no'),
	(4, 'Perabotan Rumah Tangga', 'no'),
	(5, 'Makanan', 'no');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.checkouts
CREATE TABLE IF NOT EXISTS `checkouts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.checkouts: ~4 rows (approximately)
/*!40000 ALTER TABLE `checkouts` DISABLE KEYS */;
REPLACE INTO `checkouts` (`id`, `product_id`, `user_id`) VALUES
	(31, 9, 14),
	(33, 3, 18),
	(52, 8, 29),
	(53, 1, 29);
/*!40000 ALTER TABLE `checkouts` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.ci_sessions
CREATE TABLE IF NOT EXISTS `ci_sessions` (
  `id` varchar(128) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `timestamp` int(10) unsigned NOT NULL DEFAULT 0,
  `data` text NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `timestamp` (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.ci_sessions: ~0 rows (approximately)
/*!40000 ALTER TABLE `ci_sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `ci_sessions` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.histories
CREATE TABLE IF NOT EXISTS `histories` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  `payment` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.histories: ~6 rows (approximately)
/*!40000 ALTER TABLE `histories` DISABLE KEYS */;
REPLACE INTO `histories` (`id`, `product_id`, `user_id`, `date`, `payment`) VALUES
	(15, 1, 29, '23 Dec 2020', 'GoPay'),
	(16, 9, 29, '23 Dec 2020', 'Google Play'),
	(17, 8, 29, '23 Dec 2020', 'Credit Card'),
	(18, 321, 21, '1', '234234awd'),
	(19, 3, 36, '04 Feb 2021', 'Google Play'),
	(20, 2, 36, '04 Feb 2021', 'GoPay');
/*!40000 ALTER TABLE `histories` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.migrations
CREATE TABLE IF NOT EXISTS `migrations` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `version` varchar(255) NOT NULL,
  `class` text NOT NULL,
  `group` varchar(255) NOT NULL,
  `namespace` varchar(255) NOT NULL,
  `time` int(11) NOT NULL,
  `batch` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.migrations: ~7 rows (approximately)
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;
REPLACE INTO `migrations` (`id`, `version`, `class`, `group`, `namespace`, `time`, `batch`) VALUES
	(50, '2020-11-24-094410', 'App\\Database\\Migrations\\Products', 'default', 'App', 1608610316, 1),
	(51, '2020-11-24-162718', 'App\\Database\\Migrations\\Categories', 'default', 'App', 1608610316, 1),
	(52, '2020-11-25-103945', 'App\\Database\\Migrations\\Wishlists', 'default', 'App', 1608610316, 1),
	(53, '2020-11-25-104022', 'App\\Database\\Migrations\\BestProducts', 'default', 'App', 1608610316, 1),
	(54, '2020-11-25-114112', 'App\\Database\\Migrations\\Checkouts', 'default', 'App', 1608610316, 1),
	(55, '2020-11-27-021950', 'App\\Database\\Migrations\\Users', 'default', 'App', 1608610316, 1),
	(56, '2020-11-27-140004', 'App\\Database\\Migrations\\Histories', 'default', 'App', 1608610316, 1);
/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` int(255) NOT NULL,
  `categori_id` int(255) NOT NULL,
  `imagePath` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=455 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.products: ~9 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
REPLACE INTO `products` (`id`, `name`, `description`, `price`, `categori_id`, `imagePath`) VALUES
	(1, 'HP s1000 Mouse Wireless USB Optical 1600DPI /HP WIRELESS MOUSE ', 'Mouse wireless kualitas terjamin dengan design yang pas di genggaman tangan anda. Sangat cocok buat anda yg sering berpergian dengan notebook anda karena mouse ini sangat mudah dimasukan ke dalam tas laptop anda. Menggunakan 2 buah baterai AA (BATERAI TIDAK TERMASUK).', 58000, 1, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2020/8/7/68984376/68984376_f21ddffd-cbc5-4c0f-8213-1616eb41562a_800_800.webp'),
	(2, 'IPHONE X 64GB 64 GARANSI 1 TAHUN ', 'Deskripsi IPHONE X 64GB 64 GARANSI 1 TAHUN TERMURAH BLACK WHITE GREY SILVER - Putih', 2220000, 2, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2020/8/24/110238905/110238905_56e925ed-104d-4c6f-aa08-4c1a52ddba4a_1024_1024.webp'),
	(3, 'Robot RT-BF10 Cleanable 720\' Rotating Clip Base Portable', 'Spesifikasi : Model : RT-BF10,Input DC : 5V/1A,Battery Capacity : 2000mAh,Size : 150x190x100mm, Weight : 258g', 2220000, 4, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2020/8/8/28803018/28803018_200815fe-b88b-4893-8991-f8f3fb5bb29e_700_700.webp'),
	(4, 'Mug Kantor Gelas Teh / Kopi Panas Stainless Souvenir Promosi', 'Material : body dari Stainless Stell, gagang dari plastik, tutup dari stainless stell + plastik, Ukuran : 7.2 x 7.2 x 12.5 cm, Kapasitas : 350 ml, Kemasan : box', 2220000, 4, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2015/9/9/244841/244841_0e069aaf-bca9-4c63-a0e8-274d1b22f2dd.jpg.webp'),
	(5, 'Piring Makan Cekung Keramik Ming 9" isi 6 pcs', '1 set terdiri dari 6 buah piring cekung MING,', 21321, 4, 'https://ecs7.tokopedia.net/img/cache/200-square/VqbcmM/2020/9/8/08a9fdf5-bac9-481b-b9a4-8b2711d36502.jpg.webp'),
	(6, 'ECLE Bluetooth 5.1 Wireless Earphone /Headset In Ear HD', 'ECLE Earphone wireless bluetooth yang sangat ringan,memberikan kenyamanan disaat anda menggunakannya. Shell waterproff dan teknologi interior Nano-coating yang melindungi earphone dari hujan dan keringat selama anda melakukan aktivitas. One-touch button yang ada pada headphones akan membuat anda lebih praktis dalam menggunakannya, seperti untuk bermain game dan menunda pemutaran musik, mengatur suara, menjawab dan mengakhiri telpon, dll. Pakailah earphone bluetooth stereo HD ini, dan anda akan dapat menikmati nyamannya mendengar musik audio HD setiap waktu.', 80000, 2, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2020/9/15/53087535/53087535_c89c4320-6382-4912-b196-af7d250209e1_2048_2047.webp'),
	(7, 'MINISO Headset Earphone Headphone Dilipat Portable ', 'MINISO Headset Earphone Headphone Dilipat Portable Simple Original', 160000, 1, 'https://ecs7.tokopedia.net/img/cache/200-square/VqbcmM/2020/10/16/ea44aa5a-2bd5-4753-a5a4-ad14dd2fdc0e.jpg.webp'),
	(8, 'SEDAP GORENG / MIE SEDAP GORENG MURAH', 'Mie Instant dalam kemasan, terbuat dari bahan mie berkualitas terbaik dan bumbu-bumbu pilihan sehingga menciptakan rasanya yang sedap. Mie Sedaap terus berinovasi untuk memberikan yang terbaik bagi konsumennya. Karena soal rasa lidah ngga bisa bohong. Mie Sedaap merupakan satu-satunya mi instant yang mendapatkan sertifikasi ISO 22000. Terdapat 9 varian rasa :', 2500, 5, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2020/5/15/1499263/1499263_b434dab3-b6a6-4578-9a78-240220716242_640_640.webp'),
	(9, 'Kaos google baju distro hitam', 'Kaos google baju distro hitam ini sangat cocok untuk di pakai saat jalan jalan maupun untuk sehari hari,', 90000, 3, 'https://ecs7.tokopedia.net/img/cache/200-square/product-1/2018/9/22/6924252/6924252_bcdc3571-c165-472a-85f9-7cf9aab9c816_2048_1842.jpg.webp');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `token` longtext DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.users: ~8 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
REPLACE INTO `users` (`id`, `name`, `email`, `password`, `token`) VALUES
	(29, 'wa', 'aww@gmail.com', '$2y$10$eKA0ZB2gTcXOnUlJSe5e.OGEVG2HwqVH1jsxpvnmvuen8u4mgZ33G', 'e0E0nyYiSAGc1adM3S358z:APA91bECA02Uazf4EgsHaImX75xtdcJ74oOciSoQC1gnKze5H9gJHN1qrvg_GvgaMkelNd152KUAgoC-WuYJQc1EjGzvya6FiK6R4mOKZp8pz2xNvX9dYSM2KOXClegPF2tM1-PBJ98h'),
	(30, 'aww', 'aww@gmai.com', '$2y$10$qkouR5bmz.MjJ0BLOvPoGeX/743xBQzGGDAaO0H60LBvHrwFxoGTO', 'e0E0nyYiSAGc1adM3S358z:APA91bECA02Uazf4EgsHaImX75xtdcJ74oOciSoQC1gnKze5H9gJHN1qrvg_GvgaMkelNd152KUAgoC-WuYJQc1EjGzvya6FiK6R4mOKZp8pz2xNvX9dYSM2KOXClegPF2tM1-PBJ98h'),
	(31, 'aww', 'aww2@gmail.com', '$2y$10$ZGynyBvoFhfzDFafYy1YcedEtgvJH0FVp48OLeRKnpwiWNi93hRi.', 'e0E0nyYiSAGc1adM3S358z:APA91bECA02Uazf4EgsHaImX75xtdcJ74oOciSoQC1gnKze5H9gJHN1qrvg_GvgaMkelNd152KUAgoC-WuYJQc1EjGzvya6FiK6R4mOKZp8pz2xNvX9dYSM2KOXClegPF2tM1-PBJ98h'),
	(32, 'qwe', 'qwe@mail.com', '$2y$10$th8MbDPnYCTuJ.B95f0hUO.XurfoFviZoxTTV0ELIwQN/2UFYULhC', 'dkwvv1SmSzu7_P8S0VzJHG:APA91bGd0YA-o0VpfjuA4EWWpodip0WNtkA0BpX-yKNmHyQkNJFcTH9E3ouVBDsEb_UfdCJNeXmthfi9N3IIKQVhcUM815lFFSjarnAtadeBaQFgXhhU3lbcCjGy6VupcVJ7uI63A1cZ'),
	(33, 'qwe2', 'qwe2@gmail.com', '$2y$10$y8/am3AL1DknGr1.jR/rYeqhGlM7DGpf.R1T5zBnncWKhxp6ZyoOS', 'fG2lFIDGSIOY4gF7b5OAAH:APA91bGSWxBI2p-bokkbjU3ucHK3EYCWtLAI2nvXCsMTTVu9fq5fvXfIpyt6FboluizeQy8-k4Vk7rXVbr6_cA6-nZ_T3bc9OB6iXhgx_FiRhz_0FQFsCCc48NyIexUFXFdEITWglbWB'),
	(34, 'aku3', 'aku3@gmail.com', '$2y$10$vTQqUt8lGI5HnNqglZ4wEujyf1o1NpI2ekMfTeWHf1Txl8SO330Ue', '234'),
	(35, 'q', 'q@gmail.com', '$2y$10$ryJPqC3jS8JLCBOrt7rtze6RSnvEVyGPNy8svgmkRp8YVGdP7Gfze', 'dQHH4Kc1Ty29AeRebxuJu_:APA91bGIAmO555Q3AWoUX6JVdXj9Vnh84GI1_B5mr38yacRM30c4z9Ua82XNNwGERGpNxRaeEAKVDs0s_Evj-3awCQYtY9DAcU4lVPI13yCFixyi5fxQAcbvJXE1N6drvXxINrXVVgz-'),
	(36, 'q', 'q@q.com', '$2y$10$KBGP3aTxm43/aPkRbwQZD.Me1hAQRpn1TIld5O0CrWGicKRC3vLdu', 'caLJF9qxTtedu-yhklf3KH:APA91bFQExiPz4sYTCXPtJayjWJ28WAGiWIWEqYT_S8bnBrPEhROQ2Hah1wODvO20j6z5a2LvAdKfGPznb_m8kxH2uYRPlqA7ei9-IjyGW8zk4HDyvkMTQU7U_FhyEh0Keb23lvkIr_6');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table db_blanjaa.wishlists
CREATE TABLE IF NOT EXISTS `wishlists` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- Dumping data for table db_blanjaa.wishlists: ~5 rows (approximately)
/*!40000 ALTER TABLE `wishlists` DISABLE KEYS */;
REPLACE INTO `wishlists` (`id`, `product_id`, `user_id`) VALUES
	(41, 9, 14),
	(43, 3, 18),
	(45, 1, 29),
	(47, 1, 36),
	(48, 2, 36);
/*!40000 ALTER TABLE `wishlists` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
