Dark Vinyl Player

Description: 
Dark Vinyl Player is a JavaFX-based music player that visually represents a spinning vinyl record while playing music. 
The turntable rotates when the music plays and stops when paused, with dynamic animations that enhance the user experience. 
It also includes a chat tab feature that allows users to interact with a virtual companion.



Features: 
JavaFX-powered UI with a dark aesthetic. 
Animated spinning turntable when music plays. 
Customizable text that rotates with the record. 
Click-based toggle to play and pause music. 
Detects skipping or rewinding and adjusts spin speed accordingly. 
Side chat tab that allows interaction with a virtual companion.



Requirements: 
Java 11 or later (JavaFX is not included in JDK 11+ and must be installed separately). JavaFX SDK installed and configured. A valid MP3 file placed in the correct path.
Setup Instructions: Ensure Java and JavaFX are installed on your system.
Download and configure the JavaFX SDK from [https://gluonhq.com/products/javafx/].
Update the musicPath variable in DarkVinylPlayer.java to point to your MP3 file.
Run the application using an IDE (IntelliJ IDEA, Eclipse, etc.) or via the command line:
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.media DarkVinylPlayer



Troubleshooting:
If JavaFX dependencies are missing, ensure they are correctly linked in your IDE.
Verify that the MP3 file path is correct and accessible.
If using IntelliJ IDEA, go to Run Configurations and add JavaFX modules to the VM options. 
Ensure that the chat tab feature is correctly integrated by checking for JavaFX layout issues.


Author: Mallika Suyal
