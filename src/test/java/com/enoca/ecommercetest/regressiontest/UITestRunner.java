package com.enoca.ecommercetest.regressiontest;

import com.enoca.ecommercetest.page.*;
import com.enoca.ecommercetest.utility.BaseClass;
import com.enoca.ecommercetest.utility.FunctionLibrary;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author : tolunaybisar
 * @created : 4.03.2024,23:59
 * @Email :tolunay.bisar@gmail.com
 **/

//@Listeners(TestResultListener.class)
public class UITestRunner extends BaseClass {
    CarkifelekPage carkifelekPage;
    DashBoardPage dashBoardPage;
    LoginPage loginPage;
    MyCartPage myCartPage;
    AddToCartPage addToCartPage;
    CheckOutPage checkOutPage;
    LogOutPage logOutPage;


    @BeforeClass
    public void setUp() {
        setUpBrowser(FunctionLibrary.readFromConfig("config.properties",
                "url"));
        carkifelekPage = new CarkifelekPage(driver);
        dashBoardPage = new DashBoardPage(driver);
        loginPage = new LoginPage(driver);
        myCartPage = new MyCartPage(driver);
        addToCartPage = new AddToCartPage(driver);
        checkOutPage = new CheckOutPage(driver);
        logOutPage = new LogOutPage(driver);
    }


    @Test(priority = 1,enabled = false)
    public void carkifelekAllertClicked() {
        dashBoardPage.closeCookie();
        carkifelekPage.clickOnCarkifelek();
        carkifelekPage.getDiscountCode();
      Assert.assertTrue(carkifelekPage.verifyDiscountCodeSuccessfullyGotten());


    }
    @Test(priority = 2,enabled = false )
    public void openDashboard(){
        carkifelekPage.closeCarkifelek();
    }

    @Test(priority = 3)
    public void verifyDashBoardOpened() {
        carkifelekPage.verifyDashBoardPageOpened();
    }

    @Test(priority = 4,dependsOnMethods ="openDashboard" )
    public void logIn() {
        dashBoardPage.openHesabim();
        loginPage.logIn();
        Assert.assertTrue(loginPage.verifyLogin());

    }
    @Test(priority = 5)
    public void checkMyCart(){
        dashBoardPage.openSepetim();
        myCartPage.clearSepetim();
        Assert.assertTrue(myCartPage.verifySepetimEmpity());
    }

    @Test(priority = 6,dependsOnMethods = "logIn")
    public void addToCart(){
        //myCartPage.openShoppingPage();
        dashBoardPage.clickTabOnDashBoard();
        addToCartPage.addToCart();
        Assert.assertTrue(addToCartPage.verifyAddToCart());

    }
@Test(priority = 7,dependsOnMethods ="addToCart" )
public void checkOut(){
        checkOutPage.checkOut();


}
@Test(priority = 8,dependsOnMethods = "checkOut")
public void verifyCheckOut(){
        checkOutPage.verifyCheckOut();

}
@Test(priority = 9,dependsOnMethods = "logIn")
public void addItemQtyInCart(){
        myCartPage.addItemInCart();
}

@Test(priority = 10,dependsOnMethods = "addItemQtyInCart")
public void verifyAddItemQtyInCart(){
        myCartPage.verifyItemAddedInCart();
}
@Test(priority =11,dependsOnMethods = "logIn",enabled = false)
public void logOut(){
        logOutPage.logOut();

}
@Test(priority = 12,dependsOnMethods = "logOut",enabled = false)
public void verifyLogOut(){
        logOutPage.verifyLogOut();
}


    @AfterClass(enabled = false)
    public void tearDown() {
        closeBrowser();

    }
}

