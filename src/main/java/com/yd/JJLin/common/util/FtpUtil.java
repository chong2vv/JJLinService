package com.yd.JJLin.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * FTP工具类
 *
 * @author wangyuandong
 */
@Slf4j
public class FtpUtil {

    private final FTPClient ftpClient;
    private final String host;
    private final int port;

    private FtpUtil(String host, int port) {
        this.host = host;
        this.port = port;
        ftpClient = new FTPClient();
    }

    /**
     * 创建FTP工具类
     *
     * @param host 服务器
     * @param port 端口
     * @return 工具类
     */
    public static FtpUtil getInstance(String host, int port) {
        return new FtpUtil(host, port);
    }

    /**
     * 登录FTP
     *
     * @param username 用户名
     * @param password 密码
     */
    public void connectAndLogin(String username, String password) {
        try {
            // 连接ftp服务器
            ftpClient.connect(host, port);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP SERVER CONNECT ERROR {}", e.getMessage());
        }

        try {
            // 登录ftp服务器
            ftpClient.login(username, password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("LOGIN FTP SERVER ERROR {}", e.getMessage());
        }
    }

    /**
     * 文件夹路径
     *
     * @param path 路径
     */
    public void changeWorkDirectory(String path) {
        try {
            ftpClient.changeWorkingDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP CHANGE WORKING DIRECTORY ERROR {}", e.getMessage());
        }
    }

    /**
     * 上传文件到ftp服务器
     *
     * @param file 文件对象
     * @param name 文件名称
     * @return 上传结果
     */
    public synchronized boolean storeFileToServer(File file, String name) {
        FileInputStream input = null;
        boolean saveSuccess = false;
        try {
            input = new FileInputStream(file);
            ftpClient.enterLocalPassiveMode();
            saveSuccess = ftpClient.storeFile(name, input);
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP UPLOAD FILE ERROR {}", e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return saveSuccess;
    }

    /**
     * 退出
     */
    public void logout() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
