-- CREATE DATABASE thriftport;

DROP TABLE IF EXISTS thriftport.listings;

CREATE TABLE thriftport.listings (
  `id` varchar(48) NOT NULL,
  `userId` varchar(48),
  `name` varchar(127) NOT NULL,
  `description` varchar(255),
  `purchaseAmount` decimal(19,4),
  `sellAmount` decimal(19,4),
  `listDate` date,
  `sellDate` date,
PRIMARY KEY (`id`)
);


-- listing_properties