docker run -p 3306:3306 --name mysql04 -e MYSQL_ROOT_PASSWORD=123456 -d mysql

docker run -p 3306:3306 --name mysql8 -v /Users/songrongbin/dockerworkspace/data:/data -e LANG=C.UTF-8 -d mysql

docker run -i -t mysql env LANG=C.UTF-8 /bin/bash

docker exec -it mysql env LANG=C.UTF-8 /bin/bash

locale
locale -a

tree
df -h

docker run -p 3306:3306 --name mysql8 \
-v /Users/songrongbin/dockerworkspace/data:/data \
-e MYSQL_ROOT_PASSWORD=123456 \
-e LANG=C.UTF-8 \
-d mysql

docker container ls
or
docker ps  ### 查看当前运行的容器

docker ps -a  ### 查看所有容器，包括停止的
docker ps -l ### 查看最新创建的容器，只列出最后创建的
docker ps -n=2   ### -n=x选项，会列出最后创建的x个容器

docker start container_name
or
docker start container_id

docker stop [CONTAINER NAME]/[CONTAINER ID]  ###将容器退出
docker kill [CONTAINER NAME]/[CONTAINER ID]  ###强制停止一个容器

docker rm [CONTAINER NAME]/[CONTAINER ID]    ###不能够删除一个正在运行的容器，会报错。需要先停止容器

docker rm 'docker ps -a -q'： ### -a标志列出所有容器，-q标志只列出容器的ID，然后传递给rm命令，依次删除容器

docker exec -it mysql8 env LANG=C.UTF-8 /bin/bash

mysql -uroot -p123456

SHOW VARIABLES LIKE 'character_set_%';

SHOW VARIABLES LIKE 'char%';

SHOW VARIABLES LIKE 'collation_%';


docker images

docker ps

docker exec -it mysql – env LANG=C.UTF-8 /bin/bash



//启动
kubectl run -it –rm –image=mysql mysql-client – env LANG=C.UTF-8 /bin/bash

//进入pod
kubectl exec -ti mysql-client – env LANG=C.UTF-8 /bin/bash

select host,user,password from user;

select host, user, authentication_string, plugin from user

GRANT ALL ON *.* TO 'root'@'%';

flush privileges;

ALTER USER 'root'@'localhost' IDENTIFIED BY '123456' PASSWORD EXPIRE NEVER; 

ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';

FLUSH PRIVILEGES; 

show databases;

use springboot_jpa

show tables;

create database springboot_jpa character set utf8;

CREATE DATABASE springboot_jpa
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

create database springboot_jpa;

mysqldump -h127.0.0.1 -uroot -p123456 springboot_jpa > springboot_jpa.sql



-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: springboot_jpa
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auth_user`
--

create database springboot_jpa;

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user` (
  `id` bigint NOT NULL,
  `account` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `pwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (2,'renwox','任我行','123456'),(4,'linghuc','令狐冲','123456');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `part` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL,
  `createPerson` varchar(20) NOT NULL,
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (1,'part1','part1','2017-10-16 09:37:13');
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `age` bigint NOT NULL,
  `pesel` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'andybin','song',2,'male');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_code` varchar(20) DEFAULT NULL,
  `permission_name` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission_type` int NOT NULL DEFAULT '0',
  `sort` int NOT NULL DEFAULT '0',
  `level` int NOT NULL DEFAULT '0',
  `pid` int NOT NULL,
  `operator_id` bigint NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'sys:user:view','sys:user:view','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(2,'sys:user:add','sys:user:add','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(3,'sys:user:edit','sys:user:edit','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(4,'sys:user:delete','sys:user:delete','',0,0,0,0,1,0,'2020-09-07 15:30:59','2020-09-07 15:30:59'),(5,'sys:role:view','sys:role:view','',0,0,0,0,1,0,'2020-09-07 15:31:01','2020-09-07 15:31:01'),(6,'sys:role:edit','sys:role:edit',NULL,1,1,1,1,0,0,'2020-09-09 00:18:29','2020-09-09 00:18:29');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `product_title` varchar(255) NOT NULL,
  `category_id` int NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES (1,'book1','book1',1,0,'2020-09-09 00:31:32','2020-09-09 00:31:32'),(2,'book2','book2',1,0,'2020-09-09 00:31:44','2020-09-09 00:31:44');
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `operator_id` bigint NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'ADMIN','ADMIN',1,0,'2020-09-06 10:01:18','2020-09-06 10:03:06'),(2,'stocker','stocker',0,0,'2020-09-09 00:07:12','2020-09-09 00:07:12'),(3,'saler','saler',0,0,'2020-09-09 00:08:01','2020-09-09 00:08:01'),(4,'faker','faker',0,0,'2020-09-09 00:20:27','2020-09-09 00:20:27');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (1,1,1);
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_code` varchar(50) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `operator_id` bigint NOT NULL,
  `is_del` int NOT NULL DEFAULT '0' COMMENT ' 0 1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','admin','$2a$10$JHz4wZFnISYHtF4Z2ksIVORtbFNcXhhCKlEgNpEyuAY5d2RjKn2m.',1,0,'2020-09-08 23:55:13','2020-09-08 23:55:13'),(2,'stocker','stocker','$2a$10$x9ZurZSjjBG5KR.h9BVTPuTN8.8eiPMnfbBSqJt3PNwNuagRhFjuu',0,0,'2020-09-08 23:56:08','2020-09-08 23:56:08');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1,1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_frozen`
--

DROP TABLE IF EXISTS `user_frozen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_frozen` (
  `jobId` varchar(255) NOT NULL COMMENT 'jobId',
  `recoveryDateLong` bigint NOT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_frozen`
--

LOCK TABLES `user_frozen` WRITE;
/*!40000 ALTER TABLE `user_frozen` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_frozen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `jobId` varchar(25) NOT NULL,
  `cardId` varchar(18) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` int NOT NULL,
  `part` int NOT NULL,
  `ggroup` int NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `addr` varchar(20) DEFAULT NULL,
  `kind` int NOT NULL,
  `joinTime` datetime NOT NULL,
  `password` varchar(32) NOT NULL,
  `status` int NOT NULL,
  `errorTimes` int NOT NULL,
  `post` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('0000','0000','',0,1,1,NULL,NULL,NULL,0,'2000-01-01 00:00:00','0',2,0,NULL),('1000','211103','',0,1,2,NULL,NULL,NULL,0,'2000-01-01 00:00:00','628fee503e90dc1d6c257a4ab6378cac',1,0,NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-14 23:09:11