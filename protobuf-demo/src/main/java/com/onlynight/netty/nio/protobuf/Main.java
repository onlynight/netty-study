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
            DemoProtos.Pet pet3 = DemoProtos.Pet.newBuilder().setId(3).setName("小脑斧7").build();
            DemoProtos.Pet pet4 = DemoProtos.Pet.newBuilder().setId(4).setName("小脑斧6").build();
            DemoProtos.Pet pet5 = DemoProtos.Pet.newBuilder().setId(5).setName("小脑斧4").build();
            DemoProtos.Pet pet6 = DemoProtos.Pet.newBuilder().setId(6).setName("小脑斧3").build();
            DemoProtos.Pet pet7 = DemoProtos.Pet.newBuilder().setId(7).setName("小脑斧2").build();
            DemoProtos.Person person = DemoProtos.Person.newBuilder()
                    .addPet(pet0)
                    .addPet(pet1)
                    .addPet(pet2)
                    .addPet(pet3)
                    .addPet(pet4)
                    .addPet(pet5)
                    .addPet(pet6)
                    .addPet(pet7)
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
