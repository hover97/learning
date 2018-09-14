package com.learnBase.nio.scatterAndGather;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {
    public static void main(String[] args) {
        String filePath = Test.class.getResource("testScatterAndGather.txt").getPath();

        try (RandomAccessFile file = new RandomAccessFile(filePath,"rw")) {

            FileChannel channel = file.getChannel();

            //容量为48
            ByteBuffer header = ByteBuffer.allocate(4);
            ByteBuffer body = ByteBuffer.allocate(16);
            ByteBuffer[] bufferArray = { header, body };
            //读取
            while (channel.read(bufferArray) != -1){

                header.flip();
                body.flip();

                System.out.println("------header------");
                while (header.hasRemaining()){
                    System.out.print((char)header.get());
                }

                System.out.println();
                System.out.println("------body------");
                while (body.hasRemaining()){
                    System.out.print((char)body.get());
                }
                System.out.println();

                header.clear();
                body.clear();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}