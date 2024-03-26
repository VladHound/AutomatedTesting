package ru.vladp.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ru.vladp.config.BaseTest;
import ru.vladp.pages.CatalogPage;
import ru.vladp.pages.HomePage;
import ru.vladp.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureStyleTest extends BaseTest {

    /* Тест 2
     * Перейти в "Вышитые картины", произвести поиск по жанру
     * "Городской пейзаж", открыть подробности картины "Трамвайный путь",
     * проверить, что стиль картины "Реализм". */
    @Test(testName = "Проверка стиля 'Реализм' у картины 'Трамвайный путь' " +
            "жанра 'Городской пейзаж' в разделе 'Вышитые картины'")
    public void styleCheckOfTramwayPicture() {
        log.info("Starting test: Navigate to 'Вышитые картины', search for 'Городской пейзаж', " +
                "open details of 'Трамвайные пути', check style - 'Реализм'.");

        HomePage home = new HomePage(getDriver());
        CatalogPage catalog = new CatalogPage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Вышитые картины");
        catalog.selectPictureGenre("Городской пейзаж");

        boolean isCatalogExists = picture.isPictureByNameExists("Трамвайный путь");

        if (isCatalogExists) {
            picture.openPictureInfo("Трамвайный путь");
            boolean isRealism = picture.checkStylePicture("Реализм");

            assertTrue(isRealism);
        }
    }
}
