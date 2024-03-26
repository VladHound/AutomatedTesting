package ru.vladp.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ru.vladp.config.BaseTest;
import ru.vladp.models.Picture;
import ru.vladp.pages.CartPage;
import ru.vladp.pages.HomePage;
import ru.vladp.pages.PicturePage;

import java.io.ByteArrayInputStream;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureCartPriceTest extends BaseTest {

    /* Тест 5
     * Перейти в "Ювелирное искусство", добавить первое изделие в
     * корзину, проверить, что выбранный товар находится в корзине, стоимость
     * товара не изменилась. */
    @Test(testName = "Проверка добавления первой картины из раздела 'Ювелирное искусство' в 'Корзину'")
    public void addingCheckOfPictureToCart() {
        log.info("Starting test: Navigate to 'Ювелирное искусство', add the first picture to 'Корзина'," +
                "check that it is in 'Корзина', check that the price is the same.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        CartPage cart = new CartPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Ювелирное искусство");
        Picture firstPicture = picture.putInCartSpecificPicture(0);

        cart.openCartFromModalPage();
        boolean isExistAndEqualPrice = cart.checkPictureInCart(firstPicture);

        assertTrue(isExistAndEqualPrice);
    }

    /* Тест 6 (ошибочный)
     * Перейти в "Ювелирное искусство", добавить первое изделие в
     * корзину, проверить, что выбранный товар находится в корзине, стоимость
     * товара не изменилась. */
    @Test(testName = "Неудачное добавление первого изделия в корзину в разделе 'Ювелирное искусство'")
    @Description("Результат искусственно был сделан ошибочным")
    @Attachment(value = "Failure screenshot", type = "image/png")
    public void addingCheckOfPictureToCartFailed() {
        log.info("Starting test: Navigate to 'Ювелирное искусство', add the first picture to 'Корзина'," +
                "check that it is in 'Корзина', check that the price is the same.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        CartPage cart = new CartPage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Ювелирное искусство");
        Picture firstPicture = picture.putInCartSpecificPicture(0);

        cart.openCartFromModalPage();
        boolean isExistAndEqualPrice = cart.checkPictureInCart(firstPicture);

        try {
            assertFalse(isExistAndEqualPrice);
        } catch (AssertionError e) {
            byte[] bytes = captureScreenshot(getDriver());
            Allure.addAttachment("Failure screen", new ByteArrayInputStream(bytes));
            Allure.addAttachment("Failure details", "Тест завершился неудачей: " + e.getMessage());
            throw e;
        }
    }
}
