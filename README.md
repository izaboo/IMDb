# IMDb
Repository for hosting web automation challenge.
Java project for testing IMDb search and sort capabilities

General overview of the approach:
 - Selenium Webdriver + TestNG + Maven are used for setting up and building project as well as organize tests
 - pageObject pattern was used to describe and incapsulate behaviour of pages within corresponded classes
 - 1 main class for tests preparation (driver init, opening start page, closing browser) used
 - tests' classes inherit from preparations
 - standart testng reports used with surefile pluging as builder

Further improvements which can be made
 - add fine reporting
 - analyse necessity of moving webelements locators to properties file or other storage

Note: Main comments are inside code itself.

Current tests confirm:

1. The Top 250 (http://www.imdb.com/chart/top) page returns at least 1 movie​, in the list.

2. The previous should be true, for each of the sorting options:​

3. The previous should also be true, when navigating to the Western​genre:
