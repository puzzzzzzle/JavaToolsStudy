package group.zhangtao.tools.settings;

import org.apache.logging.log4j.Logger;

public class MLogger {
    public static Logger logger = StaticFiled.logger;
    public static void debug(String msg){
        logger.debug(msg);
    }
    public static void info(String msg){
        logger.info(msg);
    }

    public static void debug(Integer integer) {
        logger.debug(integer);
    }
}
