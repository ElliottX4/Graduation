package top.superswc.tools;

import java.util.HashMap;
import java.util.Map;

public final class PojoAndBean{
	/* t:返回的POJO类型
	 * k:传入的bean
	 */
	public static <T> T bean2Pojo(Class<T> t,Object k) throws Exception{
		T temp = t.newInstance();
		return null;
	}
}
