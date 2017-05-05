package com.hevelian.zookeeper;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

public class Loader extends HttpServlet {

	private static final long serialVersionUID = -6989750428431526717L;
	private static final String snapDir = "/Users/cb/Development/hevelian/zookeeper/data";
	private static final String logDir = "/Users/cb/Development/hevelian/zookeeper/log";
	private static final int port = 2181;
	private static final int maxClients = 100;
	
	private ZooKeeperServer server;
	
	@Override
	public void init() throws ServletException {
		try {
			server = new ZooKeeperServer(new File(snapDir), new File(logDir), ZooKeeperServer.DEFAULT_TICK_TIME);

			ServerCnxnFactory factory = new NIOServerCnxnFactory();
		    factory.configure(new InetSocketAddress(port), maxClients);
			factory.startup(server);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("ClientPort: " + server.getClientPort());
		System.out.println("ZooKeeper Service Started");
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void destroy() {
		server.shutdown();
	}
}
