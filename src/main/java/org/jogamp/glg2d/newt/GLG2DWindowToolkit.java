package org.jogamp.glg2d.newt;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.ButtonPeer;
import java.awt.peer.CanvasPeer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DesktopPeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.KeyboardFocusManagerPeer;
import java.awt.peer.LabelPeer;
import java.awt.peer.LightweightPeer;
import java.awt.peer.ListPeer;
import java.awt.peer.MenuBarPeer;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;
import java.awt.peer.MouseInfoPeer;
import java.awt.peer.PanelPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.TextFieldPeer;
import java.awt.peer.WindowPeer;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.swing.SwingUtilities;

import sun.awt.KeyboardFocusManagerPeerProvider;

import com.jogamp.newt.Display;
import com.jogamp.newt.NewtFactory;
import com.jogamp.newt.Screen;

/**
 * This toolkit provides integration between Newt and Swing through GLG2D. AWT
 * components are not supported.
 * 
 * -Dawt.toolkit=org.jogamp.glg2d.newt.GLG2DWindowToolkit
 * 
 * @author Dan Avila
 * 
 */
public class GLG2DWindowToolkit extends Toolkit implements
        KeyboardFocusManagerPeerProvider
{
	private static final RuntimeException UNSUPPORTED_DEPRECATED = new UnsupportedOperationException(
	        "This toolkit does not support deprecated methods.");

	private static final RuntimeException UNSUPPORTED_AWT = new UnsupportedOperationException(
	        "This toolkit does not support AWT native peers outside of a window.");

	private EventQueue event = new GLG2DAwareEventQueue();
	private GLG2DMousePeer defaultMousePeer = new GLG2DMousePeer();

	@Override
	protected MouseInfoPeer getMouseInfoPeer()
	{
		return defaultMousePeer;
	}

	@Override
	protected LightweightPeer createComponent(Component target)
	{
		LightweightPeer peer = new GLG2DLightweightPeer(target);

		return peer;

		// return super.createComponent(target);
	}

	@Override
	protected DesktopPeer createDesktopPeer(Desktop target)
	        throws HeadlessException
	{
		return null;
	}

	@Override
	protected ButtonPeer createButton(Button target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected TextFieldPeer createTextField(TextField target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected LabelPeer createLabel(Label target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected ListPeer createList(List target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected CheckboxPeer createCheckbox(Checkbox target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected ScrollbarPeer createScrollbar(Scrollbar target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected ScrollPanePeer createScrollPane(ScrollPane target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected TextAreaPeer createTextArea(TextArea target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected ChoicePeer createChoice(Choice target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	// TODO: Until this is implemented properly, this method will not work.
	@Override
	protected FramePeer createFrame(Frame target) throws HeadlessException
	{
		FramePeer peer = new GLG2DWindowPeer(target);

		return peer;
	}

	@Override
	protected CanvasPeer createCanvas(Canvas target)
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected PanelPeer createPanel(Panel target)
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected WindowPeer createWindow(Window target) throws HeadlessException
	{
		Window window = SwingUtilities.getWindowAncestor(target);

		if (window instanceof GLG2DFrame)
		{
			return new GLG2DWindowPeer((Frame) window);
		}
		else
		{
			return null;
		}
	}

	@Override
	protected DialogPeer createDialog(Dialog target) throws HeadlessException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MenuBarPeer createMenuBar(MenuBar target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected MenuPeer createMenu(Menu target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected PopupMenuPeer createPopupMenu(PopupMenu target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected MenuItemPeer createMenuItem(MenuItem target)
	        throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	protected FileDialogPeer createFileDialog(FileDialog target)
	        throws HeadlessException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected CheckboxMenuItemPeer createCheckboxMenuItem(
	        CheckboxMenuItem target) throws HeadlessException
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	@Deprecated
	protected FontPeer getFontPeer(String name, int style)
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	public Dimension getScreenSize() throws HeadlessException
	{
		Display display = NewtFactory.createDisplay(null, true);
		Screen screen = NewtFactory.createScreen(display, 0);

		Dimension dim = new Dimension(screen.getWidth(), screen.getHeight());

		return dim;
	}

	@Override
	public int getScreenResolution() throws HeadlessException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ColorModel getColorModel() throws HeadlessException
	{
		return ColorModel.getRGBdefault();
	}

	@Override
	@Deprecated
	public String[] getFontList()
	{
		throw UNSUPPORTED_DEPRECATED;
	}

	@Override
	@Deprecated
	public FontMetrics getFontMetrics(Font font)
	{
		throw UNSUPPORTED_DEPRECATED;
	}

	@Override
	public void sync()
	{
		// This method does nothing.
	}

	@Override
	public Image getImage(String filename)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage(URL url)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image createImage(String filename)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image createImage(URL url)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean prepareImage(Image image, int width, int height,
	        ImageObserver observer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkImage(Image image, int width, int height,
	        ImageObserver observer)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Image createImage(ImageProducer producer)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image createImage(byte[] imagedata, int imageoffset, int imagelength)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props)
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	public void beep()
	{
		throw UNSUPPORTED_AWT;
	}

	@Override
	public Clipboard getSystemClipboard() throws HeadlessException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EventQueue getSystemEventQueueImpl()
	{
		return event;
	}

	@Override
	public DragSourceContextPeer createDragSourceContextPeer(
	        DragGestureEvent dge) throws InvalidDnDOperationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isModalityTypeSupported(ModalityType modalityType)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isModalExclusionTypeSupported(
	        ModalExclusionType modalExclusionType)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<TextAttribute, ?> mapInputMethodHighlight(
	        InputMethodHighlight highlight) throws HeadlessException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyboardFocusManagerPeer createKeyboardFocusManagerPeer(
	        KeyboardFocusManager arg0)
	{
		return new GLG2DWindowKeyboardPeer();
	}

}
