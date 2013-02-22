package org.jogamp.glg2d.event;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import com.jogamp.newt.event.InputEvent;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

/**
 * This class handles the translation between key events and mouse events.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class NewtKeyEventTranslator implements KeyListener
{
	private Component source;

	public NewtKeyEventTranslator(Component source)
	{
		this.source = source;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		handle(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		handle(e);
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyChar() == java.awt.event.KeyEvent.CHAR_UNDEFINED)
		{
			return;
		}

		handle(e);
	}

	private void handle(KeyEvent event)
	{
		int id = newtId2awt(event);
		long when = event.getWhen();
		int modifiers = newtModifiers2awt(event);
		int keyCode = event.getKeyCode();
		char keyChar = event.getKeyChar();

		if (id == java.awt.event.KeyEvent.KEY_TYPED)
		{
			keyCode = java.awt.event.KeyEvent.VK_UNDEFINED;
		}

		java.awt.event.KeyEvent awtEvent = new java.awt.event.KeyEvent(source,
		        id, when, modifiers, keyCode, keyChar);

		EventQueue queue = Toolkit.getDefaultToolkit().getSystemEventQueue();

		queue.postEvent(awtEvent);
	}

	private int newtId2awt(KeyEvent event)
	{
		switch (event.getEventType())
		{
		case KeyEvent.EVENT_KEY_PRESSED:
			return java.awt.event.KeyEvent.KEY_PRESSED;
		case KeyEvent.EVENT_KEY_RELEASED:
			return java.awt.event.KeyEvent.KEY_RELEASED;
		case KeyEvent.EVENT_KEY_TYPED:
			return java.awt.event.KeyEvent.KEY_TYPED;
		}

		throw new IllegalStateException("Unexpected key event type: "
		        + KeyEvent.getEventTypeString(event.getEventType()));
	}

	private int newtModifiers2awt(KeyEvent event)
	{
		int newtMods = event.getModifiers();
		int awtMods = 0;

		if ((newtMods & InputEvent.SHIFT_MASK) != 0)
		{
			awtMods |= java.awt.event.InputEvent.SHIFT_MASK;
		}
		if ((newtMods & InputEvent.CTRL_MASK) != 0)
		{
			awtMods |= java.awt.event.InputEvent.CTRL_MASK;
		}
		if ((newtMods & InputEvent.META_MASK) != 0)
		{
			awtMods |= java.awt.event.InputEvent.META_MASK;
		}
		if ((newtMods & InputEvent.ALT_MASK) != 0)
		{
			awtMods |= java.awt.event.InputEvent.ALT_MASK;
		}
		if ((newtMods & InputEvent.ALT_GRAPH_MASK) != 0)
		{
			awtMods |= java.awt.event.InputEvent.ALT_GRAPH_MASK;
		}

		return awtMods;
	}
}
