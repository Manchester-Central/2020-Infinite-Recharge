@echo off
echo.
echo Setup git configuration for CHAOS FRC robot development
echo.
set /p gc_name="Name: "
echo.
echo Email address 
echo     Note: use the @users.noreply.github.com address from 
echo     https://github.com/settings/emails to avoid making your real address
echo     public
set /p gc_email="Email: "
git config --replace-all user.name "%gc_name%"
git config --replace-all user.email "%gc_email%"
git config core.editor notepad