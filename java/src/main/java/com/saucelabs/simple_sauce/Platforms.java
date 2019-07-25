package com.saucelabs.simple_sauce;

public class Platforms
{
	public static class WINDOWS
	{
		public static String LATEST = WINDOWS_10;
	}

	public static class MAC_OS
	{
		public static String MOJAVE = MAC_OS_MOJAVE;
		public static String HIGH_SIERRA = MAC_OS_HIGH_SIERRA;
		public static String SIERRA = MAC_OS_SIERRA;
		public static String EL_CAPITAN = MAC_OS_EL_CAPITAN;
		public static String YOSEMITE = MAC_OS_YOSEMITE;

		public static String LATEST = MAC_OS_MOJAVE;
	}

	public static final String WINDOWS_10 = "Windows 10";
	public static final String WINDOWS_8_1 = "Windows 8.1";
	public static final String WINDOWS_8 = "Windows 8";

	public static final String MAC_OS_MOJAVE = "MacOS 10.14";
	public static final String MAC_OS_HIGH_SIERRA = "MacOS 10.13";
	public static final String MAC_OS_SIERRA = "MacOS 10.12";
	public static final String MAC_OS_EL_CAPITAN = "OS X 10.11";
	public static final String MAC_OS_YOSEMITE = "OS X 10.10";
}
