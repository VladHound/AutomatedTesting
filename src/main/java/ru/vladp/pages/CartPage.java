package ru.vladp.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Listeners;
import ru.vladp.config.AllureListener;
import ru.vladp.models.Picture;

import java.util.List;

import static ru.vladp.utils.PictureServiceSupport.getPictureName;
import static ru.vladp.utils.PictureServiceSupport.getPicturePrice;

@Listeners(AllureListener.class)
public class CartPage extends BasePage {

    @FindBy(css = "img[alt='Избранное']")
    private WebElement cartButton;

    @FindBy(css = "button[onclick='sendCartForm();']")
    private WebElement cartModalButton;

    @FindBy(css = "div[class='c_row']")
    private List<WebElement> cartPictures;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the cart from the modal page")
    public void openCartFromModalPage() {
        waitForElementEnable(cartModalButton);
        cartModalButton.click();
    }

    @Step("Check that the picture {picture.getName()} is in the cart with the price {picture.getPrice()}")
    public boolean checkPictureInCart(Picture picture) {
        waitForAllElementsTimeoutSeconds(cartPictures);

        String name = picture.name();

        if (!cartPictures.isEmpty()) {
            for (var pic : cartPictures) {

                String pictureName = getPictureName(pic, "c_name");

                if (pic.getText().contains(name) || name.contains(pictureName)) {
                    long picPrice = getPicturePrice(pic);
                    if (picPrice == picture.price()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
