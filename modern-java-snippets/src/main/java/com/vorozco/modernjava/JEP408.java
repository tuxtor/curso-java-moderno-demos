package com.vorozco.modernjava;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class JEP408 {

  public static void main(String[] args) throws IOException {
    System.out.println(oldFileServer());
  }

  private static String oldFileServer() throws IOException {
    HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
    server.createContext("/", exchange -> {
      byte[] bytes = "Hello from JEP 408!".getBytes();
      exchange.sendResponseHeaders(200, bytes.length);
      try (OutputStream os = exchange.getResponseBody()) {
        os.write(bytes);
      }
    });
    server.start();
    String result = "(old) server started on port " + server.getAddress().getPort();
    return result;
  }
}
