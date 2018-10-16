package com.onlynight.netty.nio.protobuf;

import com.github.onlynight.protobuf.demo.DemoProtos;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            OutputStream os = new FileOutputStream(new File("D:\\temp.proto"));
            DemoProtos.Pet pet0 = DemoProtos.Pet.newBuilder().setId(0).setName("小脑斧0").build();
            DemoProtos.Pet pet1 = DemoProtos.Pet.newBuilder().setId(1).setName("小脑斧1").build();
            DemoProtos.Pet pet2 = DemoProtos.Pet.newBuilder().setId(2).setName("小脑斧5").build();
            DemoProtos.Person person = DemoProtos.Person.newBuilder()
                    .addPet(pet0)
                    .addPet(pet1)
                    .addPet(pet2)
                    .build();
            person.writeTo(os);
            os.close();

            InputStream is = new FileInputStream(new File("D:\\temp.proto"));
            DemoProtos.Person person1 = DemoProtos.Person.parseFrom(is);
            System.out.println(person1.toString());
            System.out.println(person.getPetList().get(0).getName());
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
