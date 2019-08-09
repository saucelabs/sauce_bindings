from distutils.command.install import INSTALL_SCHEMES
from os.path import abspath, dirname, join

from setuptools import setup
from setuptools.command.install import install

for scheme in INSTALL_SCHEMES.values():
    scheme['data'] = scheme['purelib']

setup_args = {
    'cmdclass': {'install': install},
    'name': 'simple_sauce',
    'version': '0.0.1',
    'license': 'MIT',
    'description': 'Python bindings of the simple_sauce library https://github.com/saucelabs/simple_sauce',
    'long_description': open(join(abspath(dirname(__file__)), 'README.md')).read(),
    'url': 'https://github.com/saucelabs/simple_sauce',
    'classifiers': ['Intended Audience :: Developers',
                    'Operating System :: POSIX',
                    'Operating System :: Microsoft :: Windows',
                    'Operating System :: MacOS :: MacOS X',
                    'Topic :: Software Development :: Testing',
                    'Topic :: Software Development :: Libraries',
                    'Programming Language :: Python',
                    'Programming Language :: Python :: 2.7',
                    'Programming Language :: Python :: 3.4',
                    'Programming Language :: Python :: 3.5',
                    'Programming Language :: Python :: 3.6',
                    'Programming Language :: Python :: 3.7'],
    'install_requires': ['selenium', 'six', 'python-dateutil'],
    'package_dir': {'simple_sauce': 'simplesauce'},
    'packages': ['simplesauce'],
    'zip_safe': False
}

setup(**setup_args)