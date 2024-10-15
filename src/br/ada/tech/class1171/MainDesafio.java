package br.ada.tech.class1171;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MainDesafio {

    public static void main(String[] args) throws IOException {
        // Copiar os arquivos de ./database/txt/customers para a pasta do usuário
//        copy();
        // Copiar o conteúdo dos arquivos para a um outro arquivo único
//        copyContentToFile();
        // Buscar um customer pelo id
        findById();
    }

    public static void copy() throws IOException {
        var userHome = System.getProperty("user.home");
        var rootFolder = Paths.get("./database/txt/customers");
        Files.list(rootFolder).forEach(path -> {
            try {
                var fileName = path.getFileName();
                Files.copy(path, Paths.get(userHome, fileName.toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void copyContentToFile() throws IOException {
        var rootFolder = Paths.get("./database/txt/customers");
        Files.list(rootFolder)
                .flatMap(path -> {
                    try {
                        return Files.lines(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).sorted((left, right) -> {
                    var leftId = Integer.parseInt(left.split(";")[0]);
                    var rightId = Integer.parseInt(right.split(";")[0]);
                    return leftId - rightId;
                }).forEach(value -> {
                    try {
                        Files.write(
                                Paths.get("./customers.csv"),
                                List.of(value),
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static void findById() throws IOException {
        var rootFolder = Paths.get("./database/txt/customers");
        var findById = 5;
        Files.list(rootFolder)
                .filter(path -> {
                    try {
                        var value = Files.readString(path).split(";")[0];
                        var id = Integer.valueOf(value);
                        return id == findById;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).findFirst().ifPresent(path -> {
                    try {
                        System.out.println(Files.readString(path));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
