Android Architecture Components Basic Sample
===================================

This sample showcases the following Architecture Components:

* [ViewModels](https://developer.android.com/reference/android/arch/lifecycle/ViewModel.html)
* [LiveData](https://developer.android.com/reference/android/arch/lifecycle/LiveData.html)

## Features

This sample is a simple `bank account` application.
When app starts user can observe his account state with 2 deposits. Clicking `exchange` button user can pass to the second screen and make transitions between his deposits.

Application contains no rotation handling logic at all. Only subscriptions to data & that is all to magic happen.

Architecture type: `MVVM`

## Libraries
* [Android Support Library][support-lib]
* [Android Architecture Components][arch]
* [Dagger 2][dagger2] for dependency injection
* [RxJava2 & RxAndroid2][rxjava2] for lambda expressions
* [Timber][timber] for logging
* [Butterknife 8][butterknife] for views injection
* [Guava][guava] for support functionality

[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[arch]: https://developer.android.com/arch
[dagger2]: https://google.github.io/dagger
[rxjava2]: http://reactivex.io/
[timber]: https://github.com/JakeWharton/timber
[butterknife]: http://jakewharton.github.io/butterknife/
[guava]: https://github.com/google/guava/wiki

## License

The MIT License

Copyright (c) 2017, Daniil Savelyev.

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
