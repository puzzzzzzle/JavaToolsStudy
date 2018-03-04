package group.zhangtao.junit;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {
    private String message = "hello junit";
    private MessageUtil messageUtil = new MessageUtil(message);

    @Test
    public void testMessage(){
        Assert.assertEquals("hello junit",messageUtil.getMassage());
    }
    @Test
    public void testAdd() {
        //test data
        int num= 5;
        String temp= null;
        String str= "Junit is working fine";

        //check for equality
        Assert.assertEquals("Junit is working fine", str);

        //check for false condition
        Assert.assertFalse(num > 6);

        //check for not null value
        Assert.assertNotNull(str);
    }

    @Test
    public void testFloat(){
        Assert.assertEquals(1.1+1.0,2.1,0);
    }
}
