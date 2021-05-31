TASKKILL /F /IM chrome.exe
set "Command="
SET mypath=%~dp0
echo %mypath:~0,-1%
for /f "tokens=2,*" %%A in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Clients\StartMenuInternet\Google Chrome\shell\open\command" /ve 2^>nul') do set "Command=%%~B"
if not defined Command for /f "tokens=2,*" %%A in ('reg query "HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Clients\StartMenuInternet\Google Chrome\shell\open\command" /ve 2^>nul') do set "Command=%%~B"
if not defined Command echo Google Chrome was not found.
if defined Command start "Browser" "%Command%"  --allow-file-access-from-files --disable-web-security "%mypath:~0,-1%/index.html"
