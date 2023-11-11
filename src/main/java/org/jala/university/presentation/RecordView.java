package org.jala.university.presentation;


import org.jala.university.services.RecordImpl;
import org.jala.university.utilities.Dialog;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RecordView extends JFrame {
    private final RecordImpl record;
    private JPanel topPanel;
    private JPanel btnPanel;
    private JScrollPane scrollPane;

    private UUID selectedAccountId;

    public RecordView(RecordImpl record) {
        this.record = record;
        setTitle("Record Credit Card");
        setSize(500, 500);
        setBackground(Color.gray);
        setLocationRelativeTo(null);

        RecordTable recordTableView = new RecordTable(record.findAll());
        JTable list = new JTable(recordTableView);

        list.setRowSelectionAllowed(true);
        ListSelectionModel cellSelectionModel = list.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(e -> {
            UUID selectedData = null;

            int selectedRow = list.getSelectedRow();
            if(selectedRow == -1) {
                return;
            }

            selectedAccountId = (UUID) list.getValueAt(selectedRow, 0);
        });
        topPanel = new JPanel();
        btnPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        scrollPane = new JScrollPane(list);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        JButton addButtonDeleteAll = new JButton("DELETE ALL");
        JButton addButtonDeleteByID = new JButton("DELETE BY ID");
        btnPanel.add(addButtonDeleteByID);
        btnPanel.add(addButtonDeleteAll);

        addButtonDeleteByID.addActionListener(e -> {
            if (selectedAccountId != null) {
                record.deleteById(selectedAccountId);
                recordTableView.refresh(record.findAll());
            } else {
                Dialog.error("You did not select the id to delete.");
            }
        });

        addButtonDeleteAll.addActionListener(e -> {
            record.delete();
            recordTableView.refresh(record.findAll());
        });


    }
}
