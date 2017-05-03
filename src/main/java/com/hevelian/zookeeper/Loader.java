package com.hevelian.zookeeper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.zookeeper.server.ZooKeeperServer;

public class Loader extends HttpServlet {

	private ZooKeeperServer server;
	
	@Override
	public void init() throws ServletException {
		server = new ZooKeeperServer();
		server.startup();
	}
	
}
