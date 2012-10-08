package chatterbot;

public final class ChatterBotThought {

	private final String text;
	private final String[] emotions;

	public ChatterBotThought(String text, String[] emotions) {
		this.text = text;
		this.emotions = emotions;
	}

	public ChatterBotThought(String text) {
		this(text, null);
	}

	public String getText() {
		return text;
	}

	public String[] getEmotions() {
		if (emotions == null) return null;
		return emotions.clone();
	}

}