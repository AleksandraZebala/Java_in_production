package uj.jwzp.w2.e3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.modules.junit4.PowerMockRunner;
import uj.jwzp.w2.e3.external.PersistenceLayer;

import java.math.BigDecimal;

@RunWith(PowerMockRunner.class)
public class SellingServiceTest {

    @Mock
    private PersistenceLayer persistenceLayer;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void notSell() {
        //given
        SellingService uut = new SellingService(persistenceLayer);
        Mockito.when(persistenceLayer.saveCustomer((Customer)Mockito.any())).thenReturn(Boolean.TRUE);
        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Krakow, Lojasiewicza");

        //when
        boolean sold = uut.sell(i, 7, c, new DiscountsConfigWrapper());

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(10), uut.moneyService.getMoney(c));
    }

    @Test
    public void sell() {
        //given
        SellingService uut = new SellingService(persistenceLayer);
        Mockito.when(persistenceLayer.saveCustomer((Customer)Mockito.any())).thenReturn(Boolean.TRUE);
        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Krakow, Lojasiewicza");

        //when
        boolean sold = uut.sell(i, 1, c, new DiscountsConfigWrapper());

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(7), uut.moneyService.getMoney(c));
    }

    @Test
    public void sellALot() {
        //given
        DiscountsConfigWrapper discountsConfigWrapper =  Mockito.mock(DiscountsConfigWrapper.class);
        SellingService uut = new SellingService(persistenceLayer);

        Mockito.when(persistenceLayer.saveCustomer((Customer)Mockito.any())).thenReturn(Boolean.TRUE);
        Item i = new Item("i", new BigDecimal(3));
        Customer c = new Customer(1, "DasCustomer", "Krakow, Lojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(990));

        Mockito.when(discountsConfigWrapper.isWeekendPromotion()).thenReturn(Boolean.FALSE);
        Mockito.when(discountsConfigWrapper.getDiscountForItem(i, c)).thenReturn(BigDecimal.ZERO);

        //when
        boolean sold = uut.sell(i, 10, c, discountsConfigWrapper);

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(970), uut.moneyService.getMoney(c));
    }

    @Test
    public void sellWithDiscounts(){
        //given
        DiscountsConfigWrapper discountsConfigWrapper =  Mockito.mock(DiscountsConfigWrapper.class);
        SellingService uut = new SellingService(persistenceLayer);

        Mockito.when(persistenceLayer.saveCustomer((Customer)Mockito.any())).thenReturn(Boolean.TRUE);
        Item i = new Item("i", new BigDecimal(10));
        Customer c = new Customer(1, "DasCustomer", "Krakow, Lojasiewicza");
        uut.moneyService.addMoney(c, new BigDecimal(90));

        Mockito.when(discountsConfigWrapper.isWeekendPromotion()).thenReturn(Boolean.TRUE);
        Mockito.when(discountsConfigWrapper.getDiscountForItem(i, c)).thenReturn(new BigDecimal(2));

        //when
        boolean sold = uut.sell(i, 1, c, discountsConfigWrapper);

        //then
        Assert.assertFalse(sold);
        Assert.assertEquals(BigDecimal.valueOf(95), uut.moneyService.getMoney(c));
    }
}
