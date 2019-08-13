package server;
//package com.journaldev.socket;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class SocketServerExample {

	//static ServerSocket variable
	private static ServerSocket server;
	//socket server port on which it will listen
	private static int port = 9876;

	public static void main(String args[]) throws IOException, ClassNotFoundException{

		SocketServer socketServer = new SocketServer();

		System.out.println( "Server starts" );
		socketServer.start();
		System.out.println( "Server ends" );
	}

}
