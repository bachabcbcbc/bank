# Serenity Mobile Screenplay Example

## Running Mobile Tests with Appium

Before running, make sure:
- The Appium server is running on port **4724** for Android or **4723** for iOS.
- Your `android.properties` and `ios.properties` files are configured for your devices and app paths.

### Step1: Run appium server
```sh
appium --port 4724 --base-path /wd/hub --allow-cors
```

### Step2: get the simulator devices
```sh
adb device
```

### Step3: config android & ios properties file follow by simulators's properties


### Step 4: Run Android tests
```sh
mvn clean verify -Dproperties=android.properties
```

### or Run iOS tests
```sh
mvn clean verify -Dproperties=ios.properties
```

### or Run individual test
```sh
mvn clean test -Dproperties=android.properties -Dtest=WhenUserStartAppStory
```



### Run with Cucumber profile
If you want to use the Cucumber profile, add `-Pcucumber`:
```sh
# Android
mvn clean verify -Dproperties=android.properties -Pcucumber

# iOS
mvn clean verify -Dproperties=ios.properties -Pcucumber
```

---

**Tip:**
- Start the Appium server before running the tests.
- Update the `appium.app` path in the properties files to your actual app location. 