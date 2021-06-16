package com.hhj.shop.util;

import com.hhj.shop.ShopApplication;
import net.lingala.zip4j.core.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Title: zip文件解压
 * @Description:
 *
 * @author: hhj
 * @date: 2021/4/16 0014 10:25
 * @version V1.0
 */
public class FileUtils {

    protected static Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * @Title: 文件保存
     * @MethodName: saveFile
     * @param file
     * @param path
     * @Return boolean
     * @Exception
     * @Description:
     *
     * @author: 王延飞
     * @date: 2020/8/14 0014 11:04
     */
    public static boolean saveFile(MultipartFile file, String path) {

        File desFile = new File(path);
        if (!desFile.getParentFile().exists()) {
            desFile.mkdirs();
        }
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            log.error("【文件保存】异常，路径：{} ，异常信息：{} ", path, e);
            return false;
        }
        return true;
    }

    /**
     * @Title: 获取项目classpath路径
     * @MethodName: getApplicationPath
     * @param
     * @Return java.lang.String
     * @Exception
     * @Description:
     *
     * @author: 王延飞
     */
    public static String getApplicationPath() {

        //获取classpath
        ApplicationHome h = new ApplicationHome(ShopApplication.class);
        File jarF = h.getSource();
        return jarF.getParentFile() + File.separator;
    }

    /**
     * zip文件解压
     *
     * @param destPath 解压文件路径
     * @param zipFile  压缩文件
     * @param password 解压密码(如果有)
     * @param isDel 解压后删除
     */
    public static boolean unPackZip(File zipFile, String password, String destPath, boolean isDel) {
        try {
            ZipFile zip = new ZipFile(zipFile);
            /*zip4j默认用GBK编码去解压*/
            zip.setFileNameCharset(Constants.UTF8);
            log.info("【文件解压】begin unpack zip file....");
            zip.extractAll(destPath);
            // 如果解压需要密码
            if (zip.isEncrypted()) {
                zip.setPassword(password);
            }
        } catch (Exception e) {
            log.error("【文件解压】异常，路径：{} ，异常信息：{} ", destPath, e);
            return false;
        }

        if (isDel) {
            zipFile.deleteOnExit();
        }
        return true;
    }


    /**
     * @Title: 读取文件内容
     * @MethodName:  readZipFile
     * @param file
     * @Return void
     * @Exception
     * @Description:
     *
     * @author: 王延飞
     * @date:  2020/8/14 0014 20:26
     */
    public static void readZipFile(String file) throws Exception {

        java.util.zip.ZipFile zf = new java.util.zip.ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {
            } else {
                System.err.println("file - " + ze.getName());

            }
        }
        zin.closeEntry();
    }

    /*
     * Java文件操作 获取文件扩展名
     *
     *  Created on: 2011-8-2
     *      Author: blueeagle
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(0,dot);
            }
        }
        return filename;
    }

}
