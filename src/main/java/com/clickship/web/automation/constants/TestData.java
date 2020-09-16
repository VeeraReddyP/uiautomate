package com.clickship.web.automation.constants;


public class TestData {

    public static final int timeOut =30;
    public static  final int pollTime =5;

    //shipTo details
    public static  final String companyName ="test";
    public static  final String companyAddress =" 77 Pillsworth Rd";
    public static  final String companyPostal ="L7E4G4";
    public static  final String companyPhone ="123467898";
    public static  final String companyNameExt ="test123";
    public static  final String productId = "prodId_"+Math.random();
    public static  final String productUnitValue ="10";

    //valid login details
    public static  final String username = "useraug19_2";
    public static  final String password ="admin123";

    //in-valid login details
    public static  final String invalid_username = "abcxyz";
    public static  final String invalid_password ="12345";
    public static  final String invalidLoginMessage = "User does not exist in Freightcom or ClickShip. Please click";

    // assert amazon popup after login
    public static  final String amazonWelcomeText = "Amazon Integration is Here!";


}
