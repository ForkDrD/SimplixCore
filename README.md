[![Build Status](http://ci.exceptionflug.de/buildStatus/icon?job=SimplixCore)](http://ci.exceptionflug.de/job/SimplixCore/) [![Discord](https://img.shields.io/discord/752533664696369204?label=Discord)](https://discord.simplixsoft.com/) ![GitHub](https://img.shields.io/github/license/Simplix-Softworks/SimplixCore)
# SimplixCore

SimplixCore is a framework for java development. It includes a set of core Java libraries such as Googles Guava & LightningStorage. It also contains a streamlined Dependency Injection framework using Google Guice, an easy to use localization, configuration, a database framework as well as a set of utilities and a dynamic library loader. It is widely used on most Java & Kotlin projects within SimplixSoftworks.

For Spigot & BungeeCord the SimplixCore works as a plugin. You don't have to shade any parts of the SimplixCore (it's also very discouraged to do so on Spigot/BungeeCord!) By using the SimplixCore as a plugin you can also add extra libraries for the SimplixCore, that can be used for your own plugin as well.
## I Could've Invented That
Effective Java item 47, "Know and use the libraries", is our favorite explanation of why using libraries is, by and large,
preferable to writing your own utilities. The final paragraph bears repeating:

> To summarize, don’t reinvent the wheel. If you need to do something that seems like it should be reasonably common,
> there may already be a class in the libraries that does what you want. If there is,
>use it; if you don’t know, check. Generally speaking, library code is likely to be better 
> than code that you’d write yourself and is likely to improve over time. This is no reflection 
>on your abilities as a programmer. Economies of scale dictate that library code receives far 
> more attention than most developers could afford to devote to the same functionality.

## [Wiki](https://github.com/Simplix-Softworks/SimplixCore/wiki)
- #### [Getting started](https://github.com/Simplix-Softworks/SimplixCore/wiki/Getting-started)
- #### [Example-Project](https://github.com/Simplix-Softworks/SimplixExample)


## How to install
Installation is straight forward. Download the SimplixCore plugin for your platform, start your server and you are ready to go:

[SimplixCore for BungeeCord](https://ci.exceptionflug.de/job/SimplixCore/lastSuccessfulBuild/artifact/simplixcore-minecraft/simplixcore-minecraft-bungeecord/simplixcore-minecraft-bungeecord-plugin/target/SimplixCore-BungeeCord.jar)

[SimplixCore for Spigot](https://ci.exceptionflug.de/job/SimplixCore/lastSuccessfulBuild/artifact/simplixcore-minecraft/simplixcore-minecraft-spigot/simplixcore-minecraft-spigot-plugin/target/SimplixCore-Spigot.jar)

You can find all builds from our [CI server](https://ci.exceptionflug.de/job/SimplixCore/)

