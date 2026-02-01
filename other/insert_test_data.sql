-- 模拟数据
-- 在IDEA的Query Console中执行这个！

-- 1. 先禁用外键检查
SET FOREIGN_KEY_CHECKS = 0;

-- 2. 插入用户（使用类似雪花算法的大数字ID）
-- 雪花ID特点：19位，以时间戳开头（如：1725364859200110592）
-- 我们用 1700000000000000001 这样的格式
INSERT INTO user (username, userid, password, avatar, `user-usability`, `user-time-register`) VALUES
                                                                                                  ('张三', 1700000000000000001, MD5('password1'), 'https://avatar.com/zhangsan.jpg', 1, NOW()),
                                                                                                  ('李四', 1700000000000000002, MD5('password2'), 'https://avatar.com/lisi.jpg', 1, NOW()),
                                                                                                  ('王五', 1700000000000000003, MD5('password3'), 'https://avatar.com/wangwu.jpg', 1, NOW()),
                                                                                                  ('电影爱好者', 1700000000000000004, MD5('password4'), 'https://avatar.com/movielover.jpg', 1, NOW()),
                                                                                                  ('编程大神', 1700000000000000005, MD5('password5'), 'https://avatar.com/coder.jpg', 1, NOW());

-- 3. 插入视频（video_id也要用大数字）
INSERT INTO video (user_id, title, video_id, video_url, cover_url, duration, status, `create`, updated, text) VALUES
                                                                                                                  (1700000000000000004, '星际穿越深度解析', 1800000000000000001, 'https://video.com/interstellar.mp4', 'https://cover.com/interstellar.jpg', 960, 2, NOW(), NOW(), '诺兰经典科幻电影解析'),
                                                                                                                  (1700000000000000004, '盗梦空间剧情讲解', 1800000000000000002, 'https://video.com/inception.mp4', 'https://cover.com/inception.jpg', 720, 2, NOW(), NOW(), '烧脑神作解析'),
                                                                                                                  (1700000000000000005, 'Java零基础入门教程', 1800000000000000003, 'https://video.com/java-basic.mp4', 'https://cover.com/java-basic.jpg', 1200, 2, NOW(), NOW(), 'Java编程学习'),
                                                                                                                  (1700000000000000005, 'SpringBoot实战项目', 1800000000000000004, 'https://video.com/springboot.mp4', 'https://cover.com/springboot.jpg', 1800, 2, NOW(), NOW(), 'SpringBoot开发'),
                                                                                                                  (1700000000000000001, '搞笑动物合集', 1800000000000000005, 'https://video.com/funny-animals.mp4', 'https://cover.com/funny-animals.jpg', 300, 2, NOW(), NOW(), '搞笑娱乐视频');

-- 4. 插入统计数据
INSERT INTO stat (video_id, `like`, view, share, updated, comment_count) VALUES
                                                                             (1800000000000000001, 1250, 50000, 300, NOW(), 45),
                                                                             (1800000000000000002, 980, 42000, 250, NOW(), 38),
                                                                             (1800000000000000003, 3500, 120000, 600, NOW(), 89),
                                                                             (1800000000000000004, 2800, 95000, 450, NOW(), 72),
                                                                             (1800000000000000005, 4200, 150000, 800, NOW(), 120);

-- 5. 重新启用外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- 6. 验证数据
SELECT '✅ 插入完成！数据统计：' AS '';
SELECT '用户表：' AS 表名, COUNT(*) AS 记录数 FROM user;
SELECT '视频表：' AS 表名, COUNT(*) AS 记录数 FROM video;
SELECT '统计表：' AS 表名, COUNT(*) AS 记录数 FROM stat;