package org.automation.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class FlexDriver {

    private WebDriver webDriver = null;
    private final String flashObjectId;


    public FlexDriver(WebDriver driver, final String flashObjectId) {
        this.webDriver = driver;
        this.flashObjectId = flashObjectId;
    }

    public void quit(){
        webDriver.quit();
    }

    public void get(String url){
        webDriver.get(url);
    }

    public String click(final String objectId, final String optionalButtonLabel) {
        return call("doFlexClick", objectId, optionalButtonLabel);
    }

    public String click(final String objectId) {
        return click(objectId, "");
    }

    public void sendKeys(String objectId, String value){
        call("doFlexType", objectId, value);
    }

    public String getText(String objectId){
        return call("getFlexText", objectId, "dummy");
    }

    private String call(final String functionName, final String... args) {
        final Object result =   ((JavascriptExecutor)webDriver).executeScript(
                    makeJsFunction(functionName, args),
                    new Object[0]);

        return result != null ? result.toString() : null;
    }

    private String makeJsFunction(final String functionName, final String... args) {
        final StringBuffer functionArgs = new StringBuffer();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    functionArgs.append(",");
                }
                functionArgs.append(String.format("'%1$s'", args[i]));
            }
        }
        return String.format(
                "return document.%1$s.%2$s(%3$s);",
                flashObjectId,
                functionName,
                functionArgs);
    }

    public String waitForElement(String objectid, String timeOut){
        return call("doFlexWaitForElement", objectid, timeOut);
    }

}
