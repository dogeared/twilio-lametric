## Twilio Messages and LaMetric Connected Clock

My team at work recently gave me a [LaMetric Connected Clock](https://lametric.com/) as a gift.

It's pretty cool and - best of all - it has an API to push messages to it.

[Twilio](https://www.twilio.com/) is another favorite service of mine. It makes it super easy to send and receive 
SMS and MMS messages, as well as faxes and voice mails.

So, why not mash them up?

Here's the game:

* Text any message to **702-766-4224** (only US and Canada)
    * As a bonus, format your message like: `-<icon id> <msg>` where icon ids can be found at: [https://developer.lametric.com/icons](https://developer.lametric.com/icons) 
* You'll get a response referencing an easter egg from my blog post about this (soon to drop).
    * For now, you can cheat using the codes [here](twilio-lametric-api/src/main/java/com/afitnerd/twilio_lametric/service/CodeService.java).
* Text the proper reply to the same number, and your message will be sent to my [LaMetric Digital](https://lametric.com/) message board.
* You can tune into this [twitch](https://www.twitch.tv/afitnerd) stream to see your message display as well as that of other people  