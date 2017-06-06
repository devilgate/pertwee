# Pertwee

Pertwee is a library for parsing [JSON Feed](https://jsonfeed.org/) feeds.


## Using


To use it just pass it a feed URL like this:

```java
URL url = new URL("http://example.com/feed/json");
Feed feed = SimpleFeed.fromUrl(url);
```
