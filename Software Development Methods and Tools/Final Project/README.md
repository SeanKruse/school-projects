# Team 3 Stooges Signature Management Addin

This repository contains the source code for the Team 3 Stooges Signature Addin created for the CS3250 class at MSU Denver. This was created by Philip Marshall, Weston Lake, Sean Kruse, Sam Han, Jose Garcia-Martinez, Jacob Torres. amd Kero Lee. In this addin, you are able to select signatures on a per account basis as well as add new signatured and delete signatures from the saved list. The signatures will be automatically added to your email when either the insert signature button or the random signature button is pressed.

## How to install

Inside your outlook web app, compose a new email. Navigate to the "Get Add-ins" page in outlook, and go to "My add-ins". Once there you can click the button labeled "Add a custom add-in" This will let you choose between adding an addin through a url or else through a file. Choose the file and locate the xml file labeled manifest in you local repo for this app. Once you have selected the manifest file and installed it, you can now leave this addin page.

Now open the command line inside the root folder of this project and run the command "npm run dev-server", you may need to run "npm install" first. Once the server is running and has had enough time to completely load, you can go into your new email. In the same side bar that you fiound the "Get Add-ins" button, you should see a buttom labeled "Team-3-Stooges", click this and the app should load in your taskpane.
