package org.jogamp.glg2d.newt;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Simple aspect for capturing all peer-related commands.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
@Aspect
public class TracingAspect
{
	@Pointcut("execution(* GLG2DLightweightPeer.*(..))")
	public void lightweightPeer()
	{
	}

	@Pointcut("execution(* GLG2DWindowPeer.*(..))")
	public void windowPeer()
	{
	}

	@Pointcut("execution(* GLG2DWindowToolkit.*(..))")
	public void toolkit()
	{
	}

	@Pointcut("windowPeer() || lightweightPeer()")
	public void peerMethods()
	{
	}

	@Pointcut("execution(* GLG2DWindowPeer.getLocationOnScreen(..))")
	public void ignoreList()
	{
	}

	@Pointcut("execution(* org.jogamp.glg2d.GLGraphics2D.translate(..)) || execution(* org.jogamp.glg2d.GLGraphics2D.create(..)) || execution(* org.jogamp.glg2d.GLGraphics2D.draw*(..)) || execution(* org.jogamp.glg2d.GLGraphics2D.dispose(..))")
	public void graphics()
	{
	}

	@Pointcut("execution(* org.jogamp.glg2d.GLGraphics2D.getGLContext(..)) || execution(* org.jogamp.glg2d.GLGraphics2D.setCanvas(..)) || execution(* org.jogamp.glg2d.GLGraphics2D.prePaint(..))")
	public void ignoreGraphics()
	{
	}

	// @Around("graphics() && !ignoreGraphics()")
	public Object graphics(ProceedingJoinPoint pjp) throws Throwable
	{
		return trace(pjp);
	}

	@Around("peerMethods() && !ignoreList()")
	public Object trace(ProceedingJoinPoint pjp) throws Throwable
	{
		Object[] args = pjp.getArgs();

		Object retval = pjp.proceed();

		String retValString = "";

		if (pjp.getSignature().toLongString().contains("void"))
		{
			retValString = "void";
		}
		else
		{
			retValString = retval == null ? "null" : retval.toString();
		}

		System.err.print("Trace: ");
		System.err.print(retValString);
		System.err.print(" ");
		System.err.print(pjp.getSignature().toShortString());

		if (args != null && args.length > 0)
		{
			System.err.print(" : ");
			System.err.println(Arrays.toString(args));
		}
		else
		{
			System.err.println();
		}

		return retval;
	}
}
