package org.jala.university.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.jala.university.dao.RecordDao;
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

    @BeforeEach
    public void setup(){

    }
    @Test
    void createRecordTest(){
        EntityManager entityManager = mock(EntityManager.class);
        RecordDao recordDao = new RecordDao(UUID.class, RecordModel.class, entityManager);
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();

        RecordModel newRecord = recordDao.create(recordModel);
        assertEquals(recordModel, newRecord);
        verify(entityManager, timeout(1)).persist(newRecord);
    }
    @Test
    void getAllRecordTest() {
        EntityManager entityManager = mock(EntityManager.class);
        RecordDao recordDao = new RecordDao(UUID.class, RecordModel.class, entityManager);
        List<RecordModel> recordModels = Arrays.asList(
                RecordModel.builder().id(UUID.randomUUID()).build()

        );
        TypedQuery<RecordModel> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(RecordModel.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(recordModels);
        List<RecordModel> actualRecords = recordDao.findAll();
        assertEquals(recordModels, actualRecords);
    }
    @Test
    void deleteRecordTest() {
        EntityManager entityManager = mock(EntityManager.class);
        RecordDao recordDao = new RecordDao(UUID.class, RecordModel.class, entityManager);
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();

        recordDao.delete(recordModel);
        verify(entityManager, times(1)).remove(recordModel);

    }
    @Test
    void deleteRecordByIdTest() {
        EntityManager entityManager = mock(EntityManager.class);
        RecordDao recordDao = new RecordDao(UUID.class, RecordModel.class, entityManager);
        UUID recordIdToDelete = UUID.randomUUID();
        RecordModel recordModel = RecordModel.builder().id(UUID.randomUUID()).build();
        when(entityManager.find(eq(RecordModel.class), eq(recordIdToDelete))).thenReturn(recordModel);
        recordDao.deleteById(recordIdToDelete);
        verify(entityManager, times(1)).remove(recordModel);

    }


}
