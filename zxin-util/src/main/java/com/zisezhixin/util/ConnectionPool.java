package com.zisezhixin.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

import com.zisezhixin.util.adapter.ConfigBeanAdapter;

public class ConnectionPool{

	 // the limit of connection instance
	private int size = 10;
	// busy connection instances
	private ConcurrentHashMap<StorageClient1, Object> busyConnectionPool = null; 
	// idle  connection instances
	private ArrayBlockingQueue<StorageClient1> idleConnectionPool = null; 

	//private final static String tgStr = "10.1.0.137";
	private final static int port = 22122;

	private Object obj = new Object();

	// class method
	// singleton
	private ConnectionPool() {
		busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
		idleConnectionPool = new ArrayBlockingQueue<StorageClient1>(size);
		init(size);

	};

	private static ConnectionPool instance = new ConnectionPool();

	// get the connection pool instance
	public static ConnectionPool getPoolInstance() {
		return instance;
	}

	// class method
	// init the connection pool
	private void init(int size) {
		initClientGlobal();
		TrackerServer trackerServer = null;
		try {
			TrackerClient trackerClient = new TrackerClient();
			//Only tracker
			trackerServer = trackerClient.getConnection();
			for (int i = 0; i < size; i++) {
				StorageServer storageServer = null;
				StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
				idleConnectionPool.add(client1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(trackerServer!=null){
				try {
					trackerServer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	// 1. pop one connection from the idleConnectionPool,
	// 2. push the connection into busyConnectionPool;
	// 3. return the connection
	// 4. if no idle connection, do wait for wait_time seconds, and check again
	public StorageClient1 checkout(int waitTimes) throws InterruptedException {
		StorageClient1 client1 = idleConnectionPool.poll(waitTimes,
				TimeUnit.SECONDS);
		busyConnectionPool.put(client1, obj);
		return client1;
	}

	// 1. pop the connection from busyConnectionPool;
	// 2. push the connection into idleConnectionPool;
	// 3. do nessary cleanup works.
	public void checkin(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1)!=null) {
			idleConnectionPool.add(client1);
		}
	}


	// so if the connection was broken due to some erros (like
	// : socket init failure, network broken etc), drop this connection
	// from the busyConnectionPool, and init one new connection.
	public void drop(StorageClient1 client1) {
		if (busyConnectionPool.remove(client1)!=null) {
			TrackerServer trackerServer = null;
			try {
				TrackerClient trackerClient = new TrackerClient();
				//此处有内存泄露，因为trackerServer没有关闭连接
				trackerServer = trackerClient.getConnection();
				
				StorageServer storageServer = null;
				StorageClient1 newClient1 = new StorageClient1(trackerServer, storageServer);
				
				idleConnectionPool.add(newClient1);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(trackerServer!=null){
					try {
						trackerServer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void initClientGlobal() {
		InetSocketAddress[] trackerServers = new InetSocketAddress[1];
		trackerServers[0] = new InetSocketAddress(ConfigBeanAdapter.fileServerIp, port);
		ClientGlobal.setG_tracker_group(new TrackerGroup(trackerServers));
		// 连接超时的时限，单位为毫秒
		ClientGlobal.setG_connect_timeout(2000);
		// 网络超时的时限，单位为毫秒
		ClientGlobal.setG_network_timeout(30000);
		ClientGlobal.setG_anti_steal_token(false);
		// 字符集
		ClientGlobal.setG_charset("UTF-8");
		ClientGlobal.setG_secret_key(null);
	}
	
}
