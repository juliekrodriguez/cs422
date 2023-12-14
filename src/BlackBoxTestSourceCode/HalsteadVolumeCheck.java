package BlackBoxTestSourceCode;

public class HalsteadVolumeCheck {
	private int value;

	public HalsteadVolumeCheck(int initialValue) {
		value = initialValue;
	}

	public int incrementBy(int increment) {
		value += increment;
		return value;
	}
}
