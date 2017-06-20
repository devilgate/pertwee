# Pertwee

Pertwee is a library for parsing [JSON Feed](https://jsonfeed.org/) feeds.


## Using


To use it just pass it a feed URL like this:

```java
URL url = new URL("http://example.com/feed/json");
Feed feed = DefaultFeed.fromUrl(url);
```

And then process the feed at will.

## Why the Name?

Why did I call it Pertwee, you're probably wondering? I was thinking about possible names around "JSON,", but "JSON" suddenly made me think of "Jon." The most famous person to use that spelling was of course the great [Jon Pertwee](https://en.wikipedia.org/wiki/Jon_Pertwee), and there it was.

Besides, isn't it nice to have a Java library that doesn't begin with the letter "J" for a change?

