import com.ygsoft.jt.teng.fw.core.service.JtService;
import com.ygsoft.jt.teng.fw.core.service.cache.ICache;
// 
String classId = "TestCache";
String type = "testcache"; 
String key = "cache001";
String value = "helloworld";

// 获取名称为TestCache的缓存服务
ICache<Object, Object> cacheService = JT.component.cache.getCacheService(classId, type);

// 将key为cache001的缓存值设为hello world
cacheService.put(key, value);

// 从缓存中获取cache001的值
final String cacheVal = cache.get("cache001"); 