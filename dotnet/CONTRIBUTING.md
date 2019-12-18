# Want to contribute to this open source project?
We always welcome open source contributions from the community. Together we can create amazing software.

1. Pull down the solution
2. Use ` dotnet pack simple.sauce.csproj` to create a local Nuget package that you can debug
It will appear in a directory similar to this `C:\Source\SauceLabs\simple_sauce\dotnet\Simple.Sauce\bin\Debug`
3. In a 2nd solution where you want to test the integration of the Simple Sauce Nuget package, you can install the local version of Simple Sauce following these instructions https://docs.microsoft.com/en-us/nuget/consume-packages/install-use-packages-dotnet-cli
Excellent docs by MS on how to work with Nuget: https://docs.microsoft.com/en-us/nuget/create-packages/overview-and-workflow
