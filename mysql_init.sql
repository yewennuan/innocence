

CREATE TABLE `tb_91porn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL DEFAULT '',
  `pre_url` varchar(200) NOT NULL DEFAULT '',
  `url` varchar(200) NOT NULL DEFAULT '',
  `title` varchar(100) NOT NULL DEFAULT '',
  `status` tinyint(4) NOT NULL DEFAULT '0',
  `duration` varchar(20) NOT NULL DEFAULT '',
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `reset_times` tinyint(4) NOT NULL DEFAULT '0',
  `remark` varchar(100) NOT NULL DEFAULT '',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_1` (`title`,`duration`),
  UNIQUE KEY `uidx_pre_url` (`pre_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_91porn_video_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `url` varchar(200) NOT NULL,
  `name` varchar(20) NOT NULL,
  `page_no` int(11) NOT NULL DEFAULT '0',
  `max_page_no` int(11) NOT NULL DEFAULT '-1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('hot', 'http://91porn.com/v.php?category=hot&viewtype=basic', '当前最热');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('rp', 'http://91porn.com/v.php?category=rp&viewtype=basic', '最近得分');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('long', 'http://91porn.com/v.php?category=long&viewtype=basic', '10分钟以上');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('md', 'http://91porn.com/v.php?category=md&viewtype=basic', '本月讨论');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('tf', 'http://91porn.com/v.php?category=tf&viewtype=basic', '本月收藏');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('mf', 'http://91porn.com/v.php?category=mf&viewtype=basic', '收藏最多');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('rf', 'http://91porn.com/v.php?category=rf&viewtype=basic', '最近加精');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('top', 'http://91porn.com/v.php?category=top&viewtype=basic', '本月最热');
INSERT INTO tb_91porn_video_list (code, url, name) VALUES ('hd', 'http://91porn.com/v.php?category=top&m=-1&viewtype=basic', '上月最热');
