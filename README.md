<p align="center">
  <img height="200px" width="500px" src="https://user-images.githubusercontent.com/4109119/32070030-42060272-ba8b-11e7-8609-469decac7029.png"/>
</p>

# Wolox Android Bootstrap

This is the base template project that we use at [Wolox](https://www.wolox.com.ar/) for new Android applications.

This project integrates useful libraries and [WOLMO](https://github.com/Wolox/wolmo-core-android), our framework for developing
Android apps.


## Features

These features can be found in Wolox Android Boostrap:

* Fully integrated with [WOLMO](https://github.com/Wolox/wolmo-core-android)
and [WOLMO NETWORKING](https://github.com/Wolox/wolmo-networking-android)
* Usage example of the MVP architecture.
* Usage example of Dagger 2 with WOLMO and WOLMO NETWORKING modules.
* Has [WARP](https://github.com/Wolox/warp) for quick assets creation and optimization.
* Includes [Butterknife](https://github.com/JakeWharton/butterknife) for view binding
* Includes [Chuck](https://github.com/jgilfelt/chuck) for easy HTTP requests logging.
* Includes [Leak Canary](https://github.com/square/leakcanary) for memory leak detection.
* Includes some additional dependencies for testing.
* Base build variants set up for production and development.

### Usage

1. Clone this repository
2. Change the **applicationId** in the *app/build.gradle* file
3. Change the ***BootstrapApplication*** class name to the name of your application
4. Setup the API endpoints (if necessary) in the *Configuration*/*BaseConfiguration* classes
5. Change the launcher icons under *main*/*stage* directories
6. Setup the production keystore credentials: run `scripts/keystore.sh` from the project root directory or create *app/keystore.gradle* file manually:
```gradle
ext.release_keystore=file('...')
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```

## <a name="topic-about"></a> About

This project is maintained by [Juan Ignacio Molina](https://github.com/juanignaciomolina)
and is developed by [Wolox](http://www.wolox.com.ar).

![Wolox](https://raw.githubusercontent.com/Wolox/press-kit/master/logos/logo_banner.png)

## <a name="topic-license"></a> License

**WOLOX ANDROID BOOSTRAP** is available under the MIT [license](https://raw.githubusercontent.com/Wolox/WoloxAndroidBootstrap/master/LICENSE.md).

    Copyright (c) Wolox S.A

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
