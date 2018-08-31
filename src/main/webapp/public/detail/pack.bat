
echo -----start-------

@echo off
set cur=%~dp0
cd /d %~dp0

for /f " delims="  %%i in ('dir /ad/b/s .' ) do ( @echo %%~ni &  if not [%%~ni]==[bak]   call:dopackjs %%i )

@rem call:dopackjs "%cur%"

:dopackjs    - here starts my function identified by it's label  
echo.  
echo. curpath is  %~1 begin.  
cd %~1
mkdir bak
for /f "tokens=1,2 delims=." %%i in ('dir /b /a-d') do (if [%%j]==[js] @echo %%i.%%j  & move %%i.%%j bak/ &&  CScript /nologo "%cur%"pack.wsf bak/%%i.%%j >>%%i.%%j  )

echo. curpath   %~1 end.  
echo ------------------------
goto:eof 


set "%cur%"
pause