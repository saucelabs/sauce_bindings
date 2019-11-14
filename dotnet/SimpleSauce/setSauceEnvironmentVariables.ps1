Param(
[string]$sauceUserName,
[string]$sauceAccessKey
)
Write-Output "sauce.userName that was passed in from Azure DevOps=>$sauceUserName"
Write-Output "sauce.accessKey that was passed in from Azure DevOps=>$sauceAccessKey"
Write-Output "Reading sauce.userName directly from Azure DevOps using a different syntax=>" + $env:SAUCE_USERNAME

[Environment]::SetEnvironmentVariable("SAUCE_USERNAME", "$sauceUserName", "User")
[Environment]::SetEnvironmentVariable("SAUCE_ACCESS_KEY", "$sauceAccessKey", "User")
