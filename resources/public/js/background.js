var defaultFills = ['#ebedf0', '#c6e48b', '#7bc96f', '#239a3b', '#196127']

chrome.runtime.onInstalled.addListener(function() {
  chrome.storage.sync.set({
    githubColorsContributionsUserDefinedFills: defaultFills,
    githubColorsContributionsUserSelectedFills: 'none',
    githubColorsContributionsPreDefinedFills: defaultFills
  })

  chrome.declarativeContent.onPageChanged.removeRules(undefined, function() {
    chrome.declarativeContent.onPageChanged.addRules([
      {
        conditions: [
          new chrome.declarativeContent.PageStateMatcher({
            pageUrl: { hostPrefix: 'github.com', schemes: ['https'] }
          })
        ],
        actions: [new chrome.declarativeContent.ShowPageAction()]
      }
    ])
  })
})

chrome.runtime.onMessage.addListener(function(message) {
  if (message === 'runInject') {
    chrome.tabs.executeScript({
      file: 'js/inject.js'
    })
  }
})
