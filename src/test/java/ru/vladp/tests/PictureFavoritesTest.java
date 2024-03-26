package ru.vladp.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ru.vladp.config.BaseTest;
import ru.vladp.pages.FavoritePage;
import ru.vladp.pages.HomePage;
import ru.vladp.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureFavoritesTest extends BaseTest {

    /* Тест 3
     * Перейти в "Батик", добавить первую картину в избранное, проверить,
     * что выбранная картина сохранилась в разделе "Избранное". */
    @Test(testName = "Проверка добавления первой картины из раздела 'Батик' в 'Избранное'")
    public void addingCheckOfPictureToFavorites() {
        log.info("Starting test: Navigate to 'Батик', add the first picture to 'Избранное', " +
                "check that it is saved in 'Избранное'.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());
        FavoritePage favoritePage = new FavoritePage(getDriver());

        home.clickShowMoreOnCategory();
        home.clickMenuItem("Батик");

        String pictureName = picture.putInFavoriteSpecificPicture(0);

        favoritePage.openFavoriteListPage();
        boolean isExist = favoritePage.checkPictureInFavorite(pictureName);

        assertTrue(isExist);
    }
}
