package com.cybertek.library.utilities.ui;

import com.cybertek.library.pages.DashBoardPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.pages.UsersPage;

public class Pages {
    private LoginPage loginPage ;
    private DashBoardPage dashboardPage;
    private UsersPage usersPage;

    public Pages(){
        this.loginPage = new LoginPage();
        this.dashboardPage = new DashBoardPage();
        this.usersPage = new UsersPage();
    }

}
