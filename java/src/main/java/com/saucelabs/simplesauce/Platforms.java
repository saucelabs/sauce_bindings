package com.saucelabs.simplesauce;

import lombok.Getter;

enum Platforms {
	WINDOWS_10("Windows 10"),
	WINDOWS_8_1("Windows 8.1"),
	WINDOWS_8("Windows 8"),
	MAC_OS_MOJAVE("macOS 10.14"),
	MAC_OS_HIGH_SIERRA("macOS 10.13"),
	MAC_OS_SIERRA("macOS 10.12"),
	MAC_OS_EL_CAPITAN("OS X 10.11"),
	MAC_OS_YOSEMITE("OS X 10.10");

	@Getter private final String osVersion;

	//TODO Update this method whenever the "latest" has to change
	public static Platforms windowsLatest() {
		return WINDOWS_10;
	}

	//TODO Update this method whenever the "latest" has to change
	public static Platforms macLatest() {
		return MAC_OS_MOJAVE;
	}

	Platforms(String osVersion) {
		this.osVersion = osVersion;
	}

	@SuppressWarnings("SpellCheckingInspection")
	public enum MAC_OS {
		MOJAVE(MAC_OS_MOJAVE),
		HIGH_SIERRA(MAC_OS_HIGH_SIERRA),
		SIERRA(MAC_OS_SIERRA),
		EL_CAPITAN(MAC_OS_EL_CAPITAN),
		YOSEMITE(MAC_OS_YOSEMITE);

		@Getter private Platforms platform;

		MAC_OS(Platforms platform) {
			this.platform = platform;
		}
	}
}
