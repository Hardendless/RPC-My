package top.wang.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wang.rpc.api.discussPost;
import top.wang.rpc.api.discussPostService;

public class discussPostServiceImpl implements discussPostService {

    private static final Logger logger = LoggerFactory.getLogger(discussPostServiceImpl.class);

    @Override
    public discussPost getDiscussPostById(Integer id) {
        discussPost discusspost = discussPost.builder().id(id).title("我的帖子").uerId(22).build();
        logger.info("客户端查询了" + id + "帖子");
        return discusspost;
    }
}
