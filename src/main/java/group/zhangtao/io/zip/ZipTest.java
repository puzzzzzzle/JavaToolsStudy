package group.zhangtao.io.zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipTest {
    public static void main(String[] args) {
        try {
            zipFile("测试", "测.zip");
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
        ZipOutputStream zipOut=null;
        try{
            zipOut= new ZipOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(zipPath)
                    )
            );
            File sourceFile = new File(filepath);
            if (!sourceFile.exists()) {
                throw new FileNotFoundException("sourceFile not exits");
            }
            zipOut.setComment(comment);
            zipEveryFile(zipOut, sourceFile, "", bufferSize);
        }finally {
            if(zipOut!=null){
                zipOut.close();
            }
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
                zipEveryFile(zipOut, fileSec, baseDir + file.getName() + File.separator, bufferSize);
            }
        } else {
            InputStream input=null;
            try{
                input= new FileInputStream(file);
                byte[] buf = new byte[bufferSize];
                zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
                int len;
                while ((len = input.read(buf)) != -1) {
                    zipOut.write(buf, 0, len);
                }
            }finally {
                if(input!=null){
                    input.close();
                }
            }
        }
    }
}
