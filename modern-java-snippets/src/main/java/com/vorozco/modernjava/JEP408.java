package com.vorozco.modernjava;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class JEP408 {

  public static void main(String[] args) throws IOException {
    System.out.println(oldFileServer());
    // System.out.println(newFileServer());
  }

  /**
   * Before JEP 408, even a basic static file server meant creating an
   * HttpServer by hand and writing your own handler to produce a response.
   */
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
    // server.stop(0);
    return result;
  }

  /**
   * JEP 408's SimpleFileServer serves files from a directory with a
   * single factory call - no handler to write.
   */
  private static String newFileServer() throws IOException {
    Path root = Path.of(".").toAbsolutePath();
    HttpServer server = SimpleFileServer.createFileServer(
        new InetSocketAddress(0), root, SimpleFileServer.OutputLevel.NONE);
    server.start();
    String result = "(new) server started on port " + server.getAddress().getPort();
    server.stop(0);
    return result;
  }
}
