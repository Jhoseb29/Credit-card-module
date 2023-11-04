package org.jala.university.dao;

import org.jala.university.model.CreditCardModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreditCardTableDaoMock extends CreditCardTableDao{
    private final Map<UUID, CreditCardModel> cardFormMap = new HashMap<>();
    public CreditCardTableDaoMock() {
        super(null);
    }
    @Override
    public CreditCardModel findOne(UUID ruleGroupId) {
        return cardFormMap.get(ruleGroupId);
    }

    @Override
    public List<CreditCardModel> findAll() {
        return cardFormMap.values().stream().toList();
    }

    @Override
    public CreditCardModel create(CreditCardModel entity) {
        UUID id = UUID.randomUUID();
        entity.setId(id);
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public CreditCardModel update(CreditCardModel entity) {
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(CreditCardModel entity) {
        cardFormMap.remove(entity.getId());
    }

    @Override
    public void deleteById(UUID entityId) {
        cardFormMap.remove(entityId);
    }
}
