class Configs:
    base_configs = {
        'platform_name': 'platformName',
        'name': 'name',
        'build': 'build',
        'tags': 'tags',
        'custom_data': 'customData',
        'public': 'public',
        'tunnel_identifier': 'tunnelIdentifier',
        'parent_tunnel': 'parentTunnel'
    }

    vdc_configs = {
        'browser_version': 'browserVersion',
        'page_load_strategy': 'pageLoadStrategy',
        'accept_insecure_certs': 'acceptInsecureCerts',
        'proxy': 'proxy',
        'strict_file_interactability': 'strictFileInteractability',
        'unhandled_prompt_behavior': 'unhandledPromptBehavior',
        'timeouts': 'timeouts',
        'record_video': 'recordVideo',
        'video_upload_on_pass': 'videoUploadOnPass',
        'record_screenshots': 'recordScreenshots',
        'record_logs': 'recordLogs',
        'max_duration': 'maxDuration',
        'command_timeout': 'commandTimeout',
        'idle_timeout': 'idleTimeout',
        'prerun': 'prerun',
        'priority': 'priority',
        'screen_resolution': 'screenResolution',
        'time_zone': 'timeZone'
    }

    chrome_configs = {
        'chromedriver_version': 'chromedriverVersion',
        'extended_debugging': 'extendedDebugging',
        'capture_performance': 'capturePerformance',
        'set_window_rect': 'setWindowRect'
    }

    edge_configs = {
        'edgedriver_version': 'edgedriverVersion',
        'selenium_version': 'seleniumVersion',
        'set_window_rect': 'setWindowRect'
    }

    firefox_configs = {
        'geckodriver_version': 'geckodriverVersion',
        'extended_debugging': 'extendedDebugging',
        'selenium_version': 'seleniumVersion',
        'set_window_rect': 'setWindowRect'
    }

    ie_configs = {
        'iedriver_version': 'iedriverVersion',
        'avoid_proxy': 'avoidProxy',
        'selenium_version': 'seleniumVersion',
        'set_window_rect': 'setWindowRect'
    }

    safari_configs = {
        'avoid_proxy': 'avoidProxy',
        'selenium_version': 'seleniumVersion',
        'set_window_rect': 'setWindowRect'
    }

    def vdcConfigs(self):
        return {**(self).base_configs, **self.vdc_configs}

    def chromeConfigs(self):
        return {**self.vdcConfigs(), **self.chrome_configs}

    def edgeConfigs(self):
        return {**self.vdcConfigs(), **self.edge_configs}

    def firefoxConfigs(self):
        return {**self.vdcConfigs(), **self.firefox_configs}

    def ieConfigs(self):
        return {**self.vdcConfigs(), **self.ie_configs}

    def safariConfigs(self):
        return {**self.vdcConfigs(), **self.safari_configs}
