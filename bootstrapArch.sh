#!/usr/bin/env bash
pacman -Sy
yes | pacman -S nginx
pacman -S vim --noconfirm
pacman -S gradle --noconfirm

gradle bootRun

nginx
