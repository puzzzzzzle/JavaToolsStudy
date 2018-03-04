package group.zhangtao.io.dir;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        copyDir("./logs", "E:\\logs");
        checkEmpty("./logs");
    }

    public static void checkEmpty(String path) {
        File file = new File(path);
        if (!file.isDirectory() || !file.exists()) {
            return;
        }
        File[] childs = file.listFiles();
        for (File f : childs
                ) {
            if (f.isDirectory() && f.listFiles().length <= 0) {
                f.delete();
            }
        }
    }

    public static boolean copyDir(String srcPath, String destPath) {
        File src = new File(srcPath), dest = new File(destPath);
        if (!src.exists()
                || !src.isDirectory()
                ) {
            return false;
        }
        if (!dest.exists() && !dest.isDirectory()) {
            dest.mkdir();
        }
        File[] srcs = src.listFiles();
        for (File file : srcs
                ) {
            if (file.isDirectory()) {
                copyDir(file.getPath(), destPath + File.separator + file.getName());
            } else {
                copyFile(file.getPath(), destPath + File.separator + file.getName()); //调用文件拷贝的方法
            }
        }
        return true;
    }

    /**
     * 复制文件（非目录）
     *
     * @param srcFile  要复制的源文件
     * @param destFile 复制到的目标文件
     * @return
     */
    public static boolean copyFile(String srcFile, String destFile) {
        try {
            BufferedInputStream streamFrom = new BufferedInputStream(new FileInputStream(srcFile));
            BufferedOutputStream streamTo = new BufferedOutputStream(new FileOutputStream(destFile));
            byte buffer[] = new byte[1024];
            int len;
            while ((len = streamFrom.read(buffer)) > 0) {
                streamTo.write(buffer, 0, len);
            }
            streamFrom.close();
            streamTo.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
