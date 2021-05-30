## App Center Test Application

An application for understanding Microsoft App Center and learn to use it's opportunities:
* Analytic (Tracking information about sessions and custom actions)
* Crashes (Tracking application crashes and exceptions)
* Distribute (Self in-app updating)

### Usage instructions:
  #### "Crash The App" button
    Initialize ArithmeticalException that stops application. In this case App Center will get new Issue with description of error

  #### "Track Event" button
    Sends information about click with information about System language and time (As example of sending additional properties)

  #### "Track Error" button
    Initialized NullPointerException and catches it then sends information about catches exception to App Center Crashes

#### When the highest version will be distributed in App Center, user will receive information about it in dialog window with possibility/necessity(if new version is mandatory) of installing new version

![interface](https://github.com/Dkuriab/AppCenterTest/blob/master/screenshots/Screenshot_20210530_035141.png)
![update](https://github.com/Dkuriab/AppCenterTest/blob/master/screenshots/Screenshot_20210525_201804.png)
