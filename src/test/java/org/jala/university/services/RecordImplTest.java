package org.jala.university.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.jala.university.model.RecordModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class RecordImplTest {
    RecordDao recordDao = mock(RecordDao.class);
    RecordImpl record;
    @BeforeEach
    public void setup(){
        record = new RecordImpl(recordDao);
    }
    @Test
    void createRecordTest(){
        EntityManager entityManager = mock(EntityManager.class);
        recordDao = new RecorDao(UUID.class, RecordModel.class, entityManager);
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();

        RecordModel newRecord = record.create(recordModel);
        assertEquals(recordModel, newRecord);
        verify(entityManager, timeout(1)).persist(newRecord);
    }
    @Test
    void getAllRecordTest() {
        EntityManager entityManager = mock(EntityManager.class);
        recordDao = new RecorDao(UUID.class, RecordModel.class, entityManager);
        List<RecordModel> recordModels = Arrays.asList(
                RecordModel.builder().id(UUID.randomUUID()).build()

        );
        when(entityManager.createQuery(anyString(), eq(RecordModel.class))).thenReturn((TypedQuery<RecordModel>) mock(Query.class));
        when(entityManager.createQuery(anyString(), eq(RecordModel.class)).getResultList()).thenReturn(recordModels);
        List<RecordModel> actualRecords = record.findAll();
        assertEquals(recordModels, actualRecords);
    }
    @Test
    void deleteRecordTest() {
        EntityManager entityManager = mock(EntityManager.class);
        recordDao = new RecorDao(UUID.class, RecordModel.class, entityManager);
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();

        record.delete(recordModel);
        verify(entityManager, times(1)).remove(recordModel);

    }
    @Test
    void deleteRecordByIdTest() {
        EntityManager entityManager = mock(EntityManager.class);
        recordDao = new RecorDao(UUID.class, RecordModel.class, entityManager);
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();
        UUID recordIdToDelete = UUID.randomUUID();
        RecordModel recordModelDelete = RecordModel.builder().id(recordIdToDelete).build();

        record.deleteById(recordIdToDelete);

    }


}
