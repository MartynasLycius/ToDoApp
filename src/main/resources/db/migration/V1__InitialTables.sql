drop table if exists todoitem;

CREATE TABLE `todoitem` (`id` bigint NOT NULL,
                            `date` datetime(6) DEFAULT NULL,
                            `description` text NOT NULL,
                            `status` varchar(30) NOT NULL,
                            `title` varchar(30) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
