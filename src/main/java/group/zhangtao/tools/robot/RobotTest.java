package group.zhangtao.tools.robot;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotTest {
    private Robot robot = null;
    public RobotTest() throws AWTException {
        robot = new Robot();
    }
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        try {
            RobotTest robotTest = new RobotTest();
            
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
