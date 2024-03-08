package com.enoca.ecommercetest.page;

import com.enoca.ecommercetest.utility.BaseClass;
import com.enoca.ecommercetest.utility.FunctionLibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author : tolunaybisar
 * @created : 4.03.2024,23:28
 * @Email :tolunay.bisar@gmail.com
 **/
public class MyCartPage extends BaseClass {
    FunctionLibrary functionLibrary;
    DashBoardPage dashBoardPage;
    CheckOutPage checkOutPage;
    int qtyBeforeAdd;
    @FindAll(@FindBy(xpath = "//button[@onclick=\"deleteItemClick($(this))\"]"))
    List<WebElement> listOfDeleteButton;
    @FindBy(xpath = "//span[text()=\"Sepetim\"]")
    WebElement labelSepet;
    @FindBy(xpath = "//button[@onclick=\"acceptRemove()\"]")
    WebElement buttonSil;
    @FindBy(xpath = "//div[@class='cl-empty-cart-container']")
    WebElement labelSepetBos;
    @FindBy(xpath = "//button[@onclick=\"setLocation('/')\"]")
    WebElement buttonAlisverisBaslat;
    @FindBy(xpath = "//div[@class=\"cl-input-plus-button\"]")
    WebElement buttonArti;
    @FindBy(xpath = "//input[@class=\"cl-product-quantity-input\"]")
    WebElement qtyOfItem;


    public MyCartPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        functionLibrary= new FunctionLibrary();
        dashBoardPage = new DashBoardPage(driver);
        checkOutPage= new CheckOutPage(driver);
    }
    public void clearSepetim(){
        dashBoardPage.openSepetim();
        functionLibrary.waitForElementPresent(labelSepet);

        while (listOfDeleteButton.size()>0){
            functionLibrary.waitForElementPresentClick(listOfDeleteButton.get(0));

                listOfDeleteButton.get(0).click();
                functionLibrary.waitForElementPresent(buttonSil);
                buttonSil.click();

            }

    }
    public boolean verifySepetimEmpity(){
         functionLibrary.waitForElementPresent(labelSepetBos);
        return  labelSepetBos.getText().contains("Alışveriş sepetiniz boş!");

    }
    public void openShoppingPage(){
        functionLibrary.waitForElementPresentClick(buttonAlisverisBaslat);
        buttonAlisverisBaslat.click();

    }
    public int addItemInCart(){
        //checkOutPage.closeWarningAlert();
        dashBoardPage.openSepetim();
        functionLibrary.waitForElementPresent(qtyOfItem);
        qtyBeforeAdd= Integer.parseInt(qtyOfItem.getAttribute("value"));
        functionLibrary.waitForElementPresentClick(buttonArti);
        buttonArti.click();
        return qtyBeforeAdd;
    }
    public boolean verifyItemAddedInCart(){
        MyCartPage myCart= new MyCartPage(driver);
       functionLibrary.waitForElementPresent(qtyOfItem);
       int qtyAfterAdd= Integer.parseInt(qtyOfItem.getAttribute("value"));
       return qtyAfterAdd-(qtyBeforeAdd)==1;

    }
}
