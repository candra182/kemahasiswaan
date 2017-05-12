/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.27 : Database - kemahasiswaan_10113112_10113135
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kemahasiswaan_10113112_10113135` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `kemahasiswaan_10113112_10113135`;

/*Table structure for table `t_mahasiswa` */

DROP TABLE IF EXISTS `t_mahasiswa`;

CREATE TABLE `t_mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `ttl` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `alamat` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`nim`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_mahasiswa` */

insert  into `t_mahasiswa`(`nim`,`nama`,`ttl`,`tgl_lahir`,`alamat`) values ('10111317','Rian','Sumedang','1991-03-10','Rancakalong'),('10113103','Panji','Majalengka','1995-04-08','Majalengka'),('10113112','Eggy Sudianto','Bandung','1995-12-12','Bandung'),('10113134','Natsu','Sumedang','1993-07-29','Jl. Ahmad Yani'),('10113135','Candra Nova N','Sumedang','1994-11-28','Jl. Sekeloa');

/*Table structure for table `t_mata_kuliah` */

DROP TABLE IF EXISTS `t_mata_kuliah`;

CREATE TABLE `t_mata_kuliah` (
  `kd_mk` varchar(8) NOT NULL,
  `nama_mk` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kd_mk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_mata_kuliah` */

insert  into `t_mata_kuliah`(`kd_mk`,`nama_mk`) values ('IF33219','Sistem Berkas'),('IF33347','Pemrograman Dasar'),('IF37325P','Komputer Grafik'),('IF99191','Algoritma'),('IF99192','Teorema Bahasa');

/*Table structure for table `t_nilai` */

DROP TABLE IF EXISTS `t_nilai`;

CREATE TABLE `t_nilai` (
  `kd_nilai` int(11) NOT NULL AUTO_INCREMENT,
  `nim` varchar(8) DEFAULT NULL,
  `kd_mk` varchar(8) DEFAULT NULL,
  `absensi` int(11) DEFAULT NULL,
  `tgs1` double DEFAULT NULL,
  `tgs2` double DEFAULT NULL,
  `tgs3` double DEFAULT NULL,
  `uts` double DEFAULT NULL,
  `uas` double DEFAULT NULL,
  `nilai_absen` double DEFAULT NULL,
  `nilai_tugas` double DEFAULT NULL,
  `nilai_uts` double DEFAULT NULL,
  `nilai_uas` double DEFAULT NULL,
  `nilai_akhir` double DEFAULT NULL,
  `indeks` char(1) DEFAULT NULL,
  `ket` varchar(12) DEFAULT NULL,
  `angkatan` int(4) DEFAULT NULL,
  PRIMARY KEY (`kd_nilai`),
  KEY `nim` (`nim`),
  KEY `kd_mk` (`kd_mk`),
  CONSTRAINT `kd_mk` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nim` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `t_nilai` */

insert  into `t_nilai`(`kd_nilai`,`nim`,`kd_mk`,`absensi`,`tgs1`,`tgs2`,`tgs3`,`uts`,`uas`,`nilai_absen`,`nilai_tugas`,`nilai_uts`,`nilai_uas`,`nilai_akhir`,`indeks`,`ket`,`angkatan`) values (21,'222222','IF37325P',1,1,1,1,1,1,0.36,0.25,0.3,0.4,1.31,'E','TIDAK LULUS',2015),(24,'10113110','IF37325P',14,1,1,1,1,1,5,0.25,0.3,0.4,5.95,'E','TIDAK LULUS',2015),(25,'10113110','IF37325P',14,1,1,1,1,1,5,0.25,0.3,0.4,5.95,'E','TIDAK LULUS',2015),(26,'10113112','IF37325P',13,90,90,90,90,90,4.64,22.5,27,36,90.14,'A','LULUS',2010),(27,'10113135','IF99191',13,100,100,100,100,100,4.64,25,30,40,99.64,'A','LULUS',2016),(28,'10111317','IF99192',13,75,75,75,75,78,4.64,18.75,22.5,31.2,77.09,'B','LULUS',2015),(29,'10113103','IF99191',13,60,66,67,80,76,4.64,16.08,24,30.4,75.13,'B','LULUS',2014);

/*Table structure for table `t_pengguna` */

DROP TABLE IF EXISTS `t_pengguna`;

CREATE TABLE `t_pengguna` (
  `nama_lengkap` varchar(50) DEFAULT NULL,
  `pengguna` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `tgl` date DEFAULT NULL,
  `jml_error` int(1) DEFAULT NULL,
  `pertanyaan_keamanan` varchar(50) DEFAULT NULL,
  `jawaban` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pengguna`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_pengguna` */

insert  into `t_pengguna`(`nama_lengkap`,`pengguna`,`password`,`tgl`,`jml_error`,`pertanyaan_keamanan`,`jawaban`) values ('Admin','Admin','admin','2015-07-05',0,'Apa warna favorit  kamu?','Merah'),('Candra Nova Nurdiansyah','Candra','10113135','2015-07-05',0,NULL,NULL),('Eggy Sudianto','Eggy','12345','2015-07-05',0,'Dimanakah tempat lahir kamu?','Bandung'),('Nova Nurdiansyah','Nova','Blink182','2015-07-04',2,'Apa Nama Team Favorit Kamu ?','Barcelona'),('Rian Saeuloh','Rian','23452345','2015-07-05',0,'Dari mana asal sekolah kamu?','Tanjungsari');

/*Table structure for table `t_simulasi_nilai` */

DROP TABLE IF EXISTS `t_simulasi_nilai`;

CREATE TABLE `t_simulasi_nilai` (
  `kd_s_nilai` int(11) NOT NULL AUTO_INCREMENT,
  `kd_mk` varchar(8) DEFAULT NULL,
  `p_kehadiran` int(11) DEFAULT NULL,
  `p_tugas` double DEFAULT NULL,
  `p_uts` double DEFAULT NULL,
  `p_uas` double DEFAULT NULL,
  `absensi` int(11) DEFAULT NULL,
  `tgs1` double DEFAULT NULL,
  `tgs2` double DEFAULT NULL,
  `tgs3` double DEFAULT NULL,
  `uts` double DEFAULT NULL,
  `uas` double DEFAULT NULL,
  `nilai_absen` double DEFAULT NULL,
  `nilai_tugas` double DEFAULT NULL,
  `nilai_uts` double DEFAULT NULL,
  `nilai_uas` double DEFAULT NULL,
  `n_akhir` double DEFAULT NULL,
  `indeks` char(1) DEFAULT NULL,
  `keterangan` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`kd_s_nilai`),
  KEY `kd_mk` (`kd_mk`),
  CONSTRAINT `t_simulasi_nilai_ibfk_1` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `t_simulasi_nilai` */

insert  into `t_simulasi_nilai`(`kd_s_nilai`,`kd_mk`,`p_kehadiran`,`p_tugas`,`p_uts`,`p_uas`,`absensi`,`tgs1`,`tgs2`,`tgs3`,`uts`,`uas`,`nilai_absen`,`nilai_tugas`,`nilai_uts`,`nilai_uas`,`n_akhir`,`indeks`,`keterangan`) values (7,'IF37325P',25,25,25,25,14,100,100,100,100,100,25,25,25,25,100,'A','LULUS'),(8,'IF99191',10,20,30,40,14,100,100,100,100,100,10,20,30,40,100,'A','LULUS');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
