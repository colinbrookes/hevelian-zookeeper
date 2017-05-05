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
	private static final String contextName = "hevelian.zookeeper.home";
	private Configuration configuration = new Configuration(contextName);
	private ZooKeeperServer server;
	
	@Override
	public void init() throws ServletException {
		try {
			server = new ZooKeeperServer(new File(configuration.getProperty("snap.directory")), 
											new File(configuration.getProperty("log.directory")), Integer.parseInt(configuration.getProperty("tick.time")));

			ServerCnxnFactory factory = new NIOServerCnxnFactory();
		    factory.configure(new InetSocketAddress(Integer.parseInt(configuration.getProperty("client.port"))), Integer.parseInt(configuration.getProperty("max.clients")));
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
