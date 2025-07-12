# Mobile Test Automation Project

This project uses Serenity BDD, Cucumber, Appium, and Selenium for cross-platform mobile test automation.

---

## 🚀 Getting Started

### 1. Prerequisites
Make sure you have the following installed:
- **Java JDK 11 or 8** (`java -version`)
- **Maven** (`mvn -version`)
- **Node.js** (for Appium)
- **Appium 2.x** (`npm install -g appium`)
- **Appium drivers**
  - Android: `appium driver install uiautomator2`
  - iOS: `appium driver install xcuitest`
- **Android Studio** (for Android emulator)
- **Xcode** (for iOS simulator, on macOS)

---

### 2. Clone the Repository
```sh
git clone <your-repo-url>
cd <your-project-folder>
```

---

### 3. Install Maven Dependencies
```sh
mvn clean install -DskipTests
```

---

### 4. Configure Device and App Settings
- Edit the properties files in `src/test/resources/`:
  - `android.properties` for Android
  - `ios.properties` for iOS
- Set the correct paths for your `.apk` or `.app` files, device names, platform versions, etc.

#### **How to get device names/UDIDs:**
- **Android:**
  ```sh
  adb devices
  ```
- **iOS:**
  ```sh
  xcrun simctl list devices
  ```

#### **Example android.properties:**
```
appium.platformName=Android
appium.platformVersion=11
appium.deviceName=emulator-5554
appium.app=src/test/resources/app/your-app.apk
appium.automationName=UiAutomator2
```

#### **Example ios.properties:**
```
appium.platformName=IOS
appium.platformVersion=18.5
appium.deviceName=iPhone 16 Plus
appium.udid=YOUR-DEVICE-UDID
appium.app=src/test/resources/app/your-app.app
appium.automationName=XCUITest
```

---

### 5. Start the Appium Server
- **Android:**
  ```sh
  appium server -p 4724 --base-path /wd/hub --allow-cors
  ```
- **iOS:**
  ```sh
  appium server -p 4725 --base-path /wd/hub --allow-cors
  ```
Or use Appium Desktop and set the port to match your properties file.

---

### 6. Start an Emulator or Simulator
- **Android:** Use Android Studio AVD Manager to start an emulator.
- **iOS:** Use Xcode to start a simulator.

---

### 7. Run the Tests
- **Android Example:**
  ```sh
  mvn clean test -Dproperties=android.properties
  ```
- **iOS Example:**
  ```sh
  mvn clean test -Dproperties=ios.properties
  ```
- **Run individual test:**
  ```sh
  mvn clean test -Dproperties=android.properties -Dtest=WhenUserStartAppStory
  ```
- **Run with Cucumber profile:**
  ```sh
  mvn clean verify -Dproperties=android.properties -Pcucumber
  mvn clean verify -Dproperties=ios.properties -Pcucumber
  ```

---

### 8. View the Serenity Report
After tests finish, open the report:
```sh
mvn serenity:aggregate
open target/site/serenity/index.html
```
Or manually navigate to `target/site/serenity/index.html` in your file browser.

---

## Troubleshooting & Tips
- If you see dependency errors, run `mvn clean install -U` to force update.
- Make sure your device/emulator/simulator matches the name and version in your properties file.
- If Appium can’t find your app, double-check the path and bundle/package ID.
- Use Java 11 & Appium 2.x for best stability.
- Start the Appium server before running the tests.
- Update the `appium.app` path in the properties files to your actual app location.

---

## Project Structure
- `src/test/resources/android.properties` — Android config
- `src/test/resources/ios.properties` — iOS config
- `src/test/resources/app/` — Place your `.apk` or `.app` files here
- `src/test/resources/features/` — Cucumber feature files

---

Happy Testing! 🚀 