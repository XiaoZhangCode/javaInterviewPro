
-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, 'JavaScript 变量提升', '请解释 JavaScript 中的变量提升现象。', '[\"JavaScript\", \"基础\"]', '变量提升是指在 JavaScript 中，变量声明会被提升到作用域的顶部。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (2, 'CSS Flexbox 布局', '如何使用 CSS 实现一个水平居中的盒子？', '[\"CSS\", \"布局\"]', '可以使用 Flexbox 布局，通过设置父容器 display 为 flex，并使用 justify-content: center; 对齐子元素。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (3, 'HTML 中的语义化', '什么是 HTML 的语义化？为什么重要？', '[\"HTML\", \"语义化\"]', 'HTML 语义化是使用正确的标签来描述内容的意义，能够提高可访问性和 SEO 效果。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (4, 'React 中的状态管理', '如何在 React 中管理组件状态？', '[\"React\", \"状态管理\"]', '可以使用 React 的 useState 或 useReducer 钩子来管理组件状态，或使用 Redux 进行全局状态管理。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (5, '算法：二分查找', '请实现一个二分查找算法。', '[\"算法\", \"数据结构\"]', '二分查找是一种在有序数组中查找特定元素的算法，通过不断折半的方式缩小查找范围。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (6, '数据库索引的作用', '什么是数据库索引？它的作用是什么？', '[\"数据库\", \"索引\"]', '数据库索引是用于加快查询速度的数据结构，它通过优化查找路径减少查询时间。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (7, 'HTTP 与 HTTPS 的区别', '请解释 HTTP 和 HTTPS 之间的主要区别。', '[\"网络\", \"协议\"]', 'HTTPS 是加密的 HTTP，通过 SSL/TLS 提供更安全的数据传输。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (8, '设计模式：单例模式', '请解释单例模式的实现及应用场景。', '[\"设计模式\", \"单例\"]', '单例模式确保一个类只有一个实例，并提供全局访问点。常用于配置类等只需一个实例的场景。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (9, 'Git 中的分支管理', '如何在 Git 中管理分支？', '[\"版本控制\", \"Git\"]', 'Git 中通过 branch 命令创建分支，使用 checkout 切换分支，使用 merge 合并分支。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (10, 'Docker 的基本命令', '列举并解释几个常用的 Docker 命令。', '[\"容器技术\", \"Docker\"]', '常用命令包括 docker run, docker build, docker ps, docker stop 等。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (11, '前端性能优化', '列举几个前端性能优化的手段。', '[\"前端\", \"性能优化\"]', '包括代码分割、资源压缩、缓存策略、懒加载等。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (12, 'JavaScript 闭包的应用', '什么是闭包？举例说明闭包的实际应用。', '[\"JavaScript\", \"高级\"]', '闭包是指函数能够记住创建时的上下文环境，常用于数据隐藏和模块化编程。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (13, '数据库事务的特性', '请解释数据库事务的 ACID 特性。', '[\"数据库\", \"事务\"]', 'ACID 代表原子性、一致性、隔离性和持久性，是事务处理的四大特性。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (14, 'CSS 的 BEM 命名规范', '什么是 BEM？如何使用 BEM 进行 CSS 命名？', '[\"CSS\", \"命名规范\"]', 'BEM 代表块（Block）、元素（Element）和修饰符（Modifier），是一种 CSS 命名规范。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (15, 'JavaScript 原型链', '请解释 JavaScript 中的原型链机制。', '[\"JavaScript\", \"原型链\"]', '原型链是 JavaScript 实现继承的机制，对象通过原型链可以继承其他对象的属性和方法。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (16, 'React 生命周期', '请说明 React 组件的生命周期方法。', '[\"React\", \"生命周期\"]', 'React 组件的生命周期包括初始化、更新和卸载三个阶段，各阶段有不同的生命周期方法。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (17, 'HTTP 状态码 404 与 500 的区别', '请解释 HTTP 状态码 404 和 500 的含义。', '[\"网络\", \"HTTP\"]', '404 表示未找到资源，500 表示服务器内部错误。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (18, 'Python 与 Java 的区别', '比较 Python 和 Java 的主要区别。', '[\"编程语言\", \"Python\", \"Java\"]', 'Python 是动态类型语言，语法简洁，而 Java 是静态类型语言，注重严谨性和性能。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (19, 'Vue 的双向数据绑定', '请解释 Vue.js 是如何实现双向数据绑定的。', '[\"Vue\", \"数据绑定\"]', 'Vue.js 通过数据劫持和发布-订阅模式实现了双向数据绑定。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');
INSERT INTO `question` VALUES (20, '前端工程化的意义', '为什么需要前端工程化？', '[\"前端\", \"工程化\"]', '前端工程化能够提高开发效率、代码质量和可维护性，规范开发流程。', NULL, 1, '审核通过', 5, '2025-04-09 19:15:09', 0, 0, 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:09', b'0');

-- ----------------------------
-- Records of question_bank
-- ----------------------------
INSERT INTO `question_bank` VALUES (1, 'JavaScript 基础', '包含 JavaScript 的基础知识题目', '', 1, '通过', 5, '2025-04-09 19:31:03', 0, 0, '1', '2025-04-09 19:08:15', '5', '2025-04-09 19:31:03', b'0');
INSERT INTO `question_bank` VALUES (2, 'CSS 样式', '包含 CSS 相关的样式问题', 'https://pic.code-nav.cn/mianshiya/question_bank_picture/1777886594896760834/QatnFmEN_CSS.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (3, 'HTML 基础', 'HTML 标记语言的基本知识', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (4, '前端框架', 'React, Vue, Angular 等框架相关的题目', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (5, '算法与数据结构', '数据结构和算法题目', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (6, '数据库原理', 'SQL 语句和数据库设计', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (7, '操作系统', '操作系统的基本概念', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (8, '网络协议', 'HTTP, TCP/IP 等网络协议题目', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (9, '设计模式', '常见设计模式及其应用', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (10, '编程语言概述', '多种编程语言的基础知识', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (11, '版本控制', 'Git 和 SVN 的使用', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (12, '安全与加密', '网络安全和加密技术', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (13, '云计算', '云服务和架构', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (14, '微服务架构', '微服务的设计与实现', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (15, '容器技术', 'Docker 和 Kubernetes 相关知识', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (16, 'DevOps 实践', '持续集成与持续交付', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (17, '数据分析', '数据分析和可视化', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (18, '人工智能', '机器学习与深度学习基础', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '3', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (19, '区块链技术', '区块链的基本原理和应用', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '1', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');
INSERT INTO `question_bank` VALUES (20, '项目管理', '软件开发项目的管理和执行', 'https://www.mianshiya.com/logo.png', 1, '通过', 5, '2025-04-09 19:15:38', 0, 0, '2', '2025-04-09 19:08:15', '', '2025-04-09 19:15:38', b'0');

-- ----------------------------
-- Records of question_bank_question
-- ----------------------------
INSERT INTO `question_bank_question` VALUES (1, 1, 1, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (2, 1, 2, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (3, 1, 3, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (4, 1, 4, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (5, 1, 5, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (6, 1, 6, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (7, 1, 7, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (8, 1, 8, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (9, 1, 9, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (10, 1, 10, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (11, 2, 2, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (12, 2, 14, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (13, 3, 3, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (14, 3, 13, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (15, 4, 4, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (16, 4, 16, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (17, 5, 5, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (18, 5, 18, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (19, 6, 6, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (20, 6, 19, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (21, 7, 7, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (22, 7, 11, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (23, 8, 8, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (24, 8, 10, 0, 2, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (25, 9, 9, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (26, 9, 17, 0, 3, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (27, 10, 12, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');
INSERT INTO `question_bank_question` VALUES (28, 10, 20, 0, 1, '2025-04-09 19:08:15', '2025-04-09 19:08:15');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user1', '2c6632e9199ee6ad1e85041033caba22', 'unionId1', 'mpOpenId1', 'user1', 'https://www.code-nav.cn/logo.png', '喜欢编程的小白', NULL, NULL, NULL, NULL, NULL, 'user', 0, '', '2025-04-09 19:08:15', '', '2025-04-09 19:13:46', b'0');
INSERT INTO `user` VALUES (2, 'user2', '2c6632e9199ee6ad1e85041033caba22', 'unionId2', 'mpOpenId2', 'user2', 'https://www.code-nav.cn/logo.png', '全栈开发工程师', NULL, NULL, NULL, NULL, NULL, 'user', 0, '', '2025-04-09 19:08:15', '', '2025-04-09 19:13:46', b'0');
INSERT INTO `user` VALUES (3, 'user3', '2c6632e9199ee6ad1e85041033caba22', 'unionId3', 'mpOpenId3', 'user3', 'https://www.code-nav.cn/logo.png', '前端爱好者', NULL, NULL, NULL, NULL, NULL, 'user', 0, '', '2025-04-09 19:08:15', '', '2025-04-09 19:13:46', b'0');
INSERT INTO `user` VALUES (4, 'user4', '2c6632e9199ee6ad1e85041033caba22', 'unionId4', 'mpOpenId4', 'user4', 'https://www.code-nav.cn/logo.png', '后端开发工程师', NULL, NULL, NULL, NULL, NULL, 'user', 0, '', '2025-04-09 19:08:15', '', '2025-04-09 19:13:46', b'0');
INSERT INTO `user` VALUES (5, 'xiaozhang', '2c6632e9199ee6ad1e85041033caba22', NULL, NULL, '小张', 'https://www.code-nav.cn/logo.png', '系统管理员', NULL, NULL, NULL, NULL, NULL, 'admin', 0, '', '2025-04-09 19:08:15', '', '2025-04-09 19:13:46', b'0');
