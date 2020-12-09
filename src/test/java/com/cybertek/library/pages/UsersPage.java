package com.cybertek.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UsersPage extends BasePage {

    @FindBy(name="tbl_users_length")
    public WebElement showRecords;

    //@FindBy(xpath = "//thead/tr[@role='row']") List without comma very Interesting
    @FindBy(tagName = "th")
    public List<WebElement> usersTableHeaders;

    @FindBy(xpath = "f")
    public List<WebElement> recordsDropDown;
}
