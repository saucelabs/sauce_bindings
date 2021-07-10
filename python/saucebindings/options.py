import warnings
from datetime import datetime

from .configs import *
from selenium.webdriver import __version__ as seleniumVersion
import os


w3c_configs = {
    'browser_name': 'browserName',
    'browser_version': 'browserVersion',
    'platform_name': 'platformName',
    'accept_insecure_certs': 'acceptInsecureCerts',
    'page_load_strategy': 'pageLoadStrategy',
    'proxy': 'proxy',
    'set_window_rect': 'setWindowRect',
    'timeouts': 'timeouts',
    'unhandled_prompt_behavior': 'unhandledPromptBehavior',
    'strict_file_interactability': 'strictFileInteractability'
}

sauce_configs = {
    'avoid_proxy': 'avoidProxy',
    'build': 'build',
    'chromedriver_version': 'chromedriverVersion',
    'command_timeout': 'commandTimeout',
    'custom_data': 'customData',
    'edgedriver_version': 'edgedriverVersion',
    'extended_debugging': 'extendedDebugging',
    'geckodriver_version': 'geckodriverVersion',
    'idle_timeout': 'idleTimeout',
    'iedriver_version': 'iedriverVersion',
    'max_duration': 'maxDuration',
    'name': 'name',
    'parent_tunnel': 'parentTunnel',
    'prerun': 'prerun',
    'priority': 'priority',
    'public': 'public',
    'record_logs': 'recordLogs',
    'record_screenshots': 'recordScreenshots',
    'record_video': 'recordVideo',
    'screen_resolution': 'screenResolution',
    'selenium_version': 'seleniumVersion',
    'tags': 'tags',
    'time_zone': 'timeZone',
    'tunnel_identifier': 'tunnelIdentifier',
    'video_upload_on_pass': 'videoUploadOnPass',
    'capture_performance': 'capturePerformance'
}

class SauceOptions(object):

    @classmethod
    def chrome(cls, **kwargs):
        return cls('chrome', validOptions=Configs().chromeConfigs(), **kwargs)

    @classmethod
    def edge(cls, **kwargs):
        if seleniumVersion[0] == '3':
            raise NotImplementedError('Selenium 3 does not support Chromium Edge. Look for SauceBindings Support of '
                                      'Selenium 4 soon.')
        return cls('MicrosoftEdge', validOptions=Configs().edgeConfigs(), **kwargs)

    @classmethod
    def firefox(cls, **kwargs):
        return cls('firefox', validOptions=Configs().firefoxConfigs(), **kwargs)

    @classmethod
    def ie(cls, **kwargs):
        return cls('internet explorer', validOptions=Configs().ieConfigs(), **kwargs)

    @classmethod
    def safari(cls, **kwargs):
        if 'platformName' not in kwargs:
            kwargs['platformName'] = 'macOS 11.00'

        return cls('safari', validOptions=Configs().safariConfigs(), **kwargs)

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
            self.build = "{}: {}".format(os.environ['TRAVIS_JOB_NAME'], os.environ['TRAVIS_JOB_NUMBER'])
        # Circle
        elif os.environ.get('CIRCLE_JOB'):
            self.build = "{}: {}".format(os.environ['CIRCLE_JOB'], os.environ['CIRCLE_BUILD_NUM'])
        # GitHub Actions
        elif os.environ.get("GITHUB_SHA"):
            self.build = "{}: {}".format(os.environ['GITHUB_WORKFLOW'], os.environ['GITHUB_SHA'])
        # Gitlab
        elif os.environ.get('CI_JOB_ID'):
            self.build = "{}: {}".format(os.environ['CI_JOB_NAME'], os.environ['CI_JOB_ID'])
        # Team City
        elif os.environ.get('TEAMCITY_PROJECT_NAME'):
            self.build = "{}: {}".format(os.environ['TEAMCITY_PROJECT_NAME'], os.environ['BUILD_NUMBER'])
        else:
            self.build = 'Build Time: {}'.format(datetime.utcnow())

    def __init__(self, browserName=None, validOptions=None, seleniumOptions=None, **kwargs):
        super(SauceOptions, self).__setattr__('options', {'sauce:options': {}})
        super(SauceOptions, self).__setattr__('seleniumOpts', {})
        if validOptions == None:
            warnings.warn('Options() is deprecated, use class methods like Options.chrome() instead',
                          DeprecationWarning)
            validOptions = {**w3c_configs, **sauce_configs}
        super(SauceOptions, self).__setattr__('validOptions', validOptions)

        if self.validOptions == None:
            self.validOptions = {**w3c_configs, **sauce_configs}

        self.validateOptions(kwargs)

        if seleniumOptions is not None:
            self.set_capability('browserName', seleniumOptions.capabilities['browserName'])
            self.seleniumOpts['caps'] = seleniumOptions.to_capabilities()

        for key, value in kwargs.items():
            self.set_capability(key, value)

        if self.browser_version is None:
            self.set_capability('browserVersion', 'latest')

        if browserName:
            self.set_capability('browserName', browserName)
        elif self.browser_name:
            pass  # browser name set by Selenium Class
        else:
            self.set_capability('browserName', 'chrome')

        if self.platform_name is None:
            self.set_capability('platformName', 'Windows 10')

        self._set_default_build_name()

    def __setattr__(self, key, value):
        if (self.validOptions == None and key == 'validOptions') or key in self.validOptions.keys():
            self.set_option(key, value)
        else:
            raise AttributeError('parameter ' + key + ' not available for this configuration')

    def __getattr__(self, key):
        if key == 'selenium_options':
            if 'caps' in self.seleniumOpts.keys():
                return self.seleniumOpts['caps']
            else:
                return None
        try:
            if key in sauce_configs.keys():
                return self.options['sauce:options'][sauce_configs[key]]
            elif key in w3c_configs.keys():
                return self.options[w3c_configs[key]]
            else:
                raise AttributeError
        except KeyError:
            return None

    def validateOptions(self, kwargs):
        for k in kwargs.keys():
            if k not in self.validOptions.values():
                raise AttributeError('parameter ' + k + ' not available for this configuration')

    def merge_capabilities(self, capabilities):
        for key, value in capabilities.items():
            if key not in self.validOptions.values():
                raise AttributeError('parameter ' + key + ' not available for this configuration')
            self.set_capability(key, value)

    # Sets with camelCase
    def set_capability(self, key, value):
        if key in sauce_configs.values():
            self.options['sauce:options'][key] = value
        elif key in w3c_configs.values():
            self.options[key] = value
        else:
            raise AttributeError

    # Sets with snake_case
    def set_option(self, key, value):
        if key in sauce_configs.keys():
            self.options['sauce:options'][sauce_configs[key]] = value
        elif key in w3c_configs.keys():
            self.options[w3c_configs[key]] = value
        else:
            raise AttributeError

    def to_capabilities(self):
        if self.selenium_options:
            self.options.update(self.selenium_options)
        return self.options
