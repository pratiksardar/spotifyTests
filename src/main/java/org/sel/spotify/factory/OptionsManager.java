package org.sel.spotify.factory;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class OptionsManager {


    private final Properties prop;

    private ChromeOptions co;

    public OptionsManager(Properties prop){
        this.prop =prop;
    }

    public ChromeOptions getChromeOptions(){
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))){
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        co.addArguments("--log-level=3");
        return  co;
    }


}
