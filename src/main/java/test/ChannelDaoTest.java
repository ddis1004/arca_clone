package test;

import dao.Channel;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class ChannelDaoTest {

    public static Channel makeTestChannel(int id, String channel_name){
        return new Channel(
                id, channel_name, "slug", 10,
                "test_icon.webp", "test_default.jpg",
                "Introduction of " + channel_name
        );
    }
}
