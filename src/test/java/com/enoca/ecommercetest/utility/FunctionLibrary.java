package com.enoca.ecommercetest.utility;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * @author : tolunaybisar
 * @created : 4.03.2024,23:29
 * @Email :tolunay.bisar@gmail.com
 **/
public class FunctionLibrary extends BaseClass{
    public static String readFromConfig(String fileName, String key) {
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String value = properties.getProperty(key);
        return value;
    }

    public void waitForElementPresent(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(
                readFromConfig("config.properties","timeout"))));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementPresentClick(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(
                readFromConfig("config.properties","timeout"))));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementPresentSelect(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(
                readFromConfig("config.properties","timeout"))));
        wait.until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitForElementPresentAllert(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(
                readFromConfig("config.properties","timeout"))));
        wait.until(ExpectedConditions.alertIsPresent());
    }


    public void sleep(int seconds){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void javaScripClick(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);

    }


    public String timeStamp() {
        long timeStamp = System.currentTimeMillis();
        return (Long.toString(timeStamp).toString().substring(8));
    }

    public String randomText(){
        String randomText = RandomStringUtils.randomAlphabetic(4);
        return randomText;

    }

    public String generateFakeName(){
        String fakeName = Faker.instance().name().firstName();
        return fakeName;
    }
    public String generateFakerCartNo(){
        String creditCartNo = Faker.instance().finance().creditCard();
        return creditCartNo;
    }




}
