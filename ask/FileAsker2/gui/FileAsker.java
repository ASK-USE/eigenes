package gui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;

public class FileAsker extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Erstelle das GridPane-Layout und füge die Elemente hinzu
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        Label sourceLabel = new Label("Source folder:");
        root.add(sourceLabel, 0, 1);

        TextField sourceTextField = new TextField();
        root.add(sourceTextField, 1, 1);

        Label destinationLabel = new Label("Destination folder:");
        root.add(destinationLabel, 0, 2);

        TextField destinationTextField = new TextField();
        root.add(destinationTextField, 1, 2);

        Button moveButton = new Button("Move files");
        root.add(moveButton, 1, 3);

        // Setze die Aktion des Buttons
        moveButton.setOnAction(event -> {
            String sourceFolder = sourceTextField.getText();
            String destinationFolder = destinationTextField.getText();

            File folder = new File(sourceFolder);
            File[] files = folder.listFiles();

            for (File file : files) {
                // Implementation der Dateiverschiebung
            }
        });

        // Erstelle die Scene und zeige das Fenster an
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("File Asker");
        primaryStage.show();
    }

//     package ask.ask1.test;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import org.json.JSONObject;
// import java.io.FileReader;

// public class FileAsker {
    public static void main(String[] args) {
        String sourceFolder = "C:/source";
        String destinationFolder = "C:/destination";
        String defaultFolder = "OtherFiles";

        JSONObject config = readConfig("config.json");
        JSONObject extensions = config.getJSONObject("extensions");
        defaultFolder = config.getString("default");

        File folder = new File(sourceFolder);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String extension = getFileExtension(file);
                String folderName = extensions.optString(extension, defaultFolder);
                Path sourcePath = file.toPath();
                Path destinationPath = Paths.get(destinationFolder, folderName, file.getName());

                try {
                    Files.createDirectories(destinationPath.getParent());
                    Files.move(sourcePath, destinationPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }

    private static JSONObject readConfig(String path) {
        try (FileReader reader = new FileReader(path)) {
            StringBuilder builder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                builder.append((char) character);
            }
            return new JSONObject(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}


