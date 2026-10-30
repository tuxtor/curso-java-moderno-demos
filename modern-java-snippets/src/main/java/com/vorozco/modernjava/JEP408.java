package com.vorozco.modernjava;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class JEP408 {

  public static void main(String[] args) throws IOException {
    System.out.println(newFileServer());
  }

  static String oldFileServer() throws IOException {
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

  static String newFileServer() throws IOException {
    Path root = Path.of(".").toAbsolutePath();
    HttpServer server = SimpleFileServer.createFileServer(
        new InetSocketAddress(0), root, SimpleFileServer.OutputLevel.NONE);
    server.start();
    String result = "(new) server started on port " + server.getAddress().getPort();
    return result;
  }

}
