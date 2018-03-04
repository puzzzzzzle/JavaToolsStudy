package group.zhangtao.io.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFunctions {

    public static void main(String[] args) {
        try {
            zipFile("测试", "测试.zip");
//            zipFile("test", "testOut.zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 简化压缩方法
     * 压缩：文件夹：压缩整个文件夹中的所有文件，文件：压缩单独文件，生成指定名称的zip压缩包
     *
     * @param filepath 文件所在目录
     * @param zipPath  压缩后zip文件名称
     * @throws Exception 文件不存在
     */
    public static void zipFile(String filepath, String zipPath) throws Exception {
        zipFile(filepath, zipPath, 1024, "");
    }

    /**
     * 压缩：文件夹：压缩整个文件夹中的所有文件，文件：压缩单独文件，生成指定名称的zip压缩包
     *
     * @param filepath   文件所在目录
     * @param zipPath    压缩后zip文件名称
     * @param bufferSize 缓冲大小
     * @param comment    注释
     * @throws Exception 文件不存在
     */
    public static void zipFile(String filepath, String zipPath, int bufferSize, String comment) throws Exception {
        try (ZipOutputStream zipOut = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(zipPath)
                )
        )) {
            File sourceFile = new File(filepath);
            if (!sourceFile.exists()) {
                throw new FileNotFoundException("sourceFile not exits");
            }
            comment = new String(comment.getBytes("8859_1"), "GB2312");
            zipOut.setComment(comment);
            zipEveryFile(zipOut, sourceFile, "", bufferSize);
        }
    }

    /**
     * 向zipOut中写入一个文件或文件夹（文件夹递归调用）
     *
     * @param zipOut     zip outStream 对象
     * @param file       传入文件
     * @param baseDir    当前目录
     * @param bufferSize 缓冲区大小 Byte
     * @throws Exception
     */
    private static void zipEveryFile(ZipOutputStream zipOut, File file, String baseDir, int bufferSize) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileSec : files) {
                String mBaseDir = baseDir + file.getName() + File.separator;
                mBaseDir = new String(mBaseDir.getBytes("8859_1"), "GB2312");
                zipEveryFile(zipOut, fileSec, mBaseDir, bufferSize);
            }
        } else {
            try (InputStream input = new FileInputStream(file)) {
                byte[] buf = new byte[bufferSize];
                String name = baseDir + file.getName();
                name =  new String(name.getBytes("8859_1"), "GB2312");
                zipOut.putNextEntry(new ZipEntry(name));
                int len;
                while ((len = input.read(buf)) != -1) {
                    zipOut.write(buf, 0, len);
                }
            }
        }
    }
}
