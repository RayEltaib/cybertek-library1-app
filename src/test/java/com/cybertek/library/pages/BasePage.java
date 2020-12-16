package com.cybertek.library.pages;

import com.cybertek.library.utilities.ui.BrowserUtils;
import com.cybertek.library.utilities.ui.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage(){ PageFactory.initElements(Driver.getDriver(),this); }


    @FindBy(xpath = "//span[.='Dashboard']")
    public WebElement dashboardLink;

    @FindBy(xpath = "//span[.='Users']")
    public WebElement usersLink;

    @FindBy(xpath = "//span[.='Books']")
    public WebElement booksLink;

    @FindBy(xpath = "//span[.='Borrowing Books']")
    public WebElement borrowedBooks;

    @FindBy(id="navbarDropdown")
    public WebElement accountHolderName;

    @FindBy(xpath = "//a[.='Log Out']")
    public WebElement logOutButton;

    public void navigateTo(String link){
        BrowserUtils.waitTillClickable(dashboardLink,5);
        switch (link) {
            case "Dashboard":
                dashboardLink.click();
                break;
            case "Users":
                usersLink.click();
                break;
            case "Books":
                booksLink.click();
                break;
            case "Borrowing Books":
                borrowedBooks.click();
                break;
        }


    }
    public  void logOut(){

            BrowserUtils.waitTillClickable(accountHolderName, 5);
            accountHolderName.click();
            logOutButton.click();


    }




}
