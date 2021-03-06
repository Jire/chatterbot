package chatterbot;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class WebServiceFactory implements ChatterBotFactory {

	private final String url;

	public WebServiceFactory(String url) {
		this.url = url;
	}

	@Override
	public ChatterBot create() {
		return new Session();
	}

	private class Session implements ChatterBot {
		private final Map<String, String> vars;

		public Session() {
			vars = new LinkedHashMap<String, String>();
			vars.put("start", "y");
			vars.put("icognoid", "wsf");
			vars.put("fno", "0");
			vars.put("sub", "Say");
			vars.put("islearning", "1");
			vars.put("cleanslate", "false");
		}

		@Override
		public ChatterBotThought think(ChatterBotThought thought)
				throws Exception {
			vars.put("stimulus", thought.getText());

			String formData = Utils.parametersToWWWFormURLEncoded(vars);
			String formDataToDigest = formData.substring(9, 29);
			String formDataDigest = Utils.md5(formDataToDigest);
			vars.put("icognocheck", formDataDigest);

			String response = Utils.post(url, vars);

			String[] responseValues = response.split("\r");

			// vars.put("", Utils.stringAtIndex(responseValues, 0)); ??
			vars.put("sessionid", Utils.stringAtIndex(responseValues, 1));
			vars.put("logurl", Utils.stringAtIndex(responseValues, 2));
			vars.put("vText8", Utils.stringAtIndex(responseValues, 3));
			vars.put("vText7", Utils.stringAtIndex(responseValues, 4));
			vars.put("vText6", Utils.stringAtIndex(responseValues, 5));
			vars.put("vText5", Utils.stringAtIndex(responseValues, 6));
			vars.put("vText4", Utils.stringAtIndex(responseValues, 7));
			vars.put("vText3", Utils.stringAtIndex(responseValues, 8));
			vars.put("vText2", Utils.stringAtIndex(responseValues, 9));
			vars.put("prevref", Utils.stringAtIndex(responseValues, 10));
			// vars.put("", Utils.stringAtIndex(responseValues, 11)); ??
			vars.put("emotionalhistory",
					Utils.stringAtIndex(responseValues, 12));
			vars.put("ttsLocMP3", Utils.stringAtIndex(responseValues, 13));
			vars.put("ttsLocTXT", Utils.stringAtIndex(responseValues, 14));
			vars.put("ttsLocTXT3", Utils.stringAtIndex(responseValues, 15));
			vars.put("ttsText", Utils.stringAtIndex(responseValues, 16));
			vars.put("lineRef", Utils.stringAtIndex(responseValues, 17));
			vars.put("lineURL", Utils.stringAtIndex(responseValues, 18));
			vars.put("linePOST", Utils.stringAtIndex(responseValues, 19));
			vars.put("lineChoices", Utils.stringAtIndex(responseValues, 20));
			vars.put("lineChoicesAbbrev",
					Utils.stringAtIndex(responseValues, 21));
			vars.put("typingData", Utils.stringAtIndex(responseValues, 22));
			vars.put("divert", Utils.stringAtIndex(responseValues, 23));

			return new ChatterBotThought(
					Utils.stringAtIndex(responseValues, 16));
		}

		@Override
		public String think(String text) throws Exception {
			return think(new ChatterBotThought(text)).getText();
		}
	}

}