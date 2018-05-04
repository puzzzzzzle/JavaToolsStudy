package group.zhangtao.io;

import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipFileSystemProvider;
import group.zhangtao.Map.Main;
import sun.nio.fs.LinuxFileSystemProvider;
import sun.nio.fs.UnixFileSystemProvider;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;

public class NioZip {
    public static void main(String[] args) throws IOException {
        String from = "/home/tao/文档/Tencent Files/2359173906/FileRecv";
        String to = "/home/tao/文档/Tencent Files/2359173906";
        String  encode ="GBK";
        boolean isDeleteExit = false;
        new NioZip().zip(from,to,encode,isDeleteExit);
    }

    private boolean unZip(String file, String toPath, String encode, boolean isDeleteExit) throws IOException {
        Path path = Paths.get(file);
        if (!Files.exists(path)) {
            System.out.println("不存在该文件:" + file);
            return false;
        }
        try {
            Charset charset = Charset.forName(encode);
        } catch (UnsupportedCharsetException e) {
            System.out.println("不存在该编码:" + encode);
            return false;
        }

        Map<String, String> env = new HashMap<>();
        env.put("encoding", encode);
        ZipFileSystem zipFileSystem = (ZipFileSystem) new ZipFileSystemProvider().newFileSystem(path,env);
        Files.walkFileTree(zipFileSystem.getPath("/"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.toString());
                Path destPath = Paths.get(toPath, file.toString());
                if (Files.exists(destPath)) {
                    if (isDeleteExit) {
                        System.out.println("文件重名，以删除！:" + destPath);
                        Files.deleteIfExists(destPath);
                        Files.createDirectories(destPath.getParent());
                        Files.move(file, destPath);
                    } else {
                        System.out.println("文件重名，以忽略！：" + destPath);
                    }
                }

                return FileVisitResult.CONTINUE;
            }
        });
        return true;
    }

    private boolean zip(String file, String toFile, String encode, boolean isDeleteExit) throws IOException {
        Path path = Paths.get(file);
        if (!Files.exists(path)) {
            System.out.println("不存在该文件:" + file);
            return false;
        }
        Path toPath = Paths.get(toFile);
        if (!Files.exists(toPath)) {
            if (isDeleteExit) {
                //todo 取消注释
//                Files.deleteIfExists(toPath);
                System.out.println("目标文件以存在，以删除！:"+toFile);
            }else {
                System.out.println("错误：目标文件以存在，并且isDeleteExit设为false！:"+toFile);
                return false;
            }
        }
        try {
            Charset charset = Charset.forName(encode);
        } catch (UnsupportedCharsetException e) {
            System.out.println("不存在该编码:" + encode);
            return false;
        }
//        LinuxFileSystemProvider
        Map<String, String> env = new HashMap<>();
        env.put("encoding", encode);
        FileSystem from = new LinuxFileSystemProvider().newFileSystem(path, null);
        ZipFileSystem zipFileSystem = (ZipFileSystem) new ZipFileSystemProvider().newFileSystem(toPath, env);
        Files.walkFileTree(from.getPath("/"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.toString());
//                Path destPath = Paths.get(toPath, file.toString());
//                if(Files.isDirectory(file)){
//                    Files.createDirectories()
//                }

                return FileVisitResult.CONTINUE;
            }
        });
        return true;
    }
}
