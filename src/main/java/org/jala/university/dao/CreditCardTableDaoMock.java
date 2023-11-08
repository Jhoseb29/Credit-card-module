package org.jala.university.dao;

import org.jala.university.model.CreditCardTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreditCardTableDaoMock extends CreditCardTableDao{
    private final Map<UUID, CreditCardTable> cardFormMap = new HashMap<>();
    public CreditCardTableDaoMock() {
        super(null);
    }
    @Override
    public CreditCardTable findOne(UUID ruleGroupId) {
        return cardFormMap.get(ruleGroupId);
    }

    @Override
    public List<CreditCardTable> findAll() {
        return cardFormMap.values().stream().toList();
    }

    @Override
    public CreditCardTable create(CreditCardTable entity) {
        UUID id = UUID.randomUUID();
        entity.setId(id);
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public CreditCardTable update(CreditCardTable entity) {
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(CreditCardTable entity) {
        cardFormMap.remove(entity.getId());
    }

    @Override
    public void deleteById(UUID entityId) {
        cardFormMap.remove(entityId);
    }
}
