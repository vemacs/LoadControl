LoadControl
===========

control world loading for Bukkit

What it does ATM
----------------

Prevents mystcraft worlds from keeping regions loaded

Each world still takes around ~10mb for weather and others, though.

Stable release
--------------

http://cl.ly/N8CP/LoadControl.jar

Testing
-------

Before plugin was installed: OOM

After plugin is installed: no OOM, server runs fine

After plugin is removed: no OOM, since the data has been set, but slightly more memory usage

Not very scientific, but it definitely works.
