package menu;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import library.Library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static String filename = "Lib.json";
    public static Library lib;

    public static void main(String[] args) {

        System.out.println("Library project v1.2");

        lib = deserializeLib(filename);

        new MainMenu().run();

        serializeLib(filename, lib);

    }

    public static void serializeLib(String filename, Library lib) {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filename);
            ObjectMapper objectMapper = new ObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.registerModule(new JodaModule());
            objectMapper.writeValue(fileOut, lib);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            fileOut.close();
        } catch (IOException ignored) {
        }

    }

    public static Library deserializeLib(String filename) {
        FileInputStream fileIn = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.registerModule(new JodaModule());
            fileIn = new FileInputStream(filename);
            Library lib = objectMapper.readValue(fileIn, Library.class);
            fileIn.close();
            lib.linkingAuthors();
            lib.linkingBooks();
            return lib;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                if (fileIn != null) fileIn.close();
            } catch (IOException ignored) {
            }
            return new Library();
        }

    }


}