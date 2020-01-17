# FreezerApplication

## Table of Contents

* [Features](#feat)
* [Technologies](#tech)
  * [Languages Used and Tools](#lang)
    * [Back-End Application](#BE)
    * [Front-End Application](#fe)
  * [Architecture](#arch)
  * [Browser Support](#Browser)
* [How to Use](#htu)
  * [Home Page](#hp)
  * [Freezer Page](#fp)
  * [Individual Freezer Page](#ifp)

<a name="feat"></a>
## Features

The features in my application are the basic **CRUD** functionality. ..
* **Create** - In my application you have the ability to create a freezer and to create an item in said freezer
* **Read** - In my application you have the ability to read the freezers from the database as well as the items from the database
* **Update** - In my application you have the ability to update the quantity of an item in a freezer
* **Delete** - In my application you have the ability to delete both items from a freezer and an entire freezer

<a name="tech"></a>
## Technologies

<a name="lang"></a>
### Languages Used and Tools

<a name="BE"></a>
#### Back-End Application

My back-end application was built in Spring Tool Suite (version 2.2.2) and coded in Java (version 11), It is organised as a Restful API.

<a name="fe"></a>
#### Front-End Application

My front-end application was built in Visual Studio Code (version 1.41.1) and coded in HTML 5, JavaScript, CSS.

<a name="arch"></a>
### Architecture

![picture](https://i.ibb.co/G06YmrB/architecture.png)

<a name="Browser"></a>
### Browser Support

![Chrome](https://github.com/alrra/browser-logos/blob/master/src/chrome/chrome_48x48.png) | ![Firefox](https://github.com/alrra/browser-logos/blob/master/src/firefox/firefox_48x48.png)
--- | --- |
Latest :heavy_check_mark: | Latest :heavy_check_mark:

Chrome has been tested with Selenium, Firefox has only been manually tested.

<a name="htu"></a>
## How to Use

<a name="hp"></a>
### Home Page
When you first load the front end you will be taken to the home page which simply describes the application. ..
It has a nav bar that allows you to move to the freezer page by clicking Freezers. ..
At the bottom of the page it has a GitHub logo, which if you click this will take you to this Git Repo.

<a name="fp"></a>
### Freezer Page
This is the page where you would see, add and/or delete your freezers. ..

To add you would input a freezer name into the input box under "Add a Freezer" and click add, this would then be displayed in the table. ..

To delete you would input a freezer name into the input box under "Delete a Freezer" and click delete, this would then be removed from the table. ..

The add and delete is not case sensitive, when you add or delete a freezer it will automatically convert the string to lowercase. ..

To get to the Individual Freezer Page you simply click the freezer name that you would like to go to. ..

In the freezer name you can not have any numbers or special characters and it must be at least 6 characaters long.

<a name="ifp"></a>
### Individual Freezer Page
This is the page where you would see, add, edit and/or delete items in a specific freezer. ..

To add you would input a item name and quantity into the input boxes under "Add an Item" and click add, this would then be displayed in the table. ..

To edit you would input a item name and quantity into the input boxes under "Edit an Item" and click edit, this would then be removed from the table. ..

To delete you would input a item name and quantity into the input boxes under "Delete an Item" and click delete, this would then be removed from the table. ..

The add, edit and delete is not case sensitive, when you add, edit or delete an item it will automatically convert the string to lowercase. ..

In the item name you can not have any numbers or special characters and it must be at least 3 characaters long. ..

In the quantity you can not have any letters, special characters, decimals and it must not be 0. ..

To get back to the freezers page you simply click Freezers on the nav bar.
