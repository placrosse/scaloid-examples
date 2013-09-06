scaloid-examples
================

Sample Android applications using Scaloid

To use this project, you will need:
- Java JDK
- SBT
- SBT android plugin (clone and run publish-local against it via SBT)
- Scaloid library (clone and run publish-local against it via SBT)
- Android SDK (tools only, Eclipse not necessary)
- Android platform you are targetting (API 16 used in these samples, Android SDK Manager installs APIs)
- a code text editor (jEdit works well, if you don't already have one)

Refer to the SBT android plugin instructions for further details on how the build works.
In short, you can build and install the Hello example on an attached USB Android device using this sequence:

- connect your device via USB (device must be in USB debugging mode)
- from a cmd prompt, make the root of this project your current dir
- launch SBT from there
- from the SBT prompt, you will then (not shortest path, but shows each step that occurs:
    - project hello
    - clean
    - compile
    - package
    - android:package-debug
    - android:install-device
- The Android icon should show in your list of installed apps.  If your device is running less than Android 4.1.1, you will need to make minor adjustments to use an earlier API. 
