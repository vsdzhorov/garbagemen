package model;

import controller.Dimension2D;
import model.PowerUp;
import model.Spaceship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

@ExtendWith(EasyMockExtension.class)
public class SpaceshipPowerUpsTest {

	@TestSubject
	private Spaceship spaceship = new Spaceship(new Dimension2D(100, 100));

	@Mock
	private PowerUp powerUpMock;

	/*
	 * We are unsure as to how we are going to distribute the powerup ( random seed,
	 * certain conditional scenarios etc.) which is why we need to mock the
	 * evaluate() method. We check if the addPowerUp method of the spaceship works
	 * by mocking the evaluate method since this is required for the method call in
	 * addPowerUp.
	 */
	@Test
	void testSpaceshipPowerUpListUpdate() {
		expect(powerUpMock.evaluate()).andReturn(true);
		replay(powerUpMock);

		// after the evaluate() the addPowerUp should add the powerUp to the list thus
		// increasing size by 1
		int expected = spaceship.getPowerUps().size() + 1;
		spaceship.addPowerUp(powerUpMock);
		int actual = spaceship.getPowerUps().size();

		assertEquals(expected, actual);
	}
}
