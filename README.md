# tiktok-shit-cleaner-full
 The full project for the Tiktok Shit Cleaner
 
# About
 This is a project I'm doing for a youtube video where I complain about how most Tiktoks are pretty garbage. 

 The app takes a Tiktok video (usually a .mp4 video) and extracts the audio from it, then sending it to a web server for it to compare that audio to the audio on the server and it returns if it's a good video or not. The client runs as an Android Application. 

 # Tech Stack
 Right now, I'm programming the entire backend using java 11 and Spring Boot 2.5.4.
 The Server stores files in folders in your home directory in order for the converter to actually work. I know this probably isn't ideal and there's probably a much faster way to do this but, this way works very well for me for right now.

 # Setting up on local machine
 The project is seperated into 2 different parts, TiktokShitCleaner-Android and TiktokShitCleaner-Server. If you want to build it yourself, just open the build.gradle
 file in your IDE or editor of choice. 

 Server Instructions:
 Before you run the server, be sure to run the groovy script under
 `src/main/groovy/io/hayjw916/tiktokshitcleaner/server/scripts/CreateApplicationProperties.groovy`. This will generate a application.properties 
 files specific to your configuration so the files will save correctly

*FINISH README!!!!!!!*
