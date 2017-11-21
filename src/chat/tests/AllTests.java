package chat.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is the runs all the tests for chatbot.
 * @author Sean Kuo
 * @version 21/11/17
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ ChatbotTest.class, ControllerTest.class, FrameTest.class, MovieTest.class, PanelTest.class })
public class AllTests
{

}
