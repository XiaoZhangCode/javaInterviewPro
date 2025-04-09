-- 创建库
create database if not exists java_inter_view_pro;

-- 切换库
use java_inter_view_pro;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`         bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `userId`     bigint(20)                                                   NOT NULL COMMENT '评论用户id',
    `questionId` bigint(20)                                                   NOT NULL COMMENT '题目id',
    `content`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci        NOT NULL COMMENT '评论内容',
    `parentId`   bigint(20)                                                   NULL     DEFAULT NULL COMMENT '父评论id，用于回复功能',
    `likeNum`    int(11)                                                      NOT NULL DEFAULT 0 COMMENT '点赞数',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '创建者',
    `createTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '更新者',
    `updateTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`    bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_questionId` (`questionId`) USING BTREE,
    INDEX `idx_userId` (`userId`) USING BTREE,
    INDEX `idx_parentId` (`parentId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1910000237802332162
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '评论表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for favourite
-- ----------------------------
DROP TABLE IF EXISTS `favourite`;
CREATE TABLE `favourite`
(
    `id`         bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    `userId`     bigint(20)                                                   NOT NULL COMMENT '用户id',
    `questionId` bigint(20)                                                   NOT NULL COMMENT '题目id',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '创建者',
    `createTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '更新者',
    `updateTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`    bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_questionId` (`questionId`) USING BTREE,
    INDEX `idx_userId` (`userId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1910000301165682690
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '收藏表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`
(
    `id`            bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '标题',
    `content`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NULL COMMENT '内容',
    `tags`          varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '标签列表（json 数组）',
    `answer`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NULL COMMENT '推荐答案',
    `source`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '题目来源',
    `reviewStatus`  int(11)                                                        NOT NULL DEFAULT 0 COMMENT '状态：0-待审核, 1-通过, 2-拒绝',
    `reviewMessage` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '审核信息',
    `reviewerId`    bigint(20)                                                     NULL     DEFAULT NULL COMMENT '审核人 id',
    `reviewTime`    datetime(0)                                                    NULL     DEFAULT NULL COMMENT '审核时间',
    `needVip`       tinyint(4)                                                     NOT NULL DEFAULT 0 COMMENT '仅会员可见（1 表示仅会员可见）',
    `viewNum`       int(11)                                                        NOT NULL DEFAULT 0 COMMENT '浏览量',
    `thumbNum`      int(11)                                                        NOT NULL DEFAULT 0 COMMENT '点赞数',
    `favourNum`     int(11)                                                        NOT NULL DEFAULT 0 COMMENT '收藏数',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '创建者',
    `createTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '更新者',
    `updateTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`       bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_title` (`title`) USING BTREE,
    INDEX `idx_userId` (`creator`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '题目'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank`
(
    `id`            bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title`         varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '标题',
    `description`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          NULL COMMENT '描述',
    `picture`       varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '图片',
    `reviewStatus`  int(11)                                                        NOT NULL DEFAULT 0 COMMENT '状态：0-待审核, 1-通过, 2-拒绝',
    `reviewMessage` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '审核信息',
    `reviewerId`    bigint(20)                                                     NULL     DEFAULT NULL COMMENT '审核人 id',
    `reviewTime`    datetime(0)                                                    NULL     DEFAULT NULL COMMENT '审核时间',
    `viewNum`       int(11)                                                        NOT NULL DEFAULT 0 COMMENT '浏览量',
    `priority`      int(11)                                                        NOT NULL DEFAULT 0 COMMENT '优先级',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '创建者',
    `createTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '更新者',
    `updateTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`       bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_title` (`title`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 21
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '题库'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for question_bank_question
-- ----------------------------
DROP TABLE IF EXISTS `question_bank_question`;
CREATE TABLE `question_bank_question`
(
    `id`             bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `questionBankId` bigint(20)  NOT NULL COMMENT '题库 id',
    `questionId`     bigint(20)  NOT NULL COMMENT '题目 id',
    `questionOrder`  int(11)     NOT NULL DEFAULT 0 COMMENT '题目顺序（题号）',
    `userId`         bigint(20)  NOT NULL COMMENT '创建用户 id',
    `createTime`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updateTime`     datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `questionBankId` (`questionBankId`, `questionId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '题库题目'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for thumb
-- ----------------------------
DROP TABLE IF EXISTS `thumb`;
CREATE TABLE `thumb`
(
    `id`         bigint(20)                                                   NOT NULL AUTO_INCREMENT COMMENT '点赞id',
    `userId`     bigint(20)                                                   NOT NULL COMMENT '用户id',
    `targetId`   bigint(20)                                                   NOT NULL COMMENT '目标id（题目id或评论id）',
    `targetType` tinyint(4)                                                   NOT NULL COMMENT '目标类型：0-题目，1-评论',
    `creator`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '创建者',
    `createTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT '' COMMENT '更新者',
    `updateTime` datetime(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`    bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_targetId` (`targetId`) USING BTREE,
    INDEX `idx_userId` (`userId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1910000696143290371
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '点赞表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`            bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `userAccount`   varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '账号',
    `userPassword`  varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '密码',
    `unionId`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '微信开放平台id',
    `mpOpenId`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '公众号openId',
    `userName`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '用户昵称',
    `userAvatar`    varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '用户头像',
    `userProfile`   varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '用户简介',
    `vipExpireTime` datetime(0)                                                    NULL     DEFAULT NULL COMMENT '会员过期时间',
    `vipCode`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL     DEFAULT NULL COMMENT '会员兑换码',
    `vipNumber`     bigint(20)                                                     NULL     DEFAULT NULL COMMENT '会员编号',
    `shareCode`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT NULL COMMENT '分享码',
    `inviteUser`    bigint(20)                                                     NULL     DEFAULT NULL COMMENT '邀请用户 id',
    `userRole`      varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/vip',
    `userStatus`    tinyint(4)                                                     NOT NULL DEFAULT 0 COMMENT '用户状态（0正常 1停用）',
    `creator`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '创建者',
    `createTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updater`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci   NULL     DEFAULT '' COMMENT '更新者',
    `updateTime`    datetime(0)                                                    NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted`       bit(1)                                                         NOT NULL DEFAULT b'0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_unionId` (`unionId`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

