CREATE TABLE `tv_package` (
  `OFFERING_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`OFFERING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `channel` (
  `SOURCE_ID` bigint(20) NOT NULL,
  `CONTENT_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `lineup` (
  `AREA_ID` bigint(20) NOT NULL,
  `NAME` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`AREA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `package_lineup` (
  `PACKAGE` bigint(20) NOT NULL,
  `LINEUP` bigint(20) NOT NULL,
  PRIMARY KEY (`PACKAGE`,`LINEUP`),
  KEY `FK56tyj2klawlr2ace2267wcqjp` (`LINEUP`),
  CONSTRAINT `FK56tyj2klawlr2ace2267wcqjp` FOREIGN KEY (`LINEUP`) REFERENCES `lineup` (`AREA_ID`),
  CONSTRAINT `FKrc214bwaytjm3bh2sb27124cw` FOREIGN KEY (`PACKAGE`) REFERENCES `tv_package` (`OFFERING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `package_channel` (
  `PACKAGE` bigint(20) NOT NULL,
  `CHANNEL` bigint(20) NOT NULL,
  PRIMARY KEY (`PACKAGE`,`CHANNEL`),
  KEY `FK87hfp3xagg8kr0b4bced9hrnl` (`CHANNEL`),
  CONSTRAINT `FK2i330d4mod6iu6faek2w7xnfq` FOREIGN KEY (`PACKAGE`) REFERENCES `tv_package` (`OFFERING_ID`),
  CONSTRAINT `FK87hfp3xagg8kr0b4bced9hrnl` FOREIGN KEY (`CHANNEL`) REFERENCES `channel` (`SOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `lineup_channel` (
  `LINEUP` bigint(20) NOT NULL,
  `CHANNEL` bigint(20) NOT NULL,
  PRIMARY KEY (`LINEUP`,`CHANNEL`),
  KEY `FK8uog8yew0rofskv43nuu5ej5b` (`CHANNEL`),
  CONSTRAINT `FK8uog8yew0rofskv43nuu5ej5b` FOREIGN KEY (`CHANNEL`) REFERENCES `channel` (`SOURCE_ID`),
  CONSTRAINT `FKapqwbr9kfdkyo9nw9u3p8vssc` FOREIGN KEY (`LINEUP`) REFERENCES `lineup` (`AREA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
