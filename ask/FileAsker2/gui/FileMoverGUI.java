package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        browseButton.addActionListener(this);
        moveButton.addActionListener(this);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup().addComponent(sourceLabel).addComponent(destinationLabel).addComponent(extensionsLabel).addComponent(defaultFolderLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(sourceTextField).addComponent(destinationTextField).addComponent(extensionsTextField).addComponent(defaultFolderTextField).addComponent(browseButton).addComponent(moveButton));
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(sourceLabel).addComponent(sourceTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(destinationLabel).addComponent(destinationTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(extensionsLabel).addComponent(extensionsTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(defaultFolderLabel).addComponent(defaultFolderTextField));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(browseButton));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(moveButton));
        layout.setVerticalGroup(vGroup);

        setContentPane(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == browseButton) {
