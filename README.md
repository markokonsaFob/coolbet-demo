Running the tests:

Change the browser capabilities to match the following depending on which browser tests should run on.

1) Browser capabilities for Chrome in Desktop mode

"browser": {
      "UIType": "Desktop",
      "capability": "chrome"
    }

Chrome window size is changed to size 1920,1200

2) Browser capabilities for Chrome in Mobile mode

"browser": {
      "UIType": "Mobile",
      "capability": "chrome"
    }
Chrome window size is changed to 414, 736

3) Browser capabilities for Chrome on Android

  "browser": {
    "UIType": "Mobile",
    "capability": "Android",
    "browserName": "chrome",
    "deviceName": "AndroidPhone"
  }

4) Browser capabilities for Safari on iPhone

  "browser": {
    "UIType": "Mobile",
    "capability": "iPhone",
    "browserName": "safari",
    "deviceName": "device name",
    "udid": "device udid",
    "automationName": "XCUITest"
  }

