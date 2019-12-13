from datetime import datetime
import os
import re

w3c_configs = [
    "browserName",
    "browserVersion",
    "platformName"
]

sauce_configs = [
    "accessKey",
    "appiumVersion",
    "avoidProxy",
    "build",
    "captureHtml",
    "chromedriverVersion",
    "commandTimeout"
    "crmuxdriverVersion",
    "customData",
    "disablePopupHandler",
    "extendedDebugging",
    "firefoxAdapterVersion"
    "firefoxProfileUrl",
    "idleTimeout",
    "iedriverVersion",
    "maxDuration",
    "name",
    "parentTunnel",
    "passed",
    "prerun"
    "preventRequeue",
    "priority",
    "proxyHost",
    "public",
    "recordLogs",
    "recordScreenshots",
    "recordVideo"
    "restrictedPublicInfo",
    "screenResolution",
    "seleniumVersion",
    "source",
    "tags",
    "timeZone",
    "tunnelIdentifier"
    "username",
    "videoUploadOnPass",
    "capturePerformance"
]


class SauceOptions:

    def _set_default_build_name(self):
        if 'build' in self.options['sauce:options']:
            return
        # Jenkins
        elif os.environ.get("BUILD_TAG"):
            self.build = "{}: {}".format(os.environ['BUILD_NAME'], os.environ['BUILD_NUMBER'])
        # Bamboo
        elif os.environ.get('bamboo_agentId'):
            self.build = "{}: {}".format(os.environ['bamboo_shortJobName'], os.environ['bamboo_buildNumber'])
        # Travis
        elif os.environ.get('TRAVIS_JOB_ID'):
            job_name = re.search("[^/]+$", os.environ.get("TRAVIS_REPO_SLUG"))
            self.build = "{}: {}".format(job_name, os.environ['TRAVIS_JOB_NUMBER'])
        # Circle
        elif os.environ.get('CIRCLE_JOB'):
            self.build = "{}: {}".format(os.environ['CIRCLE_JOB'], os.environ['CIRCLE_BUILD_NUM'])
        # Gitlab
        elif os.environ.get('CI'):
            self.build = "{}: {}".format(os.environ['CI_JOB_NAME'], os.environ['CI_JOB_ID'])
        else:
            self.build = 'Build Time: {}'.format(datetime.utcnow())

    def __init__(self, browserName=None, browserVersion=None, platformName=None, options={}):
        super(SauceOptions, self).__setattr__('options', {'sauce:options': {}})

        for key, value in options.items():
            self.set_capability(key, value)

        self.parsePlatformName(platformName)
        self.parseBrowserName(browserName)
        if browserVersion:
            self.browserVersion = browserVersion
        elif 'browserVersion' not in self.options:
            self.browserVersion = 'latest'

        self._set_default_build_name()

    def __setattr__(self, key, value):
        if key in sauce_configs:
            self.options['sauce:options'][key] = value
        elif key in w3c_configs:
            self.options[key] = value
        else:
            raise AttributeError

    def __getattr__(self, key):
        try:
            if key in sauce_configs:
                return self.options['sauce:options'][key]
            elif key in w3c_configs:
                return self.options[key]
        except KeyError:
            raise AttributeError

    def set_capability(self, key, value):
        if key in sauce_configs:
            self.options['sauce:options'][key] = value
        elif key in w3c_configs:
            self.options[key] = value
        else:
            raise AttributeError

    def parseBrowserName(self, name):
        if 'browserName' in self.options:
            return
        elif name is None:
            self.browserName = 'chrome'
        elif name.lower() == 'ie' or name.lower() == 'internet explorer':
            self.browserName = 'internet explorer'
        elif name.lower() == 'edge':
            self.browserName = 'MicrosoftEdge'
        elif name.lower() == 'safari':
            self.browserName = 'safari'
            self.platformName = 'macOS 10.14'
        else:
            self.browserName = name.lower()

    def parsePlatformName(self, platform):
        if 'platformName' in self.options:
            return
        elif platform is None:
            self.platformName = 'Windows 10'
        elif platform.lower() == 'mac':
            self.setMacOs()
        elif 'windows' in platform.lower():
            self.platformName = 'Windows 10'
        else:
            self.platformName = platform

        self.options['platformName'] = self.platformName

    def setMacOs(self):
        self.browserName = 'safari'
        self.browserVersion = 'latest'
        self.platformName = 'macOS 10.14'
