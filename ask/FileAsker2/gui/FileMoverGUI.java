

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class FileMoverGUI extends JFrame implements ActionListener {
    private JButton browseButton;
    private JButton moveButton;
    private JLabel sourceLabel;
    private JLabel destinationLabel;
    private JLabel extensionsLabel;
    private JLabel defaultFolderLabel;
    private JTextField sourceTextField;
    private JTextField destinationTextField;
    private JTextField extensionsTextField;
    private JTextField defaultFolderTextField;
    private JLabel statusLabel;
    private JLabel statusValueLabel;

    public FileMoverGUI() {
        super("File Mover");

        browseButton = new JButton("Browse");
        moveButton = new JButton("Move");
        sourceLabel = new JLabel("Source folder:");
        destinationLabel = new JLabel("Destination folder:");
        extensionsLabel = new JLabel("Extensions:");
        defaultFolderLabel = new JLabel("Default folder:");
        sourceTextField = new JTextField();
        destinationTextField = new JTextField();
        extensionsTextField = new JTextField();
        defaultFolderTextField = new JTextField();
        statusLabel = new JLabel("Status:");
        statusValueLabel = new JLabel("Waiting...");

        browseButton.addActionListener(this);
        moveButton.addActionListener(this);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(sourceLabel).addComponent(destinationLabel).addComponent(extensionsLabel).addComponent(defaultFolderLabel).addComponent(statusLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(sourceTextField).addComponent(destinationTextField).addComponent(extensionsTextField).addComponent(defaultFolderTextField).addComponent(browseButton).addComponent(moveButton).addComponent(statusValueLabel));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(sourceLabel).addComponent(sourceTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(destinationLabel).addComponent(destinationTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(extensionsLabel).addComponent(extensionsTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(defaultFolderLabel).addComponent(defaultFolderTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(browseButton));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(moveButton));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(statusLabel).addComponent(statusValueLabel));
        layout.setVerticalGroup(vGroup);

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose Source Folder");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                sourceTextField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == moveButton) {
            String sourceFolderPath = sourceTextField.getText();
            String destinationFolderPath = destinationTextField.getText();
            String extensionsText = extensionsTextField.getText();
            String defaultFolderPath = defaultFolderTextField.getText();
        
            if (sourceFolderPath.equals("")) {
                statusValueLabel.setText("Please select a source folder.");
                return;
            }
        
            if (destinationFolderPath.equals("")) {
                statusValueLabel.setText("Please select a destination folder.");
                return;
            }
        
            File sourceFolder = new File(sourceFolderPath);
            if (!sourceFolder.isDirectory()) {
                statusValueLabel.setText("Selected source is not a folder.");
                return;
            }
        
            File destinationFolder = new File(destinationFolderPath);
            if (!destinationFolder.isDirectory()) {
                statusValueLabel.setText("Selected destination is not a folder.");
                return;
            }
        
            String[] extensionsArray = extensionsText.split(",");
            Map<String, File> foldersMap = new HashMap<>();
        
            for (String extension : extensionsArray) {
                String folderPath = defaultFolderPath;
                if (!folderPath.endsWith(File.separator)) {
                    folderPath += File.separator;
                }
                folderPath += extension.trim();
        
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                foldersMap.put(extension.trim(), folder);
            }
        
            statusValueLabel.setText("Moving files...");
            File[] files = sourceFolder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String extension = getFileExtension(file);
                    if (foldersMap.containsKey(extension)) {
                        Path sourcePath = Paths.get(file.getAbsolutePath());
                        Path destinationPath = Paths.get(foldersMap.get(extension).getAbsolutePath() + File.separator + file.getName());
                        try {
                            Files.move(sourcePath, destinationPath);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            statusValueLabel.setText("Finished moving files.");
    }       }

    private String getFileExtension(File file) {
        return null;
    }}
        
       
           
