package com.admiralcoder.core;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public final class DemoServer implements AutoCloseable {
    private final HttpServer server;

    public DemoServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            if (!exchange.getRequestMethod().equals("GET") || !exchange.getRequestURI().getPath().equals("/")) {
                exchange.sendResponseHeaders(404, -1);
                exchange.close();
                return;
            }
            byte[] html;
            try (InputStream stream = Objects.requireNonNull(
                    DemoServer.class.getResourceAsStream("/inventory.html"))) {
                html = new String(stream.readAllBytes(), StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8);
            }
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=utf-8");
            exchange.sendResponseHeaders(200, html.length);
            try (var body = exchange.getResponseBody()) {
                body.write(html);
            }
        });
        server.start();
    }

    public String url() {
        return "http://127.0.0.1:" + server.getAddress().getPort() + "/";
    }

    @Override
    public void close() {
        server.stop(0);
    }
}
