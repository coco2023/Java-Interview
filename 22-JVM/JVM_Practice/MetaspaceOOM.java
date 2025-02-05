/**
异常信息：java.lang.OutOfMemoryError: Metaspace

参数调整：-XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m

处理方法：清理无效的类加载器，避免动态生成过多类。
 */
// 代码示例：动态生成类填满元空间
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class MetaspaceOOM {
          public static void main(String[] args) {
                    while (true) {
                        Enhancer enhancer = new Enhancer();
                        enhancer.setSuperclass(Object.class);
                        enhancer.setUseCache(false); // 禁用缓存加速溢出
                        enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> null);
                        enhancer.create(); // 动态生成类
                    }
          }
}
