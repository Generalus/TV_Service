CREATE TABLE `account` (
  `LOGIN` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `AREA_ID` bigint(20) DEFAULT NULL,
  `FIRST_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD_HASH` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `OFFERING_ID` bigint(20) NOT NULL,
  `LOGIN` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKau464m0qiekjh03042q77w2ut` (`LOGIN`),
  CONSTRAINT `FKau464m0qiekjh03042q77w2ut` FOREIGN KEY (`LOGIN`) REFERENCES `account` (`LOGIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

