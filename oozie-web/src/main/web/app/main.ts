'use strict';

import {bootstrap} from '@angular/platform-browser-dynamic';
import {provide} from '@angular/core';
import { APP_BASE_HREF} from '@angular/common';
import "angular2-materialize";

import {AppComponent} from './app.component';

bootstrap(AppComponent, [
    provide(APP_BASE_HREF, {useValue: '/'})
]).catch(err => console.error(err));
