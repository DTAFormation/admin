package selenium;

import org.junit.Test;

import com.thoughtworks.selenium.SeleneseTestCase;

public class AddClientSeleniumTest extends SeleneseTestCase {
	/** {@inheritDoc} */
	@Override
	public void setUp() throws Exception {
		setUp("http://www.netapsys.fr","*firefox"); // délégation de la configuration à la classe parente
	}

	/** Ouvre la page et vérifie que le texte est bien présent. */
	public void test() {
		selenium.open("/"); // ouverture de la page
		assertTrue(selenium.isTextPresent("Netapsys"));
	}
}
