@echo off
rem Check dependencies
rem Generates an up-to-date javadoc
cd ../tddt
call gradlew javadoc
rem Copy to this folder
xcopy build/docs/javadoc ../docs/javadoc /q /e 
rem Remove duplicate folder
rmdir /S /Q "build/docs/javadoc"
pause
