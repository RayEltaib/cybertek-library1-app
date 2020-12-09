package com.cybertek.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(id="inputEmail")
    public WebElement emailElement;

    @FindBy(id="inputPassword")
    public WebElement passwordElement;

    @FindBy(xpath ="//*[.='Sign in']")
    public WebElement signInButton;

    public void login(String email, String password){


    }

}
