var Steps = require('screener-runner/src/steps');
module.exports = {
    // full repository name for your project:
    projectRepo: 'sauce-bindings',

    // this example assumes Environment Variables listed below exist on your system:
    apiKey: process.env.SCREENER_API_KEY,
    newSessionForEachState: true, //this forces screener to run a brand new sauce labs session for each screenshot

    // array of UI states to capture visual snapshots of.
    // each state consists of a url and a name.
    states: [
        {
            url: 'http://localhost:3000/docs/overview.html',
            name: 'Overview page'
        },
        {
            url: 'http://localhost:3000/docs/getting-started',
            name: 'Getting started page'
        },
        {
            url: 'http://localhost:3000/docs/create-session',
            name: 'Create session page'
        },
        {
            url: 'http://localhost:3000/docs/set-options',
            name: 'Set options page'
        },
        {
            url: 'http://localhost:3000/docs/contributing',
            name: 'Contributing page'
        },
        {
            url: 'http://localhost:3000/docs/code-of-conduct',
            name: 'Code of conduct page'
        },
        {
            url: 'http://localhost:3000/help',
            name: 'Help'
        }
        //If you wanted to dynamically preload the page, you'd do it like this
        // {
        //   //This page has dynamic elements that only appear when they are scrolled into view
        //   //so we run a script to preload those elements
        //   url: 'https://www.ultimateqa.com',
        //   name: 'Ultimate QA Home Page',
        //   steps: new Steps()
        //   .executeScript('window.scrollTo(0,document.body.scrollHeight)')
        //   .wait(4000)
        //   .snapshot('Loaded')
        //   .end()
        // }
    ],
    //What are all of the browsers that we want to test against
    //In this case, they are all overriden in the screener.sauce.config
    // Top 4 most popular desktop resolutions:
    // https://gs.statcounter.com/screen-resolution-stats/desktop/worldwide
    resolutions: [
        '1366x768',
        '1920x1080',
        '1440x900',
        '1536x864'
    ]
};