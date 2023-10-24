package org.jala.university.dao;
import org.jala.university.CreditCardForm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreditCartDaoMock extends CreditCardDao {
    private final Map<UUID, CreditCardForm> cardFormMap = new HashMap<>();
    public CreditCartDaoMock() {
        super(null);
    }
    @Override
    public CreditCardForm findOne(UUID ruleGroupId) {
        return cardFormMap.get(ruleGroupId);
    }

    @Override
    public List<CreditCardForm> findAll() {
        return cardFormMap.values().stream().toList();
    }

    @Override
    public CreditCardForm create(CreditCardForm entity) {
        UUID id = UUID.randomUUID();
        entity.setId(id);
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public CreditCardForm update(CreditCardForm entity) {
        return cardFormMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(CreditCardForm entity) {
        cardFormMap.remove(entity.getId());
    }

    @Override
    public void deleteById(UUID entityId) {
        cardFormMap.remove(entityId);
    }
}
