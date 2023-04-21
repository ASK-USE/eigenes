package ask.ask1.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAsker {
    public static void main(String[] args) {
        String sourceFolder = "C:/source";
        String destinationFolder = "C:/destination";
        String[] extensions = {"txt", "pdf", "docx"};
        String[] folders = {"TextFiles", "PDFs", "WordDocs"};
        String defaultFolder = "OtherFiles";

        File folder = new File(sourceFolder);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                String extension = getFileExtension(file);
                int index = getExtensionIndex(extension, extensions);
                String folderName = (index != -1) ? folders[index] : defaultFolder;
                Path sourcePath = Paths.get(file.getAbsolutePath());
                Path destinationPath = Paths.get(destinationFolder + "/" + folderName + "/" + file.getName());

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

    private static int getExtensionIndex(String extension, String[] extensions) {
        for (int i = 0; i < extensions.length; i++) {
            if (extensions[i].equals(extension)) {
                return i;
            }
        }
        return -1;
    }    
    
}
