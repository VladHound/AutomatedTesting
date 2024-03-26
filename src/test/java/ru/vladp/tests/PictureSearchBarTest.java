package ru.vladp.tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import ru.vladp.config.BaseTest;
import ru.vladp.pages.HomePage;
import ru.vladp.pages.PicturePage;

import static org.testng.Assert.assertTrue;

@Slf4j
public class PictureSearchBarTest extends BaseTest {

    /* Тест 4
     * Ввести в поисковую строку "Жираф", проверить, что название первой
     * картины содержит слово "Жираф". */
    @Test(testName = "Проверка поиска первой картины со словом 'Жираф'")
    public void searchCheckOfGiraffePicture() {
        log.info("Starting test: Search for 'Жираф' in the search bar, " +
                "check that the title of the first picture contains 'Жираф'.");

        HomePage home = new HomePage(getDriver());
        PicturePage picture = new PicturePage(getDriver());

        String request = "Жираф";

        home.enteringTextInSearchField(request);
        boolean isContains = picture.checkContainsRequestInName(request);

        assertTrue(isContains);
    }
}
