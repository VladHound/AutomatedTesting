package ru.vladp.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ru.vladp.config.BaseTest;
import ru.vladp.pages.CatalogPage;
import ru.vladp.pages.HomePage;
import ru.vladp.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureExistenceTest extends BaseTest {

    /* Тест 1
     * Перейти в "Вышитые картины", произвести поиск по жанру
     * "Городской пейзаж", проверить, что картина "Трамвайный путь"
     * присутствует в выдаче. */
    @Test(testName = "Проверка существования картины 'Трамвайный путь' " +
            "жанра 'Городской пейзаж' в разделе 'Вышитые картины'")
    public void existenceCheckOfTramwayPicture() {
        log.info("Starting test: Navigate to 'Вышитые картины', search for 'Городской пейзаж', " +
                "check the existence of 'Трамвайный путь'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Вышитые картины");
        catalog.selectPictureGenre("Городской пейзаж");
        boolean isCatalogExists = picture.isPictureByNameExists("Трамвайный путь");

        assertTrue(isCatalogExists);
    }
}
