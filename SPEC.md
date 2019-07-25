# Simple Sauce SPEC version 0.1

Language Bindings for making interacting with Sauce Labs for test clients.

Goal is to make it simpler to interact and do the right thing with regards to capabilities, and help provide information about tests (test name, build, pass/fail status, etc.)

## Language Bindings
	- Ruby
	- Java
	- Python
	- dotNet

## Sauce Options 
	- capabilities
		- browser
		- sauce
		- w3c
	- datacenter & url
	- validate sane capabilities (e.g. IE on Mac)
	- w3c specific
	- extend or generate Mutable Capabilities (java)

## Sauce Session
	- optionally instantiate with a Sauce Options
	- ? browser options / sauce specific options
	- starts a session
	- stops a session
	- uses sauce API when possible
	- js executor out of scope for now
	- W3C protocol specific 

## Out of scope
 - appium 
 - js executor
 - complete API (only methods for dealing with jobs)


 
