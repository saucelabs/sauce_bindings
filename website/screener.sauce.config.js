var screenerConfig = require('./screener.config');

// If you would like to run on Edge or Safari,
// you need to have a Sauce Labs account
// overriding the browser values that come from ./screener.config
// DOCS for Sauce: https://screener.io/v2/docs/sauce
screenerConfig.browsers = [
    {
        browserName: 'microsoftedge',
        version: '17.17134'   //NOTE: if you exclude the version on chrome and ff, it will run on Screener cloud.
        //including the version will run tests on Sauce Labs cloud
    }
];
screenerConfig.sauce = {
    username: process.env.SAUCE_USERNAME,
    accessKey: process.env.SAUCE_ACCESS_KEY,
    maxConcurrent: 100, // optional available concurrency you have from Sauce Labs
    launchSauceConnect: true
    //extendedDebugging: true, // optional
    //tunnelIdentifier: 'MyTunnel01' // optional
};

module.exports = screenerConfig;