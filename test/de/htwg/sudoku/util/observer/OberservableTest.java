package de.htwg.sudoku.util.observer;

import org.junit.Before;
import org.junit.Test;
import util.observer.Event;
import util.observer.IObserver;
import util.observer.Observable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OberservableTest {
	private boolean ping=false;
	private TestObserver testObserver;
	private Observable testObservable;
	
	class TestObserver implements IObserver {
		//@Override
		public void update(Event e) {
			ping=true;
		}
		
	}

	@Before
	public void setUp() throws Exception {
		testObserver = new TestObserver();
		testObservable = new Observable();
		testObservable.addObserver(testObserver);
	}

	@Test
	public void testNotify() {
		assertFalse(ping);
		testObservable.notifyObservers();
		assertTrue(ping);
	}
	
	@Test
	public void testRemove() {
		assertFalse(ping);
		testObservable.removeObserver(testObserver);
		testObservable.notifyObservers();
		assertFalse(ping);
	}
	
	@Test
	public void testRemoveAll() {
		assertFalse(ping);
		testObservable.removeAllObservers();
		testObservable.notifyObservers();
		assertFalse(ping);
	}

}
