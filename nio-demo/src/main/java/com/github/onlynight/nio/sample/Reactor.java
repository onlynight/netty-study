package com.github.onlynight.nio.sample;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {

    public int id = 100001;
    public static final int BUFFER_SIZE = 2048;

    @Override
    public void run() {
        init();
    }

    private void init() {
        try {
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
            InetSocketAddress address = new InetSocketAddress(
                    InetAddress.getLocalHost(), 4700);
            socketChannel.bind(address);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_ACCEPT)
                    .attach(id++);
            listener(selector);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listener(Selector inSelector) {
        try {
            while (true) {
                inSelector.select();
                Set<SelectionKey> selectedKeys = inSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                        serverSocketChannel.accept()
                                .configureBlocking(false)
                                .register(inSelector, SelectionKey.OP_READ | SelectionKey.OP_WRITE)
                                .attach(id++);
                    }

                    if (selectionKey.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        clientChannel.read(buffer);
                    }

                    if (selectionKey.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                        String message = "hello\n";
                        buffer.put(message.getBytes());
                        clientChannel.write(buffer);
                    }

                    if (selectionKey.isConnectable()) {
                        // TODO: 2018/7/13
                    }

                    iterator.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
