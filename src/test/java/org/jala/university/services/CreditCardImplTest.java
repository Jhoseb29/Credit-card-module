package org.jala.university.services;

import org.jala.university.Services.CreditCardImpl;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.model.CreditCardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CreditCardImplTest {

    private CreditCardDao creditCardDao;
    private CreditCardImpl creditCardImpl;

    @BeforeEach
    void setUp() {
        // Inicialización de objetos de prueba
        creditCardDao = mock(CreditCardDao.class);
        creditCardImpl = new CreditCardImpl(creditCardDao);
    }

    @Test
    void cancelCreditCardByNumberTest() {
        // Configurar datos de prueba
        String cardNumber = "1234567890123456";
        CreditCardModel mockCreditCardModel = mock(CreditCardModel.class);

        // Configurar el comportamiento del mock para devolver un mock de CreditCardModel
        when(creditCardDao.findByCardNumber(cardNumber)).thenReturn(mockCreditCardModel);

        // Realizar la llamada al método que se está probando
        try {
            creditCardImpl.cancelCreditCardByNumber(cardNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Verificar que se haya llamado al método de actualizar en CreditCardDao
        verify(creditCardDao, times(1)).update(any(CreditCardModel.class));
    }


}
