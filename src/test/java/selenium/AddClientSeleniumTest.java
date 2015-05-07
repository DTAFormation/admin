package selenium;

import org.junit.Ignore;


import com.thoughtworks.selenium.SeleneseTestCase;

public class AddClientSeleniumTest extends SeleneseTestCase {
	/** {@inheritDoc} */
	@Override
	public void setUp() throws Exception {
		setUp("http://www.netapsys.fr","*firefox"); // delegation de la configuration a la classe parente
	}

	/** Ouvre la page et verifie que le texte est bien present. */
	@Ignore
	public void test() {
		selenium.open("/"); // ouverture de la page
		assertTrue(selenium.isTextPresent("Netapsys"));
	}
}
