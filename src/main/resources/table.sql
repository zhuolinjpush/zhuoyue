create table amazon_sku(
	`skuid` varchar(32) not null,
	`mainurl` varchar(128) not null,
	`allreviewurl` varchar(256) null,
	`reviewtext` varchar(256) null,
	`reviewscount` int null  default 0,
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(`skuid`)
);
create table amazon_reviewers(
	`reviewerid`  varchar(32) not null,
	`username` varchar(64) null,
	`votestext` varchar(32) null,
	`votesnum` int null default 0,
	`rankingtext` varchar(32) null,
	`rankingnum` int null default 0,
	`recentreview` varchar(32) null,
	`refurl` varchar(256) null,
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(`reviewerid`)
);
create table amazon_sku_reviewer(
	`skuid` varchar(32) not null,
	`reviewerid`  varchar(32) not null,
	`startext` varchar(32) null,
	`starnum` int null default 0,
	`title` varchar(64) null,
	`content` text null,
	`reviewtime` varchar(32) not null,
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	primary key(`skuid`,`reviewerid`,`reviewtime`)
);
create table zoyare_settings(
    `setid` varchar(32) not null,
    `page` varchar(32) not null,
    `note` varchar(50) null,
    `content` text null,
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key(`setid`, `page`)
);