package chatterbot;

public interface ChatterBot {

	ChatterBotThought think(ChatterBotThought thought) throws Exception;

	String think(String text) throws Exception;

}