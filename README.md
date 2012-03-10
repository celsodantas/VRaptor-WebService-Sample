#A sample "REST" WebService project using VRaptor + Spring

This is just a sample. I'm using this app into one of my project so I just simplified it and pushed to Github.

It's using:

* `VRaptor` as the MVC framework
* `Spring` to inject stuff
* `Spring Security` with a base digest authentication (easy to remove)
* `EhCache` to speed things up. It's doing database cache and only goes into the real database each 20 seconds.
* `SQL Server`. You'll need to change some stuffs to work with a different database.

I build this application to be a WebService to fetch informations from a old (very old!) database. Table names were weird and had some crazy joins. So the easiest way was to use pure SQL to fetch stuff, and not use Hibernate. You'll see the SQLs inside a XML file.

I'll organize the `test/` folder and I'll push to Github as well.


