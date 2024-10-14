package br.ada.tech.class1171;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        var home = System.getProperty("user.home");
//        var file = new File(home);
//        varrerArquivos(new File("."));
//        criarArquivo("./aula.txt");
//        var list = DataHelper.customers().stream()
//                .map(
//                        customer -> customer.getId() + ";"
//                                + customer.getName() + ";"
//                                + customer.getDocument() + ";"
//                                + customer.getBirthDate()
//                ).toList();
//        criarArquivo("./customers.csv", list);
//        readStream("./customers.csv").forEach(System.out::println);
        copy("./customers.csv", home+"/customers.csv");

    }

    public static void criarArquivo(String nome, Iterable<String> content) throws IOException {
        var path = Paths.get(nome);
        Files.write(path, content);
    }

    public static List<String> lerArquivo(String nome) throws IOException {
        var path = Paths.get(nome);
        return Files.readAllLines(path);
    }

    public static Stream<String> readStream(String root) throws IOException {
        var path = Paths.get(root);
        return Files.lines(path);
    }

    public static void copy(String from, String target) throws IOException {
        var pathFrom = Paths.get(from);
        var pathTarget = Paths.get(target);
        var relativePath = pathFrom.resolve(pathTarget);
        Files.copy(pathFrom, pathTarget);
    }

    public static void varrerArquivos(File file) {
        if (file.isDirectory()) {
            System.out.println(file.getAbsolutePath());
            Arrays.stream(
                    file.listFiles(
//                            pathname -> pathname.getName().endsWith(".txt")
                    )
            ).forEach(Main::varrerArquivos);
        } else {
            System.out.println("Arquivo: " + file.getAbsolutePath() + " tem o tamanho " + file.length() + " bytes");
        }
    }

    public static void varrerArquivoStream(String root) throws IOException {
        var path = Paths.get(root);
        Files.list(path).forEach(currentPath -> {
            try {
                if (Files.isDirectory(currentPath)) {
                    varrerArquivoStream(currentPath.toString());
                } else {
                    File file = Files.createFile(currentPath).toFile();
                    System.out.println("Arquivo: " + file.getAbsolutePath() + " tem o tamanho " + file.length() + " bytes");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
