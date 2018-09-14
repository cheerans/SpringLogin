/*
SQLyog Community Edition- MySQL GUI v5.22a
Host - 5.0.19-nt : Database - shopping_cart
*********************************************************************
Server version : 5.0.19-nt
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `shopping_cart`;

USE `shopping_cart`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `adminuser` */
DROP TABLE IF EXISTS `adminuser`;

CREATE TABLE `adminuser` (
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(40) NOT NULL default '',
  `password` varchar(40) NOT NULL default '',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `userid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `adminuser` */
insert  into `adminuser`(`id`,`userid`,`password`) values (1,'admin','password');

/*Table structure for table `category` */
DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `categoryid` int(11) NOT NULL default '0',
  `categoryname` varchar(60) default NULL,
  `parentid` int(11) NOT NULL default '0',
  PRIMARY KEY  (`categoryid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `category` */
insert  into `category`(`categoryid`,`categoryname`,`parentid`) values (1,'Automobiles',0),(2,'Cellular',0),(3,'Sport',0),(4,'AudioVideo',0),(5,'Beverages',0),(8,'Mercedes',1),(9,'Toyota',1),(12,'In-line skates',3),(13,'Snowboards',3),(14,'Samsung',2),(15,'Ericsson',2),(17,'Nokia',2),(18,'Siemens',2),(20,'DVD',4),(21,'mp3-players',4),(23,'Books',0),(24,'Lexus',1),(25,'Clothes',0),(27,'Computers',0),(38,'Winter',25),(39,'Summer',25),(40,'Home',25),(46,'Alcohol',5),(47,'Alcohol free',5),(51,'Notebooks',27),(57,'PDA',27),(58,'Personal',27),(63,'Lotus',1),(64,'Lamborghini',1),(71,'Harry Potter',23),(72,'Design',23),(73,'Programming',23),(74,'Classic',25),(75,'Multimedia',27),(76,'Home theatres',4),(77,'Bycicles',3),(78,'new  category',0),(79,'new subcategory',78);

/*Table structure for table `stores` */
DROP TABLE IF EXISTS `stores`;

CREATE TABLE `stores` (
  `id` int(11) NOT NULL default '0',
  `storename` varchar(50) NOT NULL default '',
  `storeurl` varchar(50) NOT NULL default '',
  `ordernoticeemail` varchar(50) NOT NULL default '',
  `cursymbol` varchar(6) NOT NULL default '',
  `curcode` varchar(6) NOT NULL default '',
  `aboutustext` text NOT NULL,
  `shipdeltext` text NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `stores` */
insert  into `stores`(`id`,`storename`,`storeurl`,`ordernoticeemail`,`cursymbol`,`curcode`,`aboutustext`,`shipdeltext`) values (1,'My Shopping Cart','http://www.mycompany.com','notice@mycompany.com','$','USD','Rose India shopping cart simple project','Here you can provide information about shipping and payment options.This HTML text can be edited in the administrative mode.');

/*Table structure for table `orderedcarts` */
DROP TABLE IF EXISTS `orderedcarts`;

CREATE TABLE `orderedcarts` (
  `productid` int(11) NOT NULL default '0',
  `orderid` int(11) NOT NULL default '0',
  `productname` char(255) default NULL,
  `price` float default NULL,
  `quantity` int(11) default NULL,
  PRIMARY KEY  (`orderid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `orderedcarts` */
insert  into `orderedcarts`(`productid`,`orderid`,`productname`,`price`,`quantity`) values (1,1,'Toyota Land Cruiser 100',70000,2);

/*Table structure for table `orders` */
DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL auto_increment,
  `ordertime` datetime default NULL,
  `custfirstname` varchar(30) default NULL,
  `custlastname` varchar(30) default NULL,
  `custemail` varchar(30) default NULL,
  `custcountry` varchar(30) default NULL,
  `custzip` varchar(30) default NULL,
  `custstate` varchar(30) default NULL,
  `custcity` varchar(30) default NULL,
  `custaddress` varchar(30) default NULL,
  `custphone` varchar(30) default NULL,
  `custfax` int(11) default NULL,
  `creditCardType` varchar(30) NOT NULL default '',
  `cardHolderName` varchar(30) NOT NULL default '',
  `creditCardNumber` varchar(20) NOT NULL default '',
  `expirationDate` date NOT NULL default '0000-00-00',
  `customerNotes` varchar(255) NOT NULL default '',
  `cvvNumber` int(11) NOT NULL default '0',
  PRIMARY KEY  (`orderid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `orders` */
insert  into `orders`(`orderid`,`ordertime`,`custfirstname`,`custlastname`,`custemail`,`custcountry`,`custzip`,`custstate`,`custcity`,`custaddress`,`custphone`,`custfax`,`creditCardType`,`cardHolderName`,`creditCardNumber`,`expirationDate`,`customerNotes`,`cvvNumber`) values (1,'2006-12-12 09:38:18','sushil','pal','pal@yahoo.com','india','110085','delhi','delhi','rohini sec-3','764584',4536789,'','','','0000-00-00','',0),(5,NULL,'1','1','1','1','1','1','1','1','1',1,'1','1','1','2010-06-01','1',1),(6,'2006-12-12 00:00:00','1','1','1','1','1','1','1','1','1',1,'1','1','1','2010-06-01','1',1),(7,'2006-12-12 00:00:00','sushil','pal','pal@yahoo.com','india','110085','delhi','delhi','rohini sec-8','456789',543673,'','sushil','456789987654','2010-06-01','regular customer',435),(8,'2006-12-12 00:00:00','vinod','kumar','pal@yahoo.com','india','110085','delhi','delhi','rohini sec-8','456789',543673,'VISA','vinod','456789987609','2010-06-01','regular customer',435),(9,'2006-12-12 00:00:00','amit','kumar','amit@yahoo.com','ghhdhdhd','54654654','gdsg','gdsfg','gdgfdgfdg','3245454',5454554,'VISA','dvdfdgfd','54654654','2010-06-01',' klgjd gdfg gdd d\r\ng lkgj gdfklgj dfg\r\nd fkjfdj',534534),(10,'2006-12-12 00:00:00','deepak','mohan','dd@yahoo.com','grggdgdfd gdfgdf','2423423','ertete','ertret','jhghk  jkf hhd fgdfh df\r\n dfhs','546576887',87786478,'MC','deepak','4343344334344334','2010-06-01','r sj  vh bfdh dfv\r\n fdsa df hdfg hdg f',345),(11,'2007-06-23 19:01:12','ravi','kant','dvs','345353','53543','dgfdgdfg',' gfdgdfgdfg','fgd gdgdgdfg','443242',24324324,'MC','gggdfdgdgdf','53535345434','2010-06-01','jr kd bk hbd\r\n hjsf hsfjsh fsdf',546),(12,'2007-06-23 19:04:46','ravi','kant','dvs','345353','53543','dgfdgdfg',' gfdgdfgdfg','fgd gdgdgdfg','443242',24324324,'MC','gggdfdgdgdf','53535345434','2010-06-01','jr kd bk hbd\r\n hjsf hsfjsh fsdf',546),
(13,'2007-06-23 19:10:38','sushil','pal','fsdsfs','fgdgdg','5353534','fgdg','dfgdgd','5rdgtrdgdfd','54353',34535,'MC','retgetet','5353453','2010-06-01','tert erter e',545),
(19,'2007-06-28 14:30:54','sdfg','fg','dg@wf.efg','234','324','234','342','2342','23423525',0,'ABC','234','2424242424242444','2007-02-09','234',23);

/*Table structure for table `product` */
DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `productid` int(11) NOT NULL auto_increment,
  `categoryid` int(11) NOT NULL default '0',
  `productname` varchar(60) NOT NULL default '',
  `productprice` varchar(40) NOT NULL default '',
  `listprice` varchar(20) default NULL,
  `imagename` varchar(60) default NULL,
  `quantity` varchar(11) NOT NULL default '',
  `description` varchar(80) default NULL,
  `briefdisc` text,
  PRIMARY KEY  (`productid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `product` */
insert  into `product`(`productid`,`categoryid`,`productname`,`productprice`,`listprice`,`imagename`,`quantity`,`description`,`briefdisc`) values (1,9,'Toyota Land Cruiser 100','70000','','ToyotaLandCruiser100.jpg','2','',''),(2,9,'Toyota-98','80000','','','','',NULL),(3,8,'Mercedes S600','85990','87900','MercedesS600.jpg','2','Mercedes','Mercedes'),(4,24,'Lexus GS300','4900','5100','LexusGS300.jpg','2','',''),(5,24,'Lexus RX300','52000','','LexusRX300.jpg','2','',''),(6,63,'Lotus Esprit','72000','80000','LotusEsprit.jpg','2','',''),(7,64,'Lamborghini Diablo','190000','230000','LamborghiniDiablo.jpg','2','',''),(8,14,'Samsung R200','135','','','5','',''),(9,14,'Samsung N100','157','','','','',NULL),(10,14,'Samsung A100','187','','','15','',''),(11,14,'Samsung A300','249.99','','','11','','The premium SGH-A300, designed to succeed the SGH-A100 and A110, is targeted for people who lead in fashion rather than follow.'),(12,15,'Ericsson T29','173','','EricssonT29.jpg','10','',''),(13,15,'Ericsson T39','266','','EricssonT39.jpg','10','',''),(14,15,'Ericsson T60','345','380','EricssonT60.jpg','20','',''),(15,14,'Samsung R20','125','130','','10','',''),(16,14,'Samsung-n30','200','225','','12','',''),(22,74,'Classic01','50','57','','10','',''),(27,20,'The Last Kiss','15.30','20.00','thelastkiss.jpg','5','',''),(28,20,'Black Dahlia, The (Widescreen)','21.73','29.98','black_dahlia.jpg','10','',''),(29,20,'Step Up(Widescreen)','23.42','29.99','stepup.jpg','20','',''),(30,20,'Woh Lamhe','22.99','25.30','wohlamhe.jpg','20','',''),(31,20,'2 Hot 2 Handle','12.99','14.99','hot_handle.jpg','20','',''),(32,76,'JVC LT-37X987 LCD HDTV','5000','8000','jvclcdtv.jpg','2','',''),(33,21,'Apple iPod Nano (2GB, black)','129','135','Apple_ iPod_Nano.gif','20','',''),(34,20,'GURU','9.00','10.00','guruh061106_3.jpg','20','',''),(35,17,'Nokia 6600','250','280','nokia_6600.jpg','40','',''),(36,17,'Nokia 3230','250','280','nokia3230.jpg','10','',''),(37,17,'Nokia N90','300','315','nokian-90.jpg','5','',''),(38,17,'Nokia N91','625','700','nokia-N91.jpg','5','',''),(39,40,'Sweeter','47','55','480_095_small.jpg','10','',''),(40,39,'Pants','19','25','','5','',''),(41,38,'Pants-Winter','21','25','','10','',''),(42,51,'Fujitsu Siemens LifeBook P-111','1503','2000','','5','',''),(43,51,'IBM ThinkPad T30 2366-89G','3170','3500','','5','',''),(44,57,'3Com Palm m130','250','280','','5','',''),(45,57,'Compaq iPAQ H3760','365','377','','5','','3.8’’, 240 õ 320, 206MHz, 64MB, 32MB ROM, MS Pocket PC 2002'),(46,58,'HP Compaq Evo D310','1207','1300','','5','','P-IV 2.4GHz, 256MB PC2100 DDR SDRAM, 40GB, CD-RW, Direct AGP, 10/100 Eth, WinXP Pro'),(47,58,'IBM NetVista M42','1152','','','5','','P-IV 2.0GHz, 256MB PC2100 DDR SDRAM, 40GB, CD-ROM, AGP, 10/100 Eth, WinXP Pro'),(48,12,'Rollerblade TRS Access','200','225','','10','',''),(49,12,'Roces Majestic 12','149','200','','10','',''),(50,13,'BURTON Balance','640','650','','10','',''),(51,13,'BURTON Cruzer','345','380','','5','',''),(52,13,'BURTON Power','659','700','','5','',''),(53,12,'Roces Khuti','179','200','','5','',''),(54,46,'Rum Captain Morgan','14','15','46_captain_morgan.jpg','20','',''),(55,47,'Coca-Cola','0.99','1.00','732_469.jpg','50','',''),(56,46,'Budwieser','0.39','0.59','','50','',''),(57,71,'Harry Potter book #1','5.0','8.0','15794s.jpg','20','','Harry Potter has never played a sport while flying on a broomstick. He`s never worn a cloak of invisibility, befriended a giant, or helped hatch a dragon...'),(58,71,'Harry Potter book #2','5.0','7.0','15795s.jpg','25','','The Dursleys were so mean and hideous that summer that all Harry Potter wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. '),(59,71,'Harry Potter. Magic Eye. Album','3.0','5.0','','20','',''),(60,73,'Professional PHP programming','15','17','8472s.jpg','10','','PHP is a new technology that is growing fast...'),(61,73,'Visual C++ and MFC','17','20','4064s.jpg','10','','Tap into a collection of programming solutions that use the Microsoft Foundation Class(MFC) library...'),(62,72,'Stiv Krug :: Web-design ``Don`','8.0','10.0','10805s.jpg','15','','Exactly how do you deliver great web usability?...'),(63,18,'Siemens SL45','240','250','720_ssl45_small.jpg','10','','What if a phone is also an MP3 player, a digital voice recorder, an organizer? Can it still be called a phone? '),(64,18,'Siemens ME45','219','250','532_sme45.jpg','10','','With the ME45 you have it right in your hand. Voice direction and dialling, flexible memorymanagement for total personalisation, easy and fast calendar synchronisation with Microsoft Outlook, GPRS for really fast net action via your notebook or WAP.'),(65,18,'Siemens M35i','95.0','98.0','366_sm35_small.jpg','5','','he M35i gives you mobile web access, Personal Messaging with pictures, Intelligent Typing, and even allows the occasional game whenever life slows down..'),(66,75,'BOSE Companion 3 Three Piece Multimedia Computer Speaker Sys','249','250','77861534.jpg','5','',''),(67,75,'Ultra USB 2.0 WEB CAM Video Camera','63.05','65.00','68886096.jpg','15','','Creative Labs have created the WebCam Live! Ultra . You can place this camera almost anywhere, such as any flat surface, flat-panel and notebook displays due to the camera\'s built-in flexible four-way base. '),(68,77,'Dahon Vitesse D7 with Cyclone Electric Kit','800','805','t_21179.jpg','10','','If your shopping for a electric power-assisted bike, then you simply must check this out!'),(69,77,'Felt QX60 - 2007 Model','450','455','t_21835.jpg','10','','Aluminium hybrid bike, by Felt.'),(70,79,'new product','354','425','','5','','');

/*Table structure for table `user` */
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(30) NOT NULL default '',
  `storeid` int(11) NOT NULL default '0',
  `password` varchar(128) NOT NULL default '',
  `firstname` varchar(128) default NULL,
  `lastname` varchar(128) default null,
  `email` varchar(128) default NULL,
  `country` varchar(128) default NULL,
  `zip` varchar(128) default NULL,
  `state` varchar(128) default NULL,
  `city` varchar(128) default NULL,
  `address` varchar(1024) default NULL,
  `phone` varchar(128) default NULL,
  `fax` varchar(128) default NULL,
  `salt` varchar(128) default '12345678',
  `roles` varchar(128) default null,
  PRIMARY KEY  (`id`),
  FOREIGN KEY (`storeid`) REFERENCES stores(`id`),
  UNIQUE KEY `userid` (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `user` */
insert  into 
`user`(`userid`,`storeid`,`password`,`firstname`,`lastname`,`email`,`country`,`zip`,`state`,`city`,`address`,`phone`,`fax`) 
values 
('amar',1,'a7c2cb1368f6955dc9abe918940241c043911737','amar','patel','patel@yahoo.com','india','54354','delhi','delhia','sdfgfdjh djg jfd','564654',65),
('amar12',1,'amar','amar','patel','patel@yahoo.com','india','54354','delhi','delhia','sdfgfdjh djg jfd','564654',65),
('amar122',1,'amar','amar','patel','patel@yahoo.com','india','54354','delhi','delhia','sdfgfdjh djg jfd','564654',65),
('amar1222',1,'amar','amar','patel','patel@yahoo.com','india','54354','delhi','delhia','sdfgfdjh djg jfd','564654',65),
('amar11',1,'amar','amar','patel','patel@yahoo.com','delhi','564378','delhi','delhi','128,G-26,rohini sec-3','3453537',3472826),
('vinod',1,'vinod','vinod','Kumar','kumar@yahoo.com','delhi','5455343','delhi','delhi','rohini sec-3','3424243223',4322432);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;/*!40101 SET SQL_MODE=@OLD_SQL_MODE */