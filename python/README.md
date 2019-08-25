# Simple Sauce - Python Bindings

Hello, welcome to the Simple Sauce Python bindings! Let's make using Sauce Labs Simple!

## Simplest Example Usage

The goal of Simple Sauce is to provide a straightforward approach to connecting your automated scripts to Sauce. Here's a barebones example. First, set your Sauce username and Sauce access key [as environment variables](https://wiki.saucelabs.com/display/DOCS/Best+Practice%3A+Use+Environment+Variables+for+Authentication+Credentials), then Simple Sauce can be used like this:
```
from simplesauce.session import SauceSession

session = SauceSession()

session.driver.get("www.saucedemo.com")

session.driver.quit()
```


## Installation

Clone this project to build from source
```
git clone https://github.com/saucelabs/simple_sauce
python setup.py install
```
or install from `pip`:
```
pip install simplesauce
```

## Development

This project will be developed intially in Python 3.x so please create a virtual environment:

```
python3 -m venv venv
source venv/bin/activate
```

To install dependencies, do the following:
```
pip install -r requirements.txt
```

## Testing

To run all tests, run the following:
```
pytest
```

