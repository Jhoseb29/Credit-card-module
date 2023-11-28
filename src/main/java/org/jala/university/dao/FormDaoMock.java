package org.jala.university.dao;

import org.jala.university.model.FormModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FormDaoMock extends FormDao {
    private final Map<UUID, FormModel> cardFormMap = new HashMap<>();

    public FormDaoMock() {
        super(null);
    }

    @Override
    public FormModel findOne(UUID ruleGroupId) {
        return cardFormMap.get(ruleGroupId);
    }

    @Override
    public List<FormModel> findAll() {
        return cardFormMap.values().stream().toList();
    }

    @Override
    public FormModel create(FormModel entity) {
        UUID id = UUID.randomUUID();
        entity.setId(id);
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public FormModel update(FormModel entity) {
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(FormModel entity) {
        cardFormMap.remove(entity.getId());
    }

    @Override
    public void deleteById(UUID entityId) {
        cardFormMap.remove(entityId);
    }
}
