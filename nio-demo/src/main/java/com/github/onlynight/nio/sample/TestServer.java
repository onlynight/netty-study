package com.github.onlynight.nio.sample;

public class TestServer {

    public static void main(String[] args) {
        Thread server = new Thread(new Reactor());
        server.start();

        while (true) {
        }
    }

}
