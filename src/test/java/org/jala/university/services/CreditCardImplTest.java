package org.jala.university.services;

import org.jala.university.Services.CreditCardImpl;
import org.jala.university.dao.CreditCardDao;
import org.jala.university.model.CreditCardModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
    void cancelCreditCardTest() {
        // Configurar datos de prueba
        UUID cardId = UUID.randomUUID();

        // Configurar el comportamiento del mock para devolver un mock de CreditCardModel
        CreditCardModel mockCreditCardModel = mock(CreditCardModel.class);
        when(creditCardDao.findOne(cardId)).thenReturn(mockCreditCardModel);


        // Realizar la llamada al método que se está probando
        boolean result = creditCardImpl.cancelCreditCard(cardId);

        // Verificar el resultado esperado
        assertTrue(result, "La cancelación de la tarjeta debería ser exitosa");

        // Verificar que se haya llamado al método de actualizar en CreditCardDao
        verify(creditCardDao, times(1)).update(any(CreditCardModel.class));
    }


    // Agregar más pruebas para otros métodos relacionados con la cancelación de tarjetas...
}
