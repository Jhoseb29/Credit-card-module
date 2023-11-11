package org.jala.university.presentation;

import org.jala.university.model.RecordModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.UUID;

public class RecordTable extends AbstractTableModel {
    private List<RecordModel> recordModels;
    private String[] columnNames = {"ID", "OPERATION", "CURRENT_BALANCE", "PIN", "STATUS", "DATE"};
    public RecordTable(List<RecordModel> recordModels){
        this.recordModels = recordModels;
    }

    @Override
    public String getColumnName(int columnIndex){
        return columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return recordModels.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RecordModel recordModel = recordModels.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return recordModel.getId();
            case 1:
                return recordModel.getOperation();
            case 2:
                return recordModel.getCurrent_limit();
            case 3:
                return recordModel.getPIN();
            case 4:
                return recordModel.getStatus();
            case 5:
                return recordModel.getDate();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case 0:
                return UUID.class;
            case 1:
                return String.class;
            case 2:
                return Long.class;
        }
        return null;
    }
    public void refresh(List<RecordModel> list) {
        this.recordModels = list;
        fireTableDataChanged();
    }
}
