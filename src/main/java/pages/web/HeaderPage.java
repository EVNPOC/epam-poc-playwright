package pages.web;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.Objects;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HeaderPage extends Common{
    private final Page headerPage;
    private static final String LOCATION_MENU_BTN = "button.location-selector__button";
    private static final String CONTACTUS_MENU_BTN = ".header__content>a[data-gtm-category='header-contact-cta']>span";
    private static final String SERVICES_LINK = "li>span>a[href='/services']";
    private static final String INSIGHT= "li>span>a[href='/insights']";
    private static final String HOVER_ITEM=".js-opened";
    private static final String ABOUT = "li>span>a[href='/about']";
    private static final String CAREER = "li>span>a[href='/careers']";

    private static final String MAIN_NAVIGATION = ".top-navigation-ui-23";
    private static final String INDUSTRIES = "ul>li[class='top-navigation__item epam']:nth-child(2)";
    public HeaderPage(Page page) {
        super(page);
        this.headerPage = page;
    }

    public void clickMainNavigationOption(String mainNavigationName) {
        if(Objects.equals(mainNavigationName, "Contact us")) {
            headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("CONTACT US")).click();
        }
        else {
            headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(mainNavigationName)).last().click();
        }
    }

    public void verifyCorrespondingNavigation(String navigationName) {
        assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(navigationName)).first()).isVisible();

    }
    public void clickLocationMenu() {
        headerPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Global (EN)")).click();
    }

    public void clickLocationOption(String locationName) {
        headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(locationName)).click();
    }

    public void verifyLocationDefaultTextDisplayed() {
        assertThat(headerPage.locator(LOCATION_MENU_BTN)).hasText("Global (EN)");
    }

    public void verifyLocationMenuDisplayed() {
        assertAll(
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Global (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Česká Republika (Čeština)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Czech Republic (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("DACH (Deutsch)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hungary (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("India (English)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("日本 (日本語)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Polska (Polski)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("СНГ (Русский)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Україна (Українська)"))).isVisible(),
                () -> assertThat(headerPage.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("中国 (中文)"))).isVisible()
        );
    }

    public void verifySwitchLocationSuccessful(String title, String url) {
        assertAll(
                () -> assertThat(headerPage).hasTitle(Pattern.compile(title)),
                () -> assertThat(headerPage).hasURL(Pattern.compile(url))
        );
    }

    public void verifyContactUsItemDisplayed() {

        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).isVisible();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasText("CONTACT US");
    }

    public void verifyColorContactUsItem() {
        headerPage.locator(CONTACTUS_MENU_BTN).hover();
        assertThat(headerPage.locator(CONTACTUS_MENU_BTN)).hasCSS("background-color", "rgb(255, 255, 255)");
    }
    public void verifyServicesLabelDisplayed() {
        headerPage.locator(SERVICES_LINK).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }
    public void verifyInsightsLabelDisplayed() {
        headerPage.locator(INSIGHT).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }
    public void verifyAboutsLabelDisplayed() {
        headerPage.locator(ABOUT).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }
    public void verifyCareerLabelDisplayed() {
        headerPage.locator(CAREER).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }
    public void verifyIndustriesLabelDisplayed() {
        headerPage.locator(INDUSTRIES).hover();
        assertThat(headerPage.locator(HOVER_ITEM)).isVisible();
    }

    public void verifyMainNavigationDisplayedTop() {
        assertThat(headerPage.locator(MAIN_NAVIGATION)).isVisible();
    }

    public void verifyColorChangeOfServices() {
        assertThat(headerPage.locator(SERVICES_LINK)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(SERVICES_LINK).hover();
        assertThat(headerPage.locator(SERVICES_LINK)).hasCSS("color", "rgb(0, 246, 255)");
    }

    public void verifyColorChangeOfIndustries() {
        assertThat(headerPage.locator(INDUSTRIES)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(INDUSTRIES).hover();
        assertThat(headerPage.locator(INDUSTRIES)).hasCSS("color", "rgb(0, 246, 255)");
    }

    public void verifyColorChangeOfInsight() {
        assertThat(headerPage.locator(INSIGHT)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(INSIGHT).hover();
        assertThat(headerPage.locator(INSIGHT)).hasCSS("color", "rgb(0, 246, 255)");
    }

    public void verifyColorChangeOfAbout() {
        assertThat(headerPage.locator(ABOUT)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(ABOUT).hover();
        assertThat(headerPage.locator(ABOUT)).hasCSS("color", "rgb(0, 246, 255)");
    }

    public void verifyColorChangeOfCareer() {
        assertThat(headerPage.locator(CAREER)).hasCSS("color", "rgb(255, 255, 255)");
        headerPage.locator(CAREER).hover();
        assertThat(headerPage.locator(CAREER)).hasCSS("color", "rgb(0, 246, 255)");
    }

    public void ClickOnServicesItem(){
        headerPage.locator(SERVICES_LINK).click();

    }
}
