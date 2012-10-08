package chatterbot;

public class ChatterBotFactory {

	public ChatterBot create(ChatterBotType type) throws Exception {
		return create(type, null);
	}

	public ChatterBot create(ChatterBotType type, Object arg) throws Exception {
		switch (type) {
		case CLEVERBOT:
			return new Cleverbot("http://cleverbot.com/webservicemin");
		case JABBERWACKY:
			return new Cleverbot("http://jabberwacky.com/webservicemin");
		case PANDORABOTS:
			if (arg == null) {
				throw new Exception("PANDORABOTS needs a botid arg");
			}
			return new Pandorabots(arg.toString());
		}
		return null;
	}

}