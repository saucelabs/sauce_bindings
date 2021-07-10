from distutils.core import setup

with open('./README.rst', 'r') as f:
  readme = f.read()

setup(
  name = 'saucebindings',
  packages = ['saucebindings'],
  version = '1.2.0',
  license='MIT',
  description = 'Sauce Labs Python bindings library',
  long_description=readme,
  long_description_content_type="text/x-rst",
  author = 'josh.grant',
  author_email = 'josh.grant@saucelabs.com',
  url = 'https://github.com/saucelabs/sauce_bindings/',
  download_url = 'https://github.com/saucelabs/sauce_bindings/archive/v_100.tar.gz', # based on GitHub releases
  keywords = ['Sauce Labs', 'testing', 'selenium'],
  install_requires=[
          'selenium',
          'pytest'
      ],
  classifiers=[
    'Development Status :: 4 - Beta',
    'Intended Audience :: Developers',
    'Topic :: Software Development :: Testing',
    'License :: OSI Approved :: MIT License',
    'Programming Language :: Python :: 3',
    'Programming Language :: Python :: 3.5',
    'Programming Language :: Python :: 3.6',
  ],
)
