/*
 * Copyright: Copyright (c) 2015-2016 XunKeNetWork,Inc.All Rights Reserved.
 * Company:深圳迅科网络科技有限公司
 */

package com.zisezhixin.util.adapter;


/**
 * <h1>配制文件的适配</h1>
 *
 * @ClassName: ConfigBeanAdapter
 * @author maoyongbin
 * @date Jan 5, 2017 2:33:18 PM
 */
public class ConfigBeanAdapter {
    /* 文件服务器Ip地址 */
    public static String fileServerIp;
    /* 文件服务器访问地址 */
    public static String fileAddressLocal;
    public static String fileAddress;
    /*付款地址*/
    public static String payUrl;
    /*接口地址*/
    public static String interfaceUrl;
	/*中国保险1站地址*/
    public static String siteUrl;
    /*理赔服务链接 */
    public static String claimServiceUrl;
    /*销售系统地址 */
    public static String saleIp;
    /*汽车自助报价 */
    public static String vehicleSystemUrl;
    
    /* 聊天服务器ip和端口 */
    public static String chatHost;
    public static String chatPort;
    
    /*openfire聊天服务地址*/
    public static String chatServerAddress;

    /* 聊天项目接口IP地址*/
    public static String chatSystemUrl;
    
    public static String getChatSystemUrl() {
		return chatSystemUrl;
	}

	public static void setChatSystemUrl(String chatSystemUrl) {
		ConfigBeanAdapter.chatSystemUrl = chatSystemUrl;
	}

	public static String getChatServerAddress() {
		return chatServerAddress;
	}

	public static void setChatServerAddress(String chatServerAddress) {
		ConfigBeanAdapter.chatServerAddress = chatServerAddress;
	}

	public static String getPayUrl() {
        return payUrl;
    }

    public static void setPayUrl(String payUrl) {
        ConfigBeanAdapter.payUrl = payUrl;
    }

    public static String getFileServerIp() {
        return fileServerIp;
    }

    public static void setFileServerIp(String fileServerIp) {
        ConfigBeanAdapter.fileServerIp = fileServerIp;
    }

    public static String getFileAddressLocal() {
        return fileAddressLocal;
    }

    public static void setFileAddressLocal(String fileAddressLocal) {
        ConfigBeanAdapter.fileAddressLocal = fileAddressLocal;
    }

    public static String getFileAddress() {
        return fileAddress;
    }

    public static void setFileAddress(String fileAddress) {
        ConfigBeanAdapter.fileAddress = fileAddress;
    }

    public static String getChatHost() {
        return chatHost;
    }

    public static void setChatHost(String chatHost) {
        ConfigBeanAdapter.chatHost = chatHost;
    }

    public static String getChatPort() {
        return chatPort;
    }

    public static void setChatPort(String chatPort) {
        ConfigBeanAdapter.chatPort = chatPort;
    }
    
    public static String getInterfaceUrl() {
		return interfaceUrl;
	}

	public static void setInterfaceUrl(String interfaceUrl) {
		ConfigBeanAdapter.interfaceUrl = interfaceUrl;
	}

	public static String getSiteUrl() {
		return siteUrl;
	}

	public static void setSiteUrl(String siteUrl) {
		ConfigBeanAdapter.siteUrl = siteUrl;
	}

	public static String getSaleIp() {
		return saleIp;
	}

	public static void setSaleIp(String saleIp) {
		ConfigBeanAdapter.saleIp = saleIp;
	}

	public static String getVehicleSystemUrl() {
		return vehicleSystemUrl;
	}

	public static void setVehicleSystemUrl(String vehicleSystemUrl) {
		ConfigBeanAdapter.vehicleSystemUrl = vehicleSystemUrl;
	}

}
