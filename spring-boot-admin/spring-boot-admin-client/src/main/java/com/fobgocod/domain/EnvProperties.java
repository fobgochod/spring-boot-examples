package com.fobgocod.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * 功能描述
 *
 * @author seven
 * @date 2019/5/22
 */
@Component
public class EnvProperties {


    @Value("${spring.profiles.active}")
    private String active;
    @Value("${server.port}")
    private String port;


    /**
     * 当前进程运行的主机名
     */
    private String host;
    /**
     * 当前进程所在的IP地址
     */
    private String ipAddress;
    /**
     * 空闲内存
     */
    private long freeMemory;
    /**
     * 内存总量
     */
    private long totalMemory;
    /**
     * java虚拟机允许开启的最大的内存
     */
    private long maxMemory;
    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 进程号
     */
    private long pid;
    /**
     * 程序启动时间
     */
    private Date startTime;
    /**
     * 类所在路径
     */
    private String classPath;
    private String projectPath;
    /**
     * 程序运行时间，单位毫秒
     */
    private long runtime;
    /**
     * 线程总量
     */
    private int threadCount;

    public EnvProperties() {
    }

    /**
     * 把byte转换成M
     *
     * @param bytes
     * @return
     */
    static long byteToM(long bytes) {
        long kb = (bytes / 1024 / 1024);
        return kb;
    }

    /**
     * 获取进程号，适用于windows与linux
     *
     * @return
     */
    static long tryGetPid() {
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String pid = name.split("@")[0];
            return Long.parseLong(pid);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void refresh() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Runtime runtime = Runtime.getRuntime();
        //空闲内存
        long freeMemory = runtime.freeMemory();
        this.setFreeMemory(byteToM(freeMemory));
        //内存总量
        long totalMemory = runtime.totalMemory();
        this.setTotalMemory(byteToM(totalMemory));
        //最大允许使用的内存
        long maxMemory = runtime.maxMemory();
        this.setMaxMemory(byteToM(maxMemory));
        //操作系统
        this.setOsName(System.getProperty("os.name"));
        InetAddress localHost;
        try {
            localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            this.setHost(hostName);
            if (ipAddress == null) {
                ipAddress = localHost.getHostAddress();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();

            this.setHost("未知");
        }
        //ip
        this.setIpAddress(ipAddress);

        //程序启动时间
        long startTime = runtimeMXBean.getStartTime();
        Date startDate = new Date(startTime);
        this.setStartTime(startDate);
        //类所在路径
        this.setRuntime(runtimeMXBean.getUptime());
        //线程总数
        this.setThreadCount(ManagementFactory.getThreadMXBean().getThreadCount());
        this.setPid(tryGetPid());
    }


    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
}
