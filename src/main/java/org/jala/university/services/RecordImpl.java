package org.jala.university.services;

import org.jala.university.dao.RecordDao;
import org.jala.university.model.RecordModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public class RecordImpl {
    private final RecordDao recordDao;
    public RecordImpl(RecordDao recordDao) {
        this.recordDao = recordDao;
        
    }

    public RecordModel create(RecordModel recordModel) {
        recordDao.create(recordModel);
        return recordModel;
    }

    public List<RecordModel> findAll() {
        return recordDao.findAll();
    }

    public void delete() {
        List<RecordModel> allRecords = recordDao.findAll();
        for (RecordModel recordModel : allRecords) {
            recordDao.delete(recordModel);
        }
    }

    public void deleteById(UUID recordIdToDelete) {
        recordDao.deleteById(recordIdToDelete);
    }
}
