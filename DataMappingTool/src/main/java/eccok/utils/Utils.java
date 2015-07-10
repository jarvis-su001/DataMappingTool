/**
 * @Date : Jul 10, 2015
 */
package eccok.utils;

/**
 * @author Jarvis
 * @Date Jul 10, 2015
 *
 */
public class Utils {

	public static final String separator = "|";

	public static void releaseDBResource(Object source, AutoCloseable... closeableResource) {
		if (closeableResource == null || closeableResource.length == 0) {
			return;
		}
		for (AutoCloseable closeable : closeableResource) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (Throwable th) {
					closeable = null;
				}
			}
		}
	}

	public static void releaseDBResource(AutoCloseable... closeableResource) {
		if (closeableResource == null || closeableResource.length == 0) {
			return;
		}
		for (AutoCloseable closeable : closeableResource) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (Throwable th) {
					closeable = null;
				}
			}
		}
	}

}
