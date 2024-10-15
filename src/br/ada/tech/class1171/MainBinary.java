package br.ada.tech.class1171;

import br.ada.tech.class1171.customer.Customer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainBinary {

    public static void main(String[] args) throws IOException {
        var rootFolder = Paths.get("./database/binary/customers");
        //DataHelper.customers().forEach(customer -> write(rootFolder, customer));
        read(rootFolder);
    }

    public static void write(Path root, Customer customer) {
        File file = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            file = new File(root.toAbsolutePath().toString(), "customer-"+customer.getId()+".bin");
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(customer);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read(Path root) throws IOException {
        Files.list(root).forEach(path -> {
            var file = path.toFile();
            FileInputStream fileInput = null;
            ObjectInputStream objectInputStream = null;
            try {
                fileInput = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInput);

                Customer customer = (Customer) objectInputStream.readObject();
                System.out.println(customer);

                objectInputStream.close();
                fileInput.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
